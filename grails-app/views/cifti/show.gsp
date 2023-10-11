<%@ page import="balsa.file.FileMetadata" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:fieldValue bean="${file}" field="filename"/></title>
	</head>
	<body>
		<div role="main">
			<g:render template="/templates/terms" bean="${file.terms()}" var="terms" />
			<g:render template="/download/downloadModal" bean="${file}" var="item" />
			
			<div class="card overflow-hidden <g:datasetTerm item="${file}"/>">
				<div class="card-header h3 mb-0">File: <g:fieldValue bean="${file}" field="filename"/></div>
				<div class="btn-group btn-bar">
					<g:render template="/templates/buttons" bean="${file}" var="item" />
					<g:link class="btn btn-light" action="allScenes" resource="${file}">See all scenes using this file</g:link>
					<g:link class="btn btn-light" controller="download" action="downloadFile" id="${file.id}">Download file</g:link>

					<g:if test="${file?.canEdit()}">
					<button type="button" class="btn btn-light" onclick="bootbox.confirm({size: 'small', message: 'Remove this file?<g:if test="${file?.isPublic()}"> Removing a file creates a new working version of the dataset, but the file will remain in the current public version.</g:if>', 
						callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="remove" id="${file.id}"/>');} } })">
						Remove file
					</button>
					</g:if>
				</div>
				<div class="card-body">
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
						<span class="attributeLabel">VERSIONS:</span><br>
						NIFTI ${file.niftiVersion}, CIFTI ${file.ciftiVersion}
					</p>
					
					<g:if test="${file.namedMaps && file.namedMaps*.value.flatten()}">
					<p>
						<span class="attributeLabel">LABEL TABLES:</span><br>
						<ul>
							<g:each in="${file.namedMaps}" var="labelTable" status="index">
							<g:if test="${labelTable.value}">
							<li>
								<span class="attributeLabel">TABLE NAME:</span>
								${labelTable.key}
								<br>
								
								<span class="attributeLabel">LABELS:</span><br>
								<a class="btn btn-light mb-3" data-toggle="collapse" data-target="#label_table_rows${index}">click to show/hide</a>
								<div id="label_table_rows${index}" class="collapse">
									<div style="column-count: 3; column-fill: auto; max-height: ${Math.max(15, Math.ceil(labelTable.value.size() / 3)) * 1.4}em">
										<g:each in="${labelTable.value}">
											<div class="row">
												<div class="col-sm-3 text-right text-monospace">${it.index}:</div>
												<g:if test="${it.alpha}">
													<div class="col-sm-2 border border-secondary text-center" style="background-color:rgba(${it.red},${it.green},${it.blue},${it.alpha})"></div>
												</g:if>
												<g:else>
													<svg class="col-sm-2 p-0 border border-secondary" style="height:1.4em">
														<line x1="100%" y1="0" x2="0" y2="100%" style="fill:none; stroke:#6c757d; stroke-width:1px;"></line>
														<line x1="0" y1="0" x2="100%" y2="100%" style="fill:none; stroke:#6c757d; stroke-width:1px;"></line>
													</svg>
												</g:else>
												<div class="col-7">${it.label}</div>
											</div>
										</g:each>
									</div><br>
								</div>
							</li>
							</g:if>
							</g:each>
						</ul>
					</p>
					</g:if>

					<g:render template="/templates/fileInfo" />

					<p>
						<span class="attributeLabel">TAGS:</span><br>
						<g:join in="${file.tags}" delimiter=", "/>
					</p>
				</div>
			</div>
		</div>
	</body>
</html>
