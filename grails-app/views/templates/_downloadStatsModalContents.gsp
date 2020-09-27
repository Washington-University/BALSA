<p>
	<span class="attributeLabel">Total Downloads:</span><br>
	${downloadStats.totalDownloads}
</p>
<p>
	<span class="attributeLabel">Unique Users:</span><br>
	${downloadStats.uniqueUsers[0]} <br>
</p>
<p>
	<span class="attributeLabel">Unique Download IPs:</span><br>
	${downloadStats.uniqueIPs[0]} <br>
</p>
<p>
	<span class="attributeLabel">Most Recent Download:</span><br>
	<g:formatDate date="${downloadStats.mostRecentDownload}" type="datetime" style="LONG" />
</p>
<p>
	<span class="attributeLabel">Total Data Downloaded:</span><br>
	<g:displaySize bytes="${downloadStats.totalDownloadSize}"/>
</p>
<div style="margin-bottom: 10px">
	<span class="attributeLabel">Most Downloaded Scenes:</span><br>
	
	<div style="overflow-y:scroll;height: 187px;">
		<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
			<thead>
				<tr>
					<th style="width: 100px">Downloads&nbsp;</th>
					<th style="width: 850px">Name&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${downloadStats.popularScenes}" var="scene">
				<tr>
					<td>
						${scene.downloads.size()}
					</td>
					<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
						<span data-toggle="tooltip" data-html="true" data-placement="right" 
							title="ID: ${scene.id}<br>Scene File: ${scene.sceneFile.filename}<div class='scalingBox' style='width:173px;height:120px;'><img src='/scene/image/${scene?.id}'/></div>" >
							<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.shortName ?: scene.name}</g:link>
						</span>
					</td>
				</tr>
				</g:each>
			</tbody>
		</table>
	</div>
</div>
<div style="margin-bottom: 10px">
	<span class="attributeLabel">Most Downloaded Scene Files:</span><br>
	<div style="overflow-y:scroll;height: 187px;">
		<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
			<thead>
				<tr>
					<th style="width: 100px">Downloads&nbsp;</th>
					<th style="width: 750px">Name&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${downloadStats.popularSceneFiles}" var="file">
				<tr>
					<td>
						${file.downloads.size()}
					</td>
					<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
						<span  data-toggle="tooltip" title="ID: ${file.id}<br>File Path: ${file.filepath.replace('/',' / ')}" data-placement="top" data-html="true" 
							data-template="<div class='tooltip' role='tooltip'><div class='tooltip-arrow'></div><div class='tooltip-inner' style='white-space:nowrap;max-width:2000px;'></div></div>">
							<g:link controller="sceneFile" action="show" id="${file.id}">${file.filename}</g:link>
						</span>
					</td>
				</tr>
				</g:each>
			</tbody>
		</table>
	</div>
</div>
<div>
	<span class="attributeLabel">Most Downloaded Files:</span><br>
	<div style="overflow-y:scroll;height: 187px;">
		<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
			<thead>
				<tr>
					<th style="width: 100px">Downloads&nbsp;</th>
					<th style="width: 100px">Type&nbsp;</th>
					<th style="width: 750px">Name&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${downloadStats.popularFiles}" var="file">
				<tr>
					<td>
						${file.downloads.size()}
					</td>
					<td>
						${shortFileTerm('filename':file.filename)}
					</td>
					<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
						<span  data-toggle="tooltip" title="ID: ${file.id}<br>File Path: ${file.filepath.replace('/',' / ')}" data-placement="top" data-html="true" 
							data-template="<div class='tooltip' role='tooltip'><div class='tooltip-arrow'></div><div class='tooltip-inner' style='white-space:nowrap;max-width:2000px;'></div></div>">
							<g:link controller="file" action="show" id="${file.id}">${file.filename}</g:link>
						</span>
					</td>
				</tr>
				</g:each>
			</tbody>
		</table>
	</div>
</div>
<script>
	$('[data-toggle="tooltip"]').tooltip();
</script>