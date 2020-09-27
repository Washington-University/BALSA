<g:set var="thumbnailSize" value="${820/params.columns.toInteger()}" />
<g:set var="datasetVersion" value="${dataset.publicVersion() ?: dataset.preprintVersion()}" />
<div class="balsaThumbnail" style="width:${thumbnailSize}px">
	<div class="well <g:datasetTerm item="${datasetVersion}"/>" style="margin-bottom:0">
		<g:link action="show" controller="${datasetTerm('item':datasetVersion)}" id="${dataset.id}" params="${ datasetVersion.preprint ? "[version: 'preprint']" : "[]" }">
			<div style="height:3em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:3;-webkit-box-orient: vertical;">
				${datasetTermUppercase('item':datasetVersion)}: ${dataset.shortTitle ?: dataset.title}
			</div>
		</g:link>
		
		<div style="height:.5em"></div>
		
		<div id="sceneCarousel${datasetVersion.id}" class="carousel slide" data-ride="carousel" data-interval="" style="padding:0">
			<div class="carousel-inner" role="listbox">
				<g:set var="focusSceneIndex" value="${datasetVersion.scenes().findIndexOf { it.sceneLine == datasetVersion.focusScene }}"/>
				<g:set var="focusSceneIndex" value="${focusSceneIndex >= 0 ? focusSceneIndex : 0}"/>
				<script>
					var index${datasetVersion.id} = ${focusSceneIndex};
					var lastIndex${datasetVersion.id} = ${datasetVersion.scenes().size() + datasetVersion.linkedScenes().size() - 1};
					loadSlideImage${datasetVersion.id}();
					
					function loadPrevSlideImage${datasetVersion.id}() {
						index${datasetVersion.id}--;
						if (index${datasetVersion.id} < 0) { index${datasetVersion.id} = lastIndex${datasetVersion.id} }
						loadSlideImage${datasetVersion.id}();
					}
					
					function loadNextSlideImage${datasetVersion.id}() {
						index${datasetVersion.id}++;
						if (index${datasetVersion.id} > lastIndex${datasetVersion.id}) { index${datasetVersion.id} = 0 }
						loadSlideImage${datasetVersion.id}();
					}

					function loadSlideImage${datasetVersion.id}() {
						var box = $('#${datasetVersion.id}scene' + index${datasetVersion.id} + ' .scalingBox');
						box.html('<img src="/scene/image/'+box.attr('sceneImageId')+'">');
					}
				</script>
				
				<g:each in="${datasetVersion.scenes() + datasetVersion.linkedScenes()}" var="scene" status="index">
				<div id="${datasetVersion.id}scene${index}" <g:if test="${index == focusSceneIndex}">class="item active"</g:if><g:else>class="item"</g:else>>
					<div class="sceneFileAbove" <g:if test="${params.sceneFileVisibility=='Hide'}">style="display:none"</g:if>>
						<g:link class="sceneFileAbove" action="show" controller="sceneFile" id="${scene.sceneFile.id}" >
							<div style="height:1em;line-height:1em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient: vertical;word-break:break-all;">
								Scene File: ${scene.sceneFile.label ?: scene.sceneFile.filename}
							</div>
						</g:link>
						<div style="height:.5em"></div>
					</div>
					
					<g:link controller="scene" action="show" id="${scene.sceneLine.id}" style="display:inline-block;">
					<div sceneImageId="${scene?.id}" class="scalingBox" style="width:${thumbnailSize-40}px;height:${(thumbnailSize-40)*0.65}px;">
					</div>
					
					<div class="scene-caption">
						<div style="line-height:1em;height:2em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient: vertical;">
							${scene.shortName ?: scene.name}
						</div>
					</div>
					</g:link>
				</div>
				</g:each>
			</div>
			
			<a class="left carousel-control" href="#sceneCarousel${datasetVersion.id}" role="button" data-slide="prev" onclick="loadPrevSlideImage${datasetVersion.id}()">
				<span class="glyphicon glyphicon-triangle-left" aria-hidden="true" style="top:${(thumbnailSize-40)*0.325}px"></span>
			</a>
			<a class="right carousel-control" href="#sceneCarousel${datasetVersion.id}" role="button" data-slide="next" onclick="loadNextSlideImage${datasetVersion.id}()">
				<span class="glyphicon glyphicon-triangle-right" aria-hidden="true" style="top:${(thumbnailSize-40)*0.325}px"></span>
			</a>
		</div>
	</div>
</div>