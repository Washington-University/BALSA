<p>
	<span class="attributeLabel">Total Downloads:</span>
	${downloadStats.totalDownloads}
</p>
<p>
	<span class="attributeLabel">Unique Users:</span>
	${downloadStats.uniqueUsers[0]}
</p>
<p>
	<span class="attributeLabel">Most Recent Download:</span>
	<g:formatDate date="${downloadStats.mostRecentDownload}" type="datetime" style="LONG" />
</p>
<p>
	<span class="attributeLabel">Total Data Downloaded:</span>
	<g:displaySize bytes="${downloadStats.totalDownloadSize}"/>
</p>
<div class="row">
	<div class="col-6">
		<span class="attributeLabel">Most Downloaded Scenes:</span><br>

		<table id="mostDownloadedScenesTable" class="table table-hover table-bordered">
			<thead>
			<tr>
				<th class="border-0">Downloads</th>
				<th class="border-0 sorter-false">Name</th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${downloadStats.popularScenes}" var="scene">
				<tr>
					<td style="width:100px">
						${scene.dc}
					</td>
					<td class="text-truncate" style="max-width:369px">
						<span data-toggle="popover" data-trigger="hover" data-placement="right"  data-html="true"
							  data-content="ID: ${scene.scene_line_id}<br>Scene File: ${scene.filename}<div class='scalingBox' style='width:173px;height:120px;'><img src='/scene/image/${scene?.id}'/></div>" >
							<g:link controller="scene" action="show" id="${scene.scene_line_id}">${scene.short_name ?: scene.name}</g:link>
						</span>
					</td>
				</tr>
			</g:each>
			</tbody>
		</table>
	</div>
	<div class="col-6">
		<span class="attributeLabel">Most Downloaded Files:</span><br>

		<table id="mostDownloadedFilesTable" class="table table-hover table-bordered">
			<thead>
			<tr>
				<th class="border-0">Downloads</th>
				<th class="border-0 sorter-false">Name</th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${downloadStats.popularFiles}" var="file">
				<tr>
					<td style="width:100px">
						${file.dc}
					</td>
					<td class="text-truncate" style="max-width:369px">
						<g:link controller="file" action="show" id="${file.id}">${file.filename}</g:link>
					</td>
				</tr>
			</g:each>
			</tbody>
		</table>
	</div>
</div>
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