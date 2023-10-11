<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Shared Files</title>
</head>
<body>
<div role="main">
    <div class="card overflow-hidden">
        <div class="card-header h3 mb-0">Shared Files</div>
        <div class="btn-group btn-bar">
            <div class="dropdown">
                <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">Change Directory</button>
                <div class="dropdown-menu">
                    <g:each in="${dirList}">
                        <g:link class="dropdown-item ${it.name == dirName ? 'active' : ''}" controller="myelin" params="[dirName: it.name]">${it.name}</g:link>
                    </g:each>
                </div>
            </div>
            <g:if test="${dirPass}">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadFileModal">Upload</button>
            </g:if>
        </div>
        <div class="card-body">
            <g:if test="${error}">
                ${error}
            </g:if>
            <g:elseif test="${passwordNeeded}">
                <g:form class="form-inline" name="myForm" action="index" params="[dirName: dirName]">
                    <div class="form-group mr-3">
                        <input type="text" class="form-control" name="dirPass" placeholder="Password"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </g:form>
            </g:elseif>
            <g:else>
                <input type="hidden" id="dirPass" value="${dirPass}"/>
                <g:render template="/templates/fileListRecursive"  model="[directory: directory]" />
            </g:else>
        </div>
    </div>
</div>
<g:if test="${dirPass}">
    <div id="uploadFileModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-wide">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Upload Data Files</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body" id="uploadFileModalContents">
                    <div id="uploadAccordion">
                        <button id="uploadAccordionButton" class="d-none" data-toggle="collapse" data-target="#uploadProgress"></button>

                        <div id="uploadInput" class="collapse show" data-parent="#uploadAccordion">
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="fileUpload" onchange="uploadMyelinFile()" data-controller="myelin" data-dir="${dirName}">
                                <label class="custom-file-label" for="fileUpload">Choose file</label>
                            </div>
                        </div>
                        <div id="uploadProgress" class="collapse" data-parent="#uploadAccordion">
                            <div class="progress" style="height:33.5px">
                                <div id="uploadProgressBar" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" ></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</g:if>
</body>
</html>
