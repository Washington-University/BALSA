<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Upload Data Files</title>
	</head>
	<body>
		<div role="main">
			<h3>Upload Data Files for ${datasetInstance.title}</h3>
			
			<div class="btn-group">
				<g:link class="btn btn-default" action="show" resource="${datasetInstance}">Cancel</g:link>
				<g:link class="btn btn-primary" action="processUpload" resource="${datasetInstance}">Process</g:link>
			</div>
			
			<g:if test="${datasetInstance.publicVersion() || datasetInstance.preprintVersion()}">
			<div>
				Uploading files creates a new working version of the dataset. New files will not be available in the public version of the dataset until it has been gone through curation.
			</div>
			</g:if>
				
			<br><br>
			<div class="well <g:datasetTerm item="${file}"/>" style="overflow: hidden;">
				<uploadr:add name="uploader" path="${datasetInstance.getId()}" allowedExtensions="zip,txt,rtf,pdf,odt,odp,wpd,doc,docx,ppt,pptx,jpg,png,fig,m,gif,csv" controller="${datasetTerm('item':datasetInstance)}" 
					action="handleUpload" noSound="true" viewable="false" downloadable="false" deletable="false" params="${[id:datasetInstance.id]}"/>
			</div>
		</div>
	</body>
</html>