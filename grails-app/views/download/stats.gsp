<%@ page import="balsa.scene.Scene" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Download Statistics</title>
	</head>
	<body>
		<div role="main">
			<h3>Download Statistics</h3>
			
			<div class="well" style="overflow: hidden;">
				<p>
					<span class="attributeLabel">Total Downloads:</span><br>
					${downloadStats.totalDownloads} <br>
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
					<g:displaySize bytes="${downloadStats.totalDownloadSize}"/> <br>
				</p>
				<div style="margin-bottom:10px">
					<span class="attributeLabel">Most Downloaded Datasets:</span><br>
					<div style="overflow-y:scroll;height: 300px;background-color: white">
						<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
							<thead>
								<tr>
									<th style="width: 100px">Downloads&nbsp;</th>
									<th style="width: 100px">Type&nbsp;</th>
									<th style="width: 900px">Title&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${downloadStats.popularDatasets}" var="dataset">
								<tr>
									<td>
										${dataset.downloads.size()}
									</td>
									<td>
										${datasetTermUppercase('item':dataset)}
									</td>
									<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
										<span data-toggle="tooltip" data-html="true" data-placement="right"
											title="ID: ${dataset.id}" >
											<g:link action="show" controller="${datasetTerm('item':dataset)}" id="${dataset.id}">${dataset.shortTitle ?: dataset.title}</g:link>
										</span>
									</td>
								</tr>
								</g:each>
							</tbody>
						</table>
					</div>
				</div>
				<div style="margin-bottom:10px">
					<span class="attributeLabel">Most Downloaded Scenes:</span><br>
					<div style="overflow-y:scroll;height: 300px;background-color: white">
						<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
							<thead>
								<tr>
									<th style="width: 100px">Downloads&nbsp;</th>
									<th style="width: 1000px">Name&nbsp;</th>
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
											title="ID: ${scene.id}<br>${datasetTermUppercase('item':scene)}: ${scene.sceneLine.dataset.shortTitle ?: scene.sceneLine.dataset.title}<br>${datasetTermUppercase('item':scene)} ID: ${scene.sceneLine.dataset.id}<br>Scene File: ${scene.sceneFile.filename}<div class='scalingBox' style='width:173px;height:120px;'><img src='/scene/image/${scene?.id}'/></div>" >
											<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.shortName ?: scene.name}</g:link>
										</span>
									</td>
								</tr>
								</g:each>
							</tbody>
						</table>
					</div>
				</div>
				<div style="margin-bottom:10px">
					<span class="attributeLabel">Most Downloaded Scene Files:</span><br>
					<div style="overflow-y:scroll;height: 300px;background-color: white">
						<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
							<thead>
								<tr>
									<th style="width: 100px">Downloads&nbsp;</th>
									<th style="width: 1000px">Name&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${downloadStats.popularSceneFiles}" var="file">
								<tr>
									<td>
										${file.downloads.size()}
									</td>
									<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
										<span  data-toggle="tooltip" data-html="true" title="ID: ${file.id}<br>${datasetTermUppercase('item':file)}: ${file.dataset.shortTitle ?: file.dataset.title}<br>${datasetTermUppercase('item':file)} ID: ${file.dataset.id}<br>File Path: ${file.filepath}" data-placement="top" data-template="<div class='tooltip' role='tooltip'><div class='tooltip-arrow'></div><div class='tooltip-inner' style='white-space:nowrap;max-width:2000px;'></div></div>">
											<g:link controller="sceneFile" action="show" id="${file.id}">${file.filename}</g:link>
										</span>
									</td>
								</tr>
								</g:each>
							</tbody>
						</table>
					</div>
				</div>
				<div style="margin-bottom:10px">
					<span class="attributeLabel">Most Downloaded Files:</span><br>
					<div style="overflow-y:scroll;height: 300px;background-color: white">
						<table class="table table-striped table-bordered" style="table-layout: fixed; margin-bottom: 0px">
							<thead>
								<tr>
									<th style="width: 100px">Downloads&nbsp;</th>
									<th style="width: 100px">Type&nbsp;</th>
									<th style="width: 900px">Name&nbsp;</th>
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
										<span  data-toggle="tooltip" data-html="true" title="ID: ${file.id}<br>${datasetTermUppercase('item':file)}: ${file.dataset.shortTitle ?: file.dataset.title}<br>${datasetTermUppercase('item':file)} ID: ${file.dataset.id}<br>File Path: ${file.filepath}" 
											data-placement="top" data-template="<div class='tooltip' role='tooltip'><div class='tooltip-arrow'></div><div class='tooltip-inner' 
											style='white-space:nowrap;max-width:2000px;'></div></div>">
											<g:link controller="file" action="show" id="${file.id}">${file.filename}</g:link>
										</span>
									</td>
								</tr>
								</g:each>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>