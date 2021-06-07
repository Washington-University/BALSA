<!DOCTYPE html>
<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.Version" %>
<%@ page import="balsa.curation.Issue" %>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Curation</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Curation Queue</div>
				<div class="card-body p-0">
					<table class="table table-hover tablesorter pager mb-0 filterer" data-cssfilter='["form-control","form-control custom-select","d-none","form-control","form-control","d-none"]' data-sort='[[1,1]]'>
						<thead>
							<tr>
								<th class="border-0">Title</th>
								<th class="border-0 filter-select filter-exact" data-placeholder="All">Status</th>
								<th class="border-0 sorter-false">Versions</th>
								<th class="border-0">Release Date</th>
								<th class="border-0">Active Issues</th>
								<th class="border-0" data-placeholder="All">Curator</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${queue}" var="queueItem">
							<tr>
								<td>
									<g:link class="d-inline-block text-truncate" style="width:500px" controller="${datasetTerm('item':queueItem)}" action="show" id="${queueItem.dataset.id}" params="[version: queueItem.id]">${queueItem.dataset.shortTitle ?: queueItem.dataset.title}</g:link>
								</td>
								<td>
									${queueItem.status}
								</td>
								<td>
									<div class="dropdown">
										<button class="btn btn-light dropdown-toggle btn-thin" type="button" data-toggle="dropdown">Versions</button>
										<div class="dropdown-menu">
											<g:each in="${queueItem.dataset.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}}" var="version">
												<g:link class="dropdown-item" controller="${datasetTerm('item':queueItem)}" action="show" id="${queueItem.dataset.id}" params="[version: version.id]">
													${version.status == Version.Status.PUBLIC && version.preprint ? 'PREPRINT' : version.status} - ${version.updatedDate}
												</g:link>
											</g:each>
										</div>
									</div>
								</td>
								<td>
									<g:if test="${queueItem.releaseDate}">
									<span class="<g:if test="${queueItem.status == Version.Status.APPROVED}">text-success</g:if>">
										<g:formatDate format="yyyy-MM-dd HH:mm" date="${queueItem.releaseDate}"/>
									</span>
									</g:if>
									<g:else>
									<span class="<g:if test="${queueItem.status == Version.Status.APPROVED}">text-danger</g:if>">
										Not Set
									</span>
									</g:else>
								</td>
								<td>
									<g:link class="d-inline-block" controller="${datasetTerm('item':queueItem)}" action="issues" id="${queueItem.dataset.id}">
										${queueItem.dataset.issues?.count({ it.status != Issue.Status.RESOLVED })}
									</g:link>
								</td>
								<td class="curatorItem">
									<g:if test="${queueItem.dataset.curator}">
										${queueItem.dataset.curator.username}
										<g:link class="btn btn-light btn-thin btn-square float-right" action="removeFromMyQueue" id="${queueItem.dataset.id}">-</g:link>
									</g:if>
									<g:else>
										<g:link class="btn btn-light btn-thin btn-square float-right" action="addToMyQueue" id="${queueItem.dataset.id}">+</g:link>
									</g:else>
								</td>
							</tr>
							</g:each>
						</tbody>
						<g:render template="/templates/tableFooter" model="[cols: 6]" />
					</table>
				</div>
			</div>
		</div>
	</body>
</html>