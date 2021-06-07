<input type="hidden" id="index${versionInstance.id}" value="${versionInstance.focusSceneIndex()}">
<input type="hidden" id="lastIndex${versionInstance.id}" value="${versionInstance.scenes().size() + versionInstance.linkedScenes().size() - 1}">

<g:each in="${versionInstance.scenes() + versionInstance.linkedScenes()}" var="scene" status="index">
<div id="${versionInstance.id}scene${index}" <g:if test="${index == versionInstance.focusSceneIndex()}">class="carousel-item active"</g:if><g:else>class="carousel-item"</g:else>>
	<div class="sceneFileAbove collapse" <g:if test="${params.sceneFileVisibility=='Hide'}"></g:if>>
		<g:link action="show" controller="sceneFile" id="${scene.sceneFile.id}" >
			<div class="thumbnail-sceneFile-name">
				Scene File: ${scene.sceneFile.label ?: scene.sceneFile.filename}
			</div>
		</g:link>
		<div style="height:.5em"></div>
	</div>

	<g:link controller="scene" action="show" id="${scene.sceneLine.id}" style="display:inline-block;">
	<div sceneImageId="${scene?.id}" id="${versionInstance.id}scenePreview${index}" class="scalingBox" style="width:${thumbnailSize-42}px;height:${(thumbnailSize-42)*0.65}px;">
	</div>

	<div class="scene-caption">
		<div>
			${scene.shortName ?: scene.name}
		</div>
	</div>
	</g:link>
</div>
</g:each>