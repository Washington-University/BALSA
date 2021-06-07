<div class="balsaThumbnail" style="width:${thumbnailSize}px">
	<div class="card <g:datasetTerm item="${scene}"/>">
		<div class="card-header">
			<g:link action="show" controller="${datasetTerm('item':scene)}" id="${scene.sceneFile.dataset.id}">
				<div style="height:3em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:3;-webkit-box-orient: vertical;">
					${datasetTermUppercase('item':scene)}: ${scene.sceneFile.dataset.shortTitle ?: scene.sceneFile.dataset.title}
				</div>
			</g:link>
		</div>
		
		<div class="card-body">
			<div class="sceneFileAbove collapse">
				<g:link action="show" controller="sceneFile" id="${scene.sceneFile.id}">
					<div class="thumbnail-sceneFile-name">
						Scene File: ${scene.sceneFile.filename}
					</div>
				</g:link>
				<div style="height:.5em"></div>
			</div>

			<g:link controller="scene" action="show" id="${scene.sceneLine.id}" >
			<div class="scalingBox" style="width:${thumbnailSize - 42}px;height:${(thumbnailSize - 42) * 0.65}px;">
				<img src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
			</div>

			<div class="scene-caption">
				<div>
					${scene.shortName ?: scene.name}
				</div>
			</div>
			</g:link>
		</div>
	</div>
</div>