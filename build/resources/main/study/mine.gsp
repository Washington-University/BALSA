<%@ page import="balsa.security.Approval" %>
<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.Version" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>My Studies</title>
	</head>
	<body>
		<div id="list-study" role="main">
			<div class="card study overflow-hidden">
				<div class="card-header h3 mb-0">My Studies</div>
				<div class="card-body p-0">
					<table class="table table-hover tablesorter mb-0 filterer" data-cssfilter='["form-control","form-control","form-control","d-none","d-none"]' data-sort='[[0,0]]'>
						<thead>
							<tr>
								<th class="border-0">Title</th>
								<th class="border-0 sorter-false">Co-owners</th>
								<th class="border-0 filter-select filter-exact" data-placeholder="All">Most Recent Status</th>
								<th class="border-0 sorter-false filter-false">Other Versions</th>
								<th class="border-0 sorter-false filter-false">Delete</th>
							</tr>
						</thead>

						<tbody>
							<g:each in="${studyInstanceList}" var="studyInstance">
							<tr>
								<td>
									<g:link class="d-inline-block text-truncate" style="width:500px" action="show" id="${studyInstance.id}">${studyInstance.shortTitle ?: studyInstance.title}</g:link>
								</td>
								<td>
									<span class="d-none">${studyInstance.owners*.username.sort().join(', ')}</span><a class="balsaPopover" tabindex="0" role="button" data-toggle="popover" data-placement="bottom" data-trigger="focus" data-placement="left" data-content="${studyInstance.owners*.username.sort().join('\n')}">click to view</a>
								</td>
								<td>
									${studyInstance.workingVersion().status}
								</td>
								<td>
									<div class="dropdown">
										<button class="btn btn-light dropdown-toggle btn-thin" type="button" data-toggle="dropdown">All Versions</button>
										<div class="dropdown-menu dropdown-menu-right">
											<g:each in="${studyInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}}" var="version">
												<g:link class="dropdown-item" action="show" id="${studyInstance.id}" params="[version: version.id]">
													${version.status == Version.Status.PUBLIC && version.preprint ? 'PREPRINT' : version.status} - ${version.updatedDate}
												</g:link>
											</g:each>
										</div>
									</div>
								</td>
								<td>
									<a style="cursor:pointer;" onclick="bootbox.confirm({size: 'small', message: 'Delete this study?', callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="delete" id="${studyInstance.id}"/>');} } })">
										Delete
									</a>
								</td>
							</tr>
							</g:each>
						</tbody>
						
						<g:render template="/templates/tableFooter" model="[cols: 5]" />
					</table>
				</div>
			</div>
		</div>
	</body>
</html>