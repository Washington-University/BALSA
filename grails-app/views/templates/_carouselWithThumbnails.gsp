<div class="carousel-container">
	<g:set var="focusSceneIndex" value="${datasetVersion.scenes().findIndexOf { it.sceneLine == datasetVersion.focusScene }}"/>
	<g:set var="focusSceneIndex" value="${focusSceneIndex >= 0 ? focusSceneIndex : 0}"/>
	<div id="sceneCarousel" class="carousel slide" data-ride="carousel" data-interval="" style="margin-right:-5px">
		<g:if test="${datasetVersion.scenes().isEmpty() && datasetVersion.linkedScenes().isEmpty()}">
		<div class="carousel-inner" role="listbox">
			<div class="scalingBox" style="width:580px;height:380px;">
				
			</div>
		</div>
		</g:if>
		<g:else>
		<div class="carousel-inner" role="listbox">
			<g:each in="${datasetVersion.scenes() + datasetVersion.linkedScenes()}" var="scene" status="index">
			<div <g:if test="${index == focusSceneIndex}">class="item active"</g:if><g:else>class="item"</g:else>>
				<g:link controller="scene" action="show" id="${scene.sceneLine.id}" >
				<div class="scalingBox" style="width:580px;height:380px;">
					<img src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
				</div>
				
				<div class="scene-caption">${scene.name}</div>
				</g:link>
			</div>
			</g:each>
		</div>
		
		<a id="carouselLeft" class="left carousel-control" href="#sceneCarousel" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-triangle-left" aria-hidden="true"></span>
		</a>
		<a id="carouselRight" class="right carousel-control" href="#sceneCarousel" role="button" data-slide="next">
			<span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>
		</a>
		
		<div id="thumbnails" class="thumbnail-container">
			<g:set var="carouselIndex" value="${0}"/>
			<g:each in="${datasetVersion.sceneFiles()}" var="sceneFile">
			<div style="padding-top:5px;">
				<g:link action="show" controller="sceneFile" id="${sceneFile.id}">
					${sceneFile.label ?: sceneFile.filename}
				</g:link>
			</div>
			<div class="carousel-indicators">
				<g:each in="${sceneFile.scenesSorted()}" var="scene">
	
				<div <g:if test="${carouselIndex == focusSceneIndex}">class="balsaThumbnail active"</g:if><g:else>class="balsaThumbnail"</g:else> id="thumbnail${carouselIndex}" data-target="#sceneCarousel" data-slide-to="${carouselIndex}" onclick="toggleHighlight(this.id)">
					<div class="scalingBox" style="width:173px;height:120px;">			
						<img data-toggle="tooltip" title="${scene.shortName ?: scene.name}" src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
					</div>
				</div>
				
				<g:set var="carouselIndex" value="${carouselIndex+1}"/>
	
				</g:each>
			</div>
			</g:each>
			
			<g:if test="${datasetVersion.linkedScenes().size() > 0}">
			<div style="padding-top:5px;">
				Linked Scenes
			</div>
			<div class="carousel-indicators">
				<g:each in="${datasetVersion.linkedScenes()}" var="scene">
	
				<div <g:if test="${carouselIndex == focusSceneIndex}">class="balsaThumbnail active"</g:if><g:else>class="balsaThumbnail"</g:else> id="thumbnail${carouselIndex}" data-target="#sceneCarousel" data-slide-to="${carouselIndex}" onclick="toggleHighlight(this.id)">
					<div class="scalingBox" style="width:173px;height:120px;">			
						<img data-toggle="tooltip" title="${scene.shortName ?: scene.name}" src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
					</div>
				</div>
				
				<g:set var="carouselIndex" value="${carouselIndex+1}"/>
	
				</g:each>
			</div>
			</g:if>
		</div>
		
		<script type="text/javascript">
			$('.carousel-control').click(function() {scrollToHighlight();});

			function scrollToHighlight() {
				window.setTimeout(
					function() {
						var container = $('#thumbnails');
						var currentScroll = container.scrollTop();
						var containerTop = container.offset().top;
						var containerBottom = containerTop + container.height();
						
						var elem = $('.carousel-indicators .active');
						var elemTop = elem.offset().top -25;
						var elemBottom = elemTop + elem.height() + 45;

						if (elemTop < containerTop) {
							container.animate({
								scrollTop: currentScroll - containerTop + elemTop
							}, 600);
						}
						
						if (elemBottom > containerBottom) {
							container.animate({
								scrollTop: currentScroll - containerBottom + elemBottom
							}, 600);
						}
						
					}, 0);
			}

			scrollToHighlight();
		</script>
		</g:else>
	</div>
</div>