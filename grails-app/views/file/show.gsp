<%@ page import="balsa.file.FileMetadata" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:fieldValue bean="${file}" field="filename"/></title>
	</head>
	<body>
		<div role="main">
			<h3>File: <g:fieldValue bean="${file}" field="filename"/></h3>
			
			<g:render template="/templates/terms" bean="${file.terms()}" var="terms" />
			<g:render template="/download/downloadModal" bean="${file}" var="item" />
			<div class="btn-group">
				<g:render template="/templates/buttons" bean="${file}" var="item" />
				<g:link class="btn btn-default" action="allScenes" resource="${file}">See all scenes using this file</g:link>
				<g:link class="btn btn-default" controller="download" action="downloadFile" id="${file.id}">Download file</g:link>
				
				<g:if test="${file?.canEdit()}">
				<button type="button" class="btn btn-default" onclick="bootbox.confirm({size: 'small', message: 'Remove this file?<g:if test="${file?.isPublic()}"> Removing a file creates a new working version of the dataset, but the file will remain in the current public version.</g:if>', 
					callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="remove" id="${file.id}"/>');} } })">
					Remove file
				</button>
				
				</g:if>
			</div>
			
			<br><br>
			
			<div class="well <g:datasetTerm item="${file}"/>" style="overflow: hidden;">
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
					<span class="attributeLabel">TYPE:</span><br>
					<g:link action="fileTypes" controller="about" fragment="${fileCategory('filename':file.filename)}">${fileCategory('filename':file.filename)} : ${fullFileTerm('filename':file.filename)}</g:link>
				</p>
				
				<p>
					<span class="attributeLabel">PATH:</span><br>
					${file.filepath.replace('/',' / ')}
				</p>
				
				<p>
					<span class="attributeLabel">SIZE:</span><br>
					${displaySize('bytes':file.filesize)}
				</p>
				
				<p>
					<span class="attributeLabel">TAGS:</span><br>
					<g:join in="${file.tags}" delimiter=", "/>
				</p>
				
			</div>
		</div>
	</body>
</html>
