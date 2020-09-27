<div class="carousel-container">
	<g:set var="focusSceneIndex" value="${sceneFile.scenesSorted().findIndexOf { it == focusScene }}"/>
	<g:set var="focusSceneIndex" value="${focusSceneIndex >= 0 ? focusSceneIndex : 0}"/>
	<div id="sceneCarousel" class="carousel slide" data-ride="carousel" data-interval="" style="margin-right:-5px">

		<div class="carousel-inner" role="listbox">
			<g:each in="${sceneFile.scenesSorted()}" var="scene" status="index">
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
			<div  class="carousel-indicators">
				<g:each in="${sceneFile.scenesSorted()}" var="scene" status="index">
				<div <g:if test="${index == focusSceneIndex}">class="balsaThumbnail active"</g:if><g:else>class="balsaThumbnail"</g:else> id="thumbnail${index}" data-target="#sceneCarousel" data-slide-to="${index}" style="display:inline-block;padding:5px;" onclick="toggleHighlight(this.id)">
					<div class="scalingBox" style="width:173px;height:120px">			
						<img  data-toggle="tooltip" title="${scene.shortName ?: scene.name}" src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
					</div>
				</div>
				</g:each>
			</div>
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
		</script>
	</div>
</div>