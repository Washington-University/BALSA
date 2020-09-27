<g:set var="thumbnailSize" value="${820/params.columns.toInteger()}" />
<div class="balsaThumbnail" style="width:${thumbnailSize}px">
	<div class="well <g:datasetTerm item="${scene}"/>" style="margin-bottom:0">
			
		<g:link action="show" controller="${datasetTerm('item':scene)}" id="${scene.sceneFile.dataset.id}">
			<div style="height:3em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:3;-webkit-box-orient: vertical;">
				${datasetTermUppercase('item':scene)}: ${scene.sceneFile.dataset.shortTitle ?: scene.sceneFile.dataset.title}
			</div>
		</g:link>
		<div style="height:.5em"></div>
		
		<div class="sceneFileAbove" style="display:none">
			<g:link class="sceneFileAbove" action="show" controller="sceneFile" id="${scene.sceneFile.id}" style="display:none">
				<div style="height:1em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient: vertical;word-break:break-all;">
					Scene File: ${scene.sceneFile.filename}
				</div>
			</g:link>
			<div style="height:.5em"></div>
		</div>

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