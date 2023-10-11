package balsa

import grails.converters.JSON
import org.apache.catalina.connector.ClientAbortException

import java.nio.file.Files

import grails.plugin.springsecurity.annotation.Secured

import java.text.SimpleDateFormat

@Secured(['permitAll'])
class MyelinController extends AbstractBalsaController {
    private static final SimpleDateFormat RFC2616 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US)
    static final int BUFF_SIZE = 100000;
    static final byte[] buffer = new byte[BUFF_SIZE];

    def index() {
        // list all files in the designated downloads directory
        def rootDir = new File(grailsApplication.config.balsa.myelin.root)
        def dir = params.dirName ? Directory.get(params.dirName) : Directory.get('public')
        def dirList = Directory.list()
        if (dir) {
            if (dir.pass && dir.pass != params.dirPass) {
                [dirName: dir.name, dirList: dirList, passwordNeeded: true]
            }
            else {
                def downloadDir = new File(rootDir, dir.path)
                if ((downloadDir.exists() || downloadDir.mkdirs()) && downloadDir.isDirectory() && downloadDir.canRead()) {
                    [dirName: dir.name, dirList: dirList, directory: fileList(downloadDir, downloadDir), dirPass: dir.pass]
                }
                else {
                    [dirName: dir.name, dirList: dirList, error: 'Could not read download directory.']
                }
            }
        }
        else {
            [dirList: dirList, error: 'Could not read download directory.']
        }
    }

    def private fileList(directory, downloadDir) {
        def files = [dirName: directory.name, contents: []]
        for (File file in directory.listFiles().sort()) {
            if (!file.isHidden() && !file.isDirectory()) {
                files.contents.add([filename: file.name, filepath: downloadDir.toPath().relativize(file.toPath()), size: file.length(), lastModified: new Date(file.lastModified())])
            }
            if (!file.isHidden() && file.isDirectory()) {
                files.contents.add(fileList(file, downloadDir))
            }
        }
        files
    }

    def handleUpload() {
        // handle incoming uploads
        def fileName    = URLDecoder.decode(request.getHeader('X-File-Name'), 'UTF-8') as String
        def fileSize 	= (request.getHeader('X-File-Size') != "undefined") ? request.getHeader('X-File-Size') as Long : 0L
        def dir = params.dirName ? Directory.get(params.dirName) : Directory.get('public')
        if (dir && dir.pass == params.dirPass) {
            def directory = new File(grailsApplication.config.balsa.myelin.root, dir.path)
            def file = new File(directory,fileName)
            int status = 0
            def statusText  = ""

            Files.deleteIfExists(file.toPath())

            // set response content type to json
            response.contentType    = 'application/json'

            // do we have enough space available for this upload?
            def freeSpace = directory.getUsableSpace()
            if (fileSize > freeSpace) {
                // not enough free space
                response.sendError(500, "cannot store '${fileName}' (${fileSize} bytes), only ${freeSpace} bytes of free space left on device")
                render([written: false, fileName: file.name] as JSON)
                return false
            }

            // is the file writable?
            if (!directory.canWrite()) {
                // no, try to make it writable
                if (!directory.setWritable(true)) {
                    response.sendError(500, "Myelin is not writable, and unable to change rights")
                    render([written: false, fileName: file.name] as JSON)
                    return false
                }
            }

            // define input and output streams
            InputStream inStream = null
            OutputStream outStream = null

            // handle file upload
            try {
                inStream = request.getInputStream()
                outStream = new FileOutputStream(file)

                while (true) {
                    synchronized (buffer) {
                        int amountRead = inStream.read(buffer);
                        if (amountRead == -1) {
                            break
                        }
                        outStream.write(buffer, 0, amountRead)
                    }
                }
                outStream.flush()

                status      = 200
                statusText  = "'${file.name}' upload successful!"
            } catch (Exception e) {
                // whoops, looks like something went wrong
                status      = 500
                statusText  = e.getMessage()
            } finally {
                if (inStream != null) inStream.close()
                if (outStream != null) outStream.close()
                file.setReadable(true, false)
                file.setWritable(true, false)
                file.setExecutable(true, false)
            }

            // make sure the file was properly written
            if (status == 200 && fileSize > file.size()) {
                // whoops, looks like the transfer was aborted!
                status      = 500
                statusText  = "'${file.name}' transfer incomplete, received ${file.size()} of ${fileSize} bytes"
            }

            // got an error of some sorts?
            if (status != 200) {
                // then -try to- delete the file
                try {
                    file.delete()
                } catch (Exception e) { }
            }

            // render json response
            response.setStatus(status)
            render([written: (status == 200), fileName: file.name, status: status, statusText: statusText] as JSON)
        }
    }

    def download() {
        // download specific file
        def rootDir = new File(grailsApplication.config.balsa.myelin.root)
        def dir = params.dirName ? Directory.get(params.dirName) : Directory.get('public')
        def file = new File(grailsApplication.config.balsa.myelin.root, dir?.path + '/' + params.filepath)
        if (dir && (!dir.pass || dir.pass == params.dirPass) && file.exists() && file.canRead() && !params.filepath.contains('..')) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-Disposition", "Attachment;Filename=\"${file.name}\"")
            response.setHeader("Content-Length", file.length().toString())
            response.setHeader("Last-Modified", RFC2616.format(file.lastModified()))
            try {
                response.outputStream << file.bytes
                response.outputStream.flush()
                response.outputStream.close()
            }
            catch (ClientAbortException e) {
                // ignore, as this is almost certainly just someone closing their browser window
            }
        }
        else {
            render([status: 500, error: 'Could not read file from directory.'])
        }
    }
}
