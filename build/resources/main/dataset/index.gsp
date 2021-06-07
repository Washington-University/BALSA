<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.Version" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${datasetType} Index</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden ${datasetType.toLowerCase()}">
				<div class="card-header h3 mb-0">${datasetType} Index</div>
				<div class="card-body p-0">
					<g:if test="${datasetType=='Study'}">
					<sec:ifNotGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
					<table class="table table-hover tablesorter pager mb-0 filterer" data-cssfilter='["form-control","form-control","form-control","form-control","form-control"]' data-sort='[[0,0]]'>
						<g:render template="/templates/tableFooter" model="[cols: 5]" />
					
					</sec:ifNotGranted>

					<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
					<table class="table table-hover tablesorter pager mb-0 filterer" data-cssfilter='["form-control","form-control","form-control","form-control","form-control","form-control custom-select","d-none"]' data-sort='[[0,0]]'>
						<g:render template="/templates/tableFooter" model="[cols: 7]" /></sec:ifAnyGranted>
					</g:if>
					
					<g:else>
					<sec:ifNotGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
					<table class="table table-hover tablesorter pager mb-0 filterer" data-cssfilter='["form-control","form-control"]' data-sort='[[0,0]]'>
						<g:render template="/templates/tableFooter" model="[cols: 2]" />
					</sec:ifNotGranted>

					<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
					<table class="table table-hover tablesorter pager mb-0 filterer" data-cssfilter='["form-control","form-control","form-control custom-select","d-none"]' data-sort='[[0,0]]'>
						<g:render template="/templates/tableFooter" model="[cols: 4]" />
					</sec:ifAnyGranted>
					</g:else>
					
						<thead>
							<tr>
								<th class="border-0">Title</th>
								<th class="border-0">Species</th>
								
								<g:if test="${datasetType=='Study'}">
								<th class="border-0">PMID</th>
								<th class="border-0">DOI</th>
								<th class="border-0 sorter-false" style="width:120px">Owners</th>
								</g:if>
								
								<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
								<th class="border-0 filter-select filter-exact" data-placeholder="All">Status</th>
								<th class="border-0 sorter-false filter-false">Other Versions</th>
								</sec:ifAnyGranted>
							</tr>
						</thead>
						<tbody>
							<g:each in="${datasetInstanceList}" var="datasetInstance">
							<tr>
								<td>
									<g:link class="d-inline-block text-truncate" style="width:500px" action="show" id="${datasetInstance.id}">${datasetInstance.shortTitle ?: datasetInstance.title}</g:link>
								</td>
								<td style="white-space: nowrap;">
									${datasetInstance.species*.name.sort().join(', ')}
								</td>
								
								<g:if test="${datasetType=='Study'}">
								<td>
									<a href="https://www.ncbi.nlm.nih.gov/pubmed/${datasetInstance.publicVersion()?.pmid}" target="_blank">${datasetInstance.publicVersion()?.pmid}</a>
								</td>
								<td>
									<a href="https://doi.org/${datasetInstance.publicVersion()?.doi}" target="_blank">${datasetInstance.publicVersion()?.doi}</a>
								</td>
								<td>
									<span class="d-none">${datasetInstance.owners*.username.sort().join(', ')}</span><a class="balsaPopover" tabindex="0" role="button" data-toggle="popover" data-placement="bottom" data-trigger="focus" data-placement="left" data-content="${datasetInstance.owners*.username.sort().join('\n')}">click to view</a>
								</td>

								</g:if>
								
								<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
								<td >
									${datasetInstance.workingVersion().status}
								</td>
								<td>
									<div class="dropdown">
										<button class="btn btn-light dropdown-toggle btn-thin" type="button" data-toggle="dropdown">All Versions</button>
										<div class="dropdown-menu dropdown-menu-right">
											<g:each in="${datasetInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}}" var="version">
												<g:link class="dropdown-item" controller="${datasetType.toLowerCase()}" action="show" id="${datasetInstance.id}" params="[version: version.id]">
													${version.status == Version.Status.PUBLIC && version.preprint ? 'PREPRINT' : version.status} - ${version.updatedDate}
												</g:link>
											</g:each>
										</div>
									</div>
								</td>
								</sec:ifAnyGranted>
							</tr>
							</g:each>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
