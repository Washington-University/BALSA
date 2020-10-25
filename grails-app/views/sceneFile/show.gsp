<%@ page import="balsa.file.SceneFile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:fieldValue bean="${file}" field="filename"/></title>
	</head>
	<body>
		<div role="main">
			<h3>Scene File: <g:fieldValue bean="${file}" field="filename"/></h3>
			
			<g:render template="/templates/terms" bean="${file.terms()}" var="terms" />
			<g:render template="/download/downloadModal" bean="${file}" var="item" />
			<div class="btn-group">
				<g:if test="${item?.canEdit()}">
					<g:link class="btn btn-default" action="edit" id="${item.id}">Edit</g:link>
				</g:if>
				<g:render template="/templates/buttons" bean="${file}" var="item" />
				<g:if test="${file?.canEdit()}">
				<g:link class="btn btn-default" controller="${datasetTerm('item':file)}" action="editScenes" id="${file.dataset.id}" params="[startId: file.id]">Edit Scenes</g:link>
				</g:if>
				<button type="button" class="btn btn-default" role="button" data-toggle="modal" data-target="#fileModal">Supporting Files</button>
				<g:if test="${file?.canEdit()}">
				<button type="button" class="btn btn-default" onclick="bootbox.confirm({size: 'small', message: 'Remove this file?<g:if test="${file?.isPublic()}"> Removing a file creates a new working version of the dataset, but the file will remain in the current public version.</g:if>', 
					callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="remove" id="${file.id}"/>');} } })">
					Remove file
				</button>
				</g:if>
			</div>
			<g:render template="/templates/fileModal" model="['files':dependencies,'datasetInstance':file.dataset]" />

			<br><br>
			<div class="well <g:datasetTerm item="${file}"/>" style="overflow: hidden;">
				<g:render template="carouselWithThumbnails" model="['sceneFile': file, 'focusScene': file.focusScene(versionId)]" />
				
				<p>
					<span class="attributeLabel">${datasetTerm('item':file)}:</span><br>
					<g:if test="${versionId}">
					<g:link action="show" controller="${datasetTerm('item':file)}" id="${file.dataset.id}" params="[version: versionId]">${file.dataset.title}</g:link>
					</g:if>
					<g:else>
					<g:link action="show" controller="${datasetTerm('item':file)}" id="${file.dataset.id}">${file.dataset.title}</g:link>
					</g:else>
				</p>
				
				<g:if test="${file.canEdit()}">
				<p>
					<span class="attributeLabel">FILE ADDED:</span><br>
					${file.added.format('yyyy-MM-dd h:mm a z')}
				</p>
				</g:if>
				
				<p>
					<span class="attributeLabel">PATH:</span><br>
					${file.filepath.replace('/',' / ')}
				</p>
				
				<g:if test="${file.label}">
				<p>
					<span class="attributeLabel">DESCRIPTIVE LABEL:</span><br>
					${file.label}
				</p>
				</g:if>
				
				<div>
					<span class="attributeLabel">SCENES:</span><br>
					<ul>
						<g:each in="${file.scenesSorted()}" var="scene">
						<li>
							<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.name?.encodeAsHTML()}</g:link>
							<g:if test="${scene.canView()}">
							(Index: ${scene.index}, ID: ${scene.sceneLine.id})
							</g:if>
						</li>
						</g:each>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
