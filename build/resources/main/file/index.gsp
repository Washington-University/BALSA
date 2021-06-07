<%@ page import="balsa.file.FileMetadata" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Files</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Files</div>
				<div class="card-body p-0">
					<table class="table table-hover tablesorter mb-0 filterer" data-cssfilter='["form-control","form-control","form-control custom-select","form-control","form-control"]' data-sort='[[0,0]]'>
						<thead>
							<tr>
								<th class="border-0">File Name</th>
								<th class="border-0">Dataset</th>
								<th class="border-0 filter-select filter-exact" data-placeholder="All">Type</th>
								<th class="border-0">Added</th>
								<th class="border-0 filter-false">Size</th>
							</tr>
						</thead>
						<g:render template="/templates/tableFooter" model="[cols: 5]" />
						<tbody>
							<g:each in="${fileList}" var="file">
							<tr>
								<td>
									<g:link class="d-inline-block text-truncate" style="width:500px" action="show" id="${file.id}">${file.filename}</g:link>
								</td>
								<td>
									<g:link class="d-inline-block text-truncate" style="width:500px" action="show" controller="${datasetTerm('item':file)}" id="${file.dataset.id}">${file.dataset.shortTitle ?: file.dataset.title}</g:link>
								</td>
								<td style="width:200px">
									${shortFileTerm('filename':file.filename)}
								</td>
								<td>
									<g:formatDate format="yyyy-MM-dd" date="${file.added}"/>
								</td>
								<td>
									<span class="d-none">${file.filesize}</span><g:displaySize bytes="${file.filesize}"/>
								</td>
							</tr>
							</g:each>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
