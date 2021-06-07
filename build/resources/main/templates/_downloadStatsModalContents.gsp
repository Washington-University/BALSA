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
<p>
	<span class="attributeLabel">Most Downloaded Scenes:</span><br>
	
	<table id="mostDownloadedScenesTable" class="table table-hover tablesorter table-bordered pager" data-cssfilter='["d-none","d-none"]' data-rows="10">
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
					${scene.downloads.size()}
				</td>
				<td class="text-truncate">
					<span data-toggle="popover" data-trigger="hover" data-placement="right"  data-html="true" 
						data-content="ID: ${scene.id}<br>Scene File: ${scene.sceneFile.filename}<div class='scalingBox' style='width:173px;height:120px;'><img src='/scene/image/${scene?.id}'/></div>" >
						<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.shortName ?: scene.name}</g:link>
					</span>
				</td>
			</tr>
			</g:each>
		</tbody>
		<g:render template="/templates/tableFooter" model="[cols: 2]" />
	</table>
</p>
