<%@ page import="balsa.scene.Scene" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${sceneInstance.name}</title>
	</head>
	<body>
		<div role="main">
			<div id="fullSizeModal" class="modal fade" role="dialog">
				<div class="modal-dialog fullsize-image">
					<div class="modal-content">
						<div class="modal-header ${datasetTerm('item':datasetInstance)}">
							<h4 class="modal-title">Scene Preview</h4>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						</div>
						<div class="modal-body">
							<img src=<g:createLink controller="scene" action="image" id="${sceneInstance?.id}"/>/>
						</div>
					</div>
				</div>
			</div>
			
			<g:render template="/templates/terms" bean="${sceneInstance.terms()}" var="terms" />
			<g:render template="/download/downloadModal" bean="${sceneInstance}" var="item" />
			<g:render template="/templates/fileModal" model="['files':dependencies,'datasetInstance':sceneInstance.sceneFile.dataset]" />
			
			<div class="card overflow-hidden <g:datasetTerm item="${sceneInstance}"/>">
				<div class="card-header h3 mb-0">Scene: <g:fieldValue bean="${sceneInstance}" field="name"/> <g:if test="${sceneInstance.shortName}">/ <g:fieldValue bean="${sceneInstance}" field="shortName"/></g:if></div>
				<div class="btn-group btn-bar">
					<g:render template="/templates/buttons" bean="${sceneInstance}" var="item" />
					<g:if test="${sceneInstance?.canEdit()}">
					<g:link class="btn btn-light" controller="${datasetTerm('item':sceneInstance)}" action="editScenes" id="${sceneInstance.sceneFile.dataset.id}" params="[startId: sceneInstance.sceneLine.id]">Edit Scenes</g:link>
					</g:if>
					<button type="button" class="btn btn-light" role="button" data-toggle="modal" data-target="#fileModal">Supporting Files</button>
				</div>
				<div class="card-body">
					<div class="float-right" style="padding-left:10px">
						<div data-toggle="modal" data-target="#fullSizeModal" class="scalingBox" style="width:580px;height:380px;">
							<img src=<g:createLink controller="scene" action="image" id="${sceneInstance?.id}"/>/>
						</div>
					</div>

					<p>
						<span class="attributeLabel">${datasetTerm('item':sceneInstance)}:</span><br>
						<g:link action="show" controller="${datasetTerm('item':sceneInstance)}" id="${sceneInstance.sceneFile.dataset.id}">${sceneInstance.sceneFile.dataset.shortTitle ?: sceneInstance.sceneFile.dataset.title}</g:link>
					</p>

					<p>
						<span class="attributeLabel">SCENE FILE:</span><br>
						<g:link action="show" controller="sceneFile" id="${sceneInstance.sceneFile.id}">${sceneInstance.sceneFile.filename.replace('.scene','')}</g:link>
					</p>

					<p>
						<span class="attributeLabel">SCENE:</span><br>
						<g:fieldValue bean="${sceneInstance}" field="name"/>
					</p>

					<p>
						<span class="attributeLabel">DESCRIPTION:</span><br>
						<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${sceneInstance}" field="description"/></g:encodeAs>
					</p>

					<p>
						<span class="attributeLabel">TAGS:</span><br>
						<g:join in="${sceneInstance.tags}" delimiter=", "/>
					</p>
				</div>
			</div>
		</div>
	</body>
</html>
