<ul class="rlist">
    <g:each in="${directory.contents}" var="file">
        <li>
            <g:if test="${file.dirName}">
                <span class="rlistcaret">${file.dirName}</span>
                <g:render template="/templates/fileListRecursive" model="[directory: file]" />
            </g:if>
        </li>
    </g:each>
    <g:each in="${directory.contents}" var="file">
        <li>
            <g:if test="${file.filename}">
                <g:link controller="myelin" action="download" params="[dirName: dirName, filepath: file.filepath, dirPass: dirPass]">
                    ${file.filename} - <g:displaySize bytes="${file.size}"/> - ${file.lastModified.format('yyyy-MM-dd HH:mm:ss')}
                </g:link>
            </g:if>
        </li>
    </g:each>
</ul>