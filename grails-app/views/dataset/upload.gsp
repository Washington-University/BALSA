<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Upload Data Files</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden ${datasetTerm('item':datasetInstance)}">
				<div class="card-header h3 mb-0">Upload Data Files to ${datasetInstance.shortTitle ?: datasetInstance.title}</div>
				<div class="btn-group btn-bar">
					
					<g:link class="btn btn-light" action="show" resource="${datasetInstance}">Cancel</g:link>
				</div>
				<div class="card-body">
					<g:if test="${datasetInstance.publicVersion() || datasetInstance.preprintVersion()}">
					Uploading files creates a new working version of the dataset. New files will not be available in the public version of the dataset until it has been gone through curation.<br><br>
					</g:if>
						<div id="uploadAccordion">
							<button id="uploadAccordionButton" class="d-none" data-toggle="collapse" data-target="#uploadProgress"></button>

							<div id="uploadInput" class="collapse show" data-parent="#uploadAccordion">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="fileUpload" onchange="uploadFile()" data-controller="${datasetTerm('item':datasetInstance)}" data-datasetid="${datasetInstance.id}">
									<label class="custom-file-label" for="fileUpload">Choose file</label>
								</div>
							</div>
							<div id="uploadProgress" class="collapse" data-parent="#uploadAccordion">
								<g:link class="float-right btn btn-secondary disabled ml-4" elementId="processUploadButton" action="processUpload" resource="${datasetInstance}">Process</g:link>
								<div class="progress" style="height:33.5px">
									<div id="uploadProgressBar" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" ></div>
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</body>
</html>