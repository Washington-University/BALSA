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
			<h3>My Download History</h3>
			
			<div class="btn-group">
				<button class="btn btn-default" onclick="bootbox.confirm({size: 'small', message: 'Delete your download history?', 
					callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="anonymize"/>');} } })">
					Delete Download History
				</button>
			</div>
			
			<br><br>
			<div class="well" style="overflow: hidden;">
				<table class="table table-striped table-bordered" id="myStudiesTable" style="table-layout: fixed; margin-bottom: 0px">
					<thead>
						<tr>
							<th style="width: 130px">
								Date
							</th>
							<th>
								Dataset
							</th>
							<th style="width: 150px">
								Download Contents
							</th>
						</tr>
					</thead>
					
					<tbody>
						<g:each in="${downloads}" var="download">
						<tr>
							<td style="border-top:1px solid rgb(190,192,218)">
								<g:formatDate format="yyyy-MM-dd HH:mm" date="${download.date}" />
							</td>
							<td style="border-top:1px solid rgb(190,192,218);white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
								<g:link controller="${datasetTerm('item':download.dataset)}" action="show" id="${download.dataset.id}">${download.dataset.shortTitle ?: download.dataset.title}</g:link>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<a data-toggle="modal" data-target="#${download.id}ContentsModal">Click to view</a>
								<div id="${download.id}ContentsModal" class="modal fade" role="dialog">
									<div class="modal-dialog" style="width:1000px">
										<div class="modal-content">
											<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												<h4 class="modal-title">Download Contents</h4>
											</div>
											<div class="modal-body" style="overflow-y:scroll;height: 750px;">
												<g:if test="${download.scenes.size()}">
												<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
													<thead>
														<tr>
															<th>
																Scenes
															</th>
														</tr>
													</thead>
													<tbody>
														<g:each in="${download.scenes}" var="scene">
														<tr>
															<td style="border-top:1px solid rgb(190,192,218);white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
																<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.shortName ?: scene.name}</g:link>
															</td>
														</tr>
														</g:each>
													</tbody>
												</table>
												</g:if>
												<g:if test="${download.scenes.size() && download.files.size()}">
												<br>
												</g:if>
												<g:if test="${download.files.size()}">
												<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
													<thead>
														<tr>
															<th>
																Files
															</th>
														</tr>
													</thead>
													<tbody>
														<g:each in="${download.files}" var="file">
														<tr>
															<td style="border-top:1px solid rgb(190,192,218);white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
																<g:link controller="file" action="show" id="${file.id}">${file.filename}</g:link>
															</td>
														</tr>
														</g:each>
													</tbody>
												</table>
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
	</body>
</html>