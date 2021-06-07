<%@ page import="balsa.security.Approval" %>
<%@ page import="balsa.Dataset" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>My Download History</title>
	</head>
	<body>
		<div id="list-study" role="main">
			<br><br>
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">My Download History</div>
				<div class="btn-group btn-bar">
					<button class="btn btn-light" onclick="bootbox.confirm({size: 'small', message: 'Delete your download history?', 
						callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="anonymize"/>');} } })">
						Delete Download History
					</button>
				</div>
				<div class="card-body p-0">
					<table class="table table-hover tablesorter mb-0 filterer pager" data-cssfilter='["form-control","form-control","d-none"]' data-sort='[[0,0]]'>
						<thead>
							<tr>
								<th class="border-0">Date</th>
								<th class="border-0">Dataset</th>
								<th class="border-0 filter-false">Download Contents</th>
							</tr>
						</thead>
						<g:render template="/templates/tableFooter" model="[cols: 3]" />
						<tbody>
							<g:each in="${downloads}" var="download">
							<tr>
								<td>
									<g:formatDate format="yyyy-MM-dd HH:mm" date="${download.date}" />
								</td>
								<td class="d-inline-block text-truncate w-100">
									<g:link controller="${datasetTerm('item':download.dataset)}" action="show" id="${download.dataset.id}">${download.dataset.shortTitle ?: download.dataset.title}</g:link>
								</td>
								<td>
									<a data-toggle="modal" data-target="#ContentsModal${download.id}">Click to view</a>
									<div id="ContentsModal${download.id}" class="modal fade" role="dialog">
										<div class="modal-dialog modal-wide">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">Download Contents</h4>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												</div>
			
												<div class="modal-body vh-50 overflow-auto">
													<g:if test="${download.scenes.size()}">
													<div>
														<span class="attributeLabel">Scenes:</span><br>
														<ul>
															<g:each in="${download.scenes}" var="scene">
															<li><g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.shortName ?: scene.name}</g:link></li>
															</g:each>
														</ul>
													</div>
													</g:if>
													
													<g:if test="${download.scenes.size() && download.files.size()}">
													<br>
													</g:if>
													
													<g:if test="${download.files.size()}">
													<div>
														<span class="attributeLabel">Files:</span><br>
														<ul>
															<g:each in="${download.files}" var="file">
															<li><g:link controller="file" action="show" id="${file.id}">${file.filename}</g:link></li>
															</g:each>
														</ul>
													</div>
													</g:if>
												</div>
											</div>
										</div>
									</div>
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