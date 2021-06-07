<div class="carousel-container">
	<g:set var="focusSceneIndex" value="${datasetVersion.scenes().findIndexOf { it.sceneLine == datasetVersion.focusScene }}"/>
	<g:set var="focusSceneIndex" value="${focusSceneIndex >= 0 ? focusSceneIndex : 0}"/>
	<div id="sceneCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
		<g:if test="${datasetVersion.scenes().isEmpty() && datasetVersion.linkedScenes().isEmpty()}">
		<div class="carousel-inner" role="listbox">
			<div class="scalingBox carousel-image">
				
			</div>
		</div>
		</g:if>
		<g:else>
		<div class="carousel-inner" role="listbox">
			<g:each in="${datasetVersion.scenes() + datasetVersion.linkedScenes()}" var="scene" status="index">
			<div <g:if test="${index == focusSceneIndex}">class="carousel-item active"</g:if><g:else>class="carousel-item"</g:else>>
				<g:link controller="scene" action="show" id="${scene.sceneLine.id}" >
				<div class="scalingBox carousel-image">
					<img src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
				</div>
				
				<div class="scene-caption">${scene.name}</div>
				</g:link>
			</div>
			</g:each>
		</div>
		
		<a id="carouselLeft" class="carousel-control carousel-control-prev" href="#sceneCarousel" role="button" data-slide="prev" onclick="scrollToHighlight();"></a>
		<a id="carouselRight" class="carousel-control carousel-control-next" href="#sceneCarousel" role="button" data-slide="next" onclick="scrollToHighlight();"></a>
		
		<div id="thumbnails" class="thumbnail-container">
			<g:each in="${datasetVersion.sceneFiles()}" var="sceneFile">
			<div class="carousel-sceneFile-label">
				<g:link action="show" controller="sceneFile" id="${sceneFile.id}">
					${sceneFile.label ?: sceneFile.filename}
				</g:link>
			</div>
			<g:each in="${new int[sceneFile.scenes.size() / 3]}">
			<div class="carousel-spacer">
			</div>
			</g:each>
			<g:if test="${(sceneFile.scenes.size() % 3) == 1}">
			<div class="carousel-spacer two-th"></div>
			</g:if>
			<g:if test="${(sceneFile.scenes.size() % 3) == 2}">
			<div class="carousel-spacer one-th"></div>
			</g:if>
			<div class="carousel-spacer-end">
			</div>
			</g:each>
			
			<g:if test="${datasetVersion.linkedScenes().size() > 0}">
			<div class="carousel-sceneFile-label">
				Linked Scenes
			</div>
			</g:if>
			
			<g:set var="carouselIndex" value="${0}"/>
			<div class="carousel-indicators">
				<g:each in="${datasetVersion.sceneFiles()}" var="sceneFile">
				<g:each in="${sceneFile.scenesSorted()}" var="scene">
				<div <g:if test="${carouselIndex == focusSceneIndex}">class="balsaThumbnail active"</g:if><g:else>class="balsaThumbnail"</g:else> id="thumbnail${carouselIndex}" data-target="#sceneCarousel" data-slide-to="${carouselIndex}">
					<div class="scalingBox carousel-thumbnail">	
						<img data-toggle="popover" data-trigger="hover" data-content="${scene.shortName ?: scene.name}" src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
					</div>
				</div>
				<g:set var="carouselIndex" value="${carouselIndex+1}"/>
				</g:each>
				</g:each>
				
				<g:if test="${datasetVersion.linkedScenes().size() > 0}">
				<g:each in="${datasetVersion.linkedScenes()}" var="scene">
				<div <g:if test="${carouselIndex == focusSceneIndex}">class="balsaThumbnail active"</g:if><g:else>class="balsaThumbnail"</g:else> id="thumbnail${carouselIndex}" data-target="#sceneCarousel" data-slide-to="${carouselIndex}">
					<div class="scalingBox carousel-thumbnail">			
						<img data-toggle="popover" data-trigger="hover" data-content="${scene.shortName ?: scene.name}" src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
					</div>
				</div>
				<g:set var="carouselIndex" value="${carouselIndex+1}"/>
				</g:each>
				</g:if>
			</div>
		</div>
		</g:else>
	</div>
</div>