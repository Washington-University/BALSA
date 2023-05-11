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
										${scene.dc}
									</td>
									<td>
										<span data-toggle="popover" data-trigger="hover" data-html="true" data-content="<img width='250' src='/scene/image/${scene.id}'/>">
											<g:link controller="scene" action="show" id="${scene.scene_line_id}">${scene.short_name ?: scene.name}</g:link>
										</span>
									</td>
									<td>
										<g:link action="show" controller="sceneFile" id="${scene.scene_file_id}">${scene.filename}</g:link>
									</td>
									<td>
										<g:link action="show" controller="${scene.dclass == 'balsa.Study' ? 'study' : 'reference'}" id="${scene.dataset_id}">${scene.short_title ?: scene.title}</g:link>
									</td>
								</tr>
								</g:each>
							</tbody>
							<g:render template="/templates/tableFooter" model="[cols: 4]" />
						</table>
					</p>
					<p>
						<span class="attributeLabel">Most Downloaded Files:</span><br>
						<table id="mostDownloadedFilesTable" class="table table-hover tablesorter table-bordered filterer pager" data-cssfilter='["d-none","form-control","form-control","form-control"]' data-rows="10">
							<thead>
							<tr>
								<th class="border-0 sorter-false">Downloads&nbsp;</th>
								<th class="border-0 sorter-false">Name&nbsp;</th>
								<th class="border-0 sorter-false">Dataset&nbsp;</th>
							</tr>
							</thead>
							<tbody>
							<g:each in="${downloadStats.popularFiles}" var="file">
								<tr>
									<td style="width:100px">
										${file.dc}
									</td>
									<td>
										<g:link controller="file" action="show" id="${file.id}">${file.filename}</g:link>
									</td>
									<td>
										<g:link action="show" controller="${file.dclass == 'balsa.Study' ? 'study' : 'reference'}" id="${file.dataset_id}">${file.short_title ?: file.title}</g:link>
									</td>
								</tr>
							</g:each>
							</tbody>
							<g:render template="/templates/tableFooter" model="[cols: 4]" />
						</table>
					</p>
					<p>
						<span class="attributeLabel">Downloads By Month:</span>
						<canvas id="downloadChart"></canvas>
					</p>
					<script>
						<g:applyCodec encodeAs="none">
						let chartdata = ${downloadStats.downloadsByMonth};
						</g:applyCodec>
						let downloadChart = new Chart($('#downloadChart')[0].getContext('2d'),
								{type: 'bar',
									data: { labels: chartdata.map(row => row.month),
										datasets: [{data: chartdata.map(row => row.count),
											borderColor: '#3080d0',
											backgroundColor: '#3080d0'}] },
									options: {legend:{display: false}, scales:{xAxes:[{gridLines:{display: false}}]}}});
					</script>
				</div>
			</div>
		</div>
	</body>
</html>