<%@ page import="balsa.scene.Scene" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Download Statistics</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Download Statistics</div>

				<div class="card-body">
					<p>
						<span class="attributeLabel">Total Downloads:</span><br>
						${downloadStats.totalDownloads} <br>
					</p>
					<p>
						<span class="attributeLabel">Unique Users:</span><br>
						${downloadStats.uniqueUsers[0]} <br>
					</p>
					<p>
						<span class="attributeLabel">Most Recent Download:</span><br>
						<g:formatDate date="${downloadStats.mostRecentDownload}" type="datetime" style="LONG" />
					</p>
					<p>
						<span class="attributeLabel">Total Data Downloaded:</span><br>
						<g:displaySize bytes="${downloadStats.totalDownloadSize}"/> <br>
					</p>
					<p>
						<span class="attributeLabel">Most Downloaded Datasets:</span><br>
						<table id="mostDownloadedDatasetsTable" class="table table-hover tablesorter table-bordered filterer pager" data-cssfilter='["d-none","form-control custom-select","form-control"]' data-rows="10">
							<thead>
								<tr>
									<th class="border-0 sorter-false">Downloads&nbsp;</th>
									<th class="border-0 sorter-false filter-select filter-exact" data-placeholder="All">Type&nbsp;</th>
									<th class="border-0 sorter-false">Title&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${downloadStats.popularDatasets}" var="dataset">
								<tr>
									<td style="width:100px">
										${dataset.downloads.size()}
									</td>
									<td>
										${datasetTermUppercase('item':dataset)}
									</td>
									<td>
										<g:link action="show" controller="${datasetTerm('item':dataset)}" id="${dataset.id}">${dataset.shortTitle ?: dataset.title}</g:link>
									</td>
								</tr>
								</g:each>
							</tbody>
							<g:render template="/templates/tableFooter" model="[cols: 3]" />
						</table>
					</p>
					<p>
						<span class="attributeLabel">Most Downloaded Scenes:</span><br>
						<table id="mostDownloadedScenesTable" class="table table-hover tablesorter table-bordered filterer pager" data-cssfilter='["d-none","form-control","form-control","form-control"]' data-rows="10">
							<thead>
								<tr>
									<th class="border-0 sorter-false">Downloads&nbsp;</th>
									<th class="border-0 sorter-false">Name&nbsp;</th>
									<th class="border-0 sorter-false">Scene File&nbsp;</th>
									<th class="border-0 sorter-false">Dataset&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${downloadStats.popularScenes}" var="scene">
								<tr>
									<td style="width:100px">
										${scene.downloads.size()}
									</td>
									<td>
										<span data-toggle="popover" data-trigger="hover" data-html="true" data-content="<img width='250' src='/scene/image/${scene?.id}'/>">
											<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.shortName ?: scene.name}</g:link>
										</span>
									</td>
									<td>
										<g:link action="show" controller="sceneFile" id="${scene.sceneFile.id}">${scene.sceneFile.filename}</g:link>
									</td>
									<td>
										<g:link action="show" controller="${datasetTerm('item':scene)}" id="${scene.sceneFile.dataset.id}">${scene.sceneFile.dataset.shortTitle ?: scene.sceneFile.dataset.title}</g:link>
									</td>
								</tr>
								</g:each>
							</tbody>
							<g:render template="/templates/tableFooter" model="[cols: 4]" />
						</table>
					</p>
				</div>
			</div>
		</div>
	</body>
</html>