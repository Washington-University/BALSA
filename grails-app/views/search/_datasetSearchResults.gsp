<div style="margin: -5px -5px 5px;font-size:0" onload="changeSceneFileVisibility();">
<g:set var="thumbnailSize" value="${1143/(params.columns?.toInteger() ?: 4)}" />

<g:each in="${searchResults.datasets}" var="dataset">
<g:set var="datasetVersion" value="${dataset.publicVersion() ?: dataset.preprintVersion()}" />
<div class="balsaThumbnail" style="width:${thumbnailSize}px">
	<div class="card <g:datasetTerm item="${datasetVersion}"/>">
		<div class="card-header">
			<g:link action="show" controller="${datasetTerm('item':datasetVersion)}" id="${datasetVersion.dataset.id}" params="${ datasetVersion.preprint ? "[version: 'preprint']" : "[]" }">
				<div style="height:3em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:3;-webkit-box-orient: vertical;">
					${datasetTermUppercase('item':datasetVersion)}: ${dataset.shortTitle ?: dataset.title}
				</div>
			</g:link>
		</div>
		
		<div class="card-body">
			<div id="sceneCarousel${datasetVersion.id}" class="carousel slide p-0" data-ride="carousel" data-interval="false">
				<input type="hidden" class="carouselToPopulate" data-datasetId="${datasetVersion.id}" data-thumbnailSize="${thumbnailSize}"/>
				<div class="carousel-inner" role="listbox" id="innerCarousel${datasetVersion.id}">
					<div class="sceneFileAbove">
						<div style="height:1em;line-height:1em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient: vertical;word-break:break-all;">
						</div>
						<div style="height:.5em"></div>
					</div>

					<div class="scalingBox" style="width:${thumbnailSize - 42}px;height:${(thumbnailSize - 42) * 0.65}px;">
						<g:img file="loading.gif"/>
					</div>

					<div class="scene-caption">
						<div style="line-height:1em;height:2em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient: vertical;">
						</div>
					</div>

				</div>

				<a class="carousel-control carousel-control-prev" style="top:${(thumbnailSize-40)*0.325}px" href="#sceneCarousel${datasetVersion.id}" role="button" data-slide="prev" onclick="loadPrevSlideImage('${datasetVersion.id}')"></a>
				<a class="carousel-control carousel-control-next" style="top:${(thumbnailSize-40)*0.325}px" href="#sceneCarousel${datasetVersion.id}" role="button" data-slide="next" onclick="loadNextSlideImage('${datasetVersion.id}')"></a>
			</div>
		</div>
	</div>
</div>
</g:each>

<g:if test="${searchResults.datasets.size() < params.max}">
<g:each in="${(searchResults.datasets.size() .. (params.max - 1))}" status="i" var="thing">
	<g:render template="emptySearchResult" />
</g:each>
</g:if>
</div>

<div class="balsaPaginate">
<util:remotePaginate name="thing" controller="search" action="datasetSearch" total="${searchResults.totalCount}" params="${params}" update="datasetSearchResults" prev="&lt;" next="&gt;" onSuccess="populateCarousel();updateRecentActivity();changeSceneFileVisibility();"/> 
</div>