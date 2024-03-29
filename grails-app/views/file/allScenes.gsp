<%@ page import="balsa.scene.Scene" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Scenes</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">All scenes containing file <g:fieldValue bean="${file}" field="filename"/></div>
				<div class="btn-group btn-bar">
					<g:link class="btn btn-light" action="show" resource="${file}">Return to file details</g:link>
				</div>
				<div class="card-body">
					<div style="font-size:0;margin:-5px">
					<g:each in="${scenes}" var="scene">
						<g:set var="thumbnailSize" value="${1538/5}" />
						<div class="balsaThumbnail" style="width:${thumbnailSize}px">
							<div class="card <g:datasetTerm item="${scene}"/>" style="margin-bottom:0">
								<div class="card-body">
									<g:link action="show" controller="${datasetTerm('item':scene)}" id="${scene.sceneFile.dataset.id}">
										<div style="height:3em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:3;-webkit-box-orient: vertical;">
											${datasetTermUppercase('item':scene)}: ${scene.sceneFile.dataset.shortTitle ?: scene.sceneFile.dataset.title}
										</div>
									</g:link>

									<div style="height:.5em"></div>

									<g:link controller="scene" action="show" id="${scene.sceneLine.id}" >
									<div class="scalingBox" style="width:${thumbnailSize-40}px;height:${(thumbnailSize-40)*0.65}px;">
										<img src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
									</div>

									<div class="scene-caption">
										<div style="line-height:1em;height:2em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient: vertical;">
											${scene.shortName ?: scene.name}
										</div>
									</div>
									</g:link>
								</div>
							</div>
						</div>
					</g:each>
					</div>
				</div>
			</div>
		<br><br>
		</div>
	</body>
</html>