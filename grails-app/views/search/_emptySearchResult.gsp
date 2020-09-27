<g:set var="thumbnailSize" value="${820/params.columns.toInteger()}" />
<div class="balsaThumbnail" style="width:${thumbnailSize}px;cursor:auto">
	<div style="padding:15px;margin-bottom:0">
			
		<div style="height:3em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:3;-webkit-box-orient: vertical;">
		</div>
		<div style="height:.5em"></div>
		
		<div class="sceneFileAbove" style="display:none">
			<div style="height:1em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient: vertical;word-break:break-all;">
			</div>
			<div style="height:.5em"></div>
		</div>

		<div style="width:${thumbnailSize-40}px;height:${(thumbnailSize-40)*0.65}px;">
		</div>
		
		<div style="padding:5px;border:1px solid white">
			<div style="line-height:1em;height:2em;overflow:hidden;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient: vertical;">
			</div>
		</div>
	</div>
</div>