package balsa

import balsa.file.FileMetadata
import balsa.scene.Scene

class Download {
	String id
	Date date
	String username
	String ipAddress
	Dataset dataset
	long totalSize
	
	static hasMany = [files: FileMetadata, scenes: Scene]
	static belongsTo = [Dataset, FileMetadata, Scene]

    static constraints = {
		username nullable: true, blank: true
		dataset nullable: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		ipAddress index: 'download_ip_index'
		files joinTable: [name: 'file_metadata_download', key: 'download_id']
		scenes joinTable: [name: 'scene_download', key: 'download_id']
	}
}
