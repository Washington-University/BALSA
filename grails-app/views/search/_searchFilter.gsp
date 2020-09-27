<div class="panel panel-default" style="padding:5px;margin-bottom:10px">
		<button type="button" class="close" style="color:gray;line-height:12.25px;margin-left:5px" onclick="jQuery(this).parent().remove();refreshSearchResults();"><span aria-hidden="true">&times;</span></button>
		<g:render template="/tagging/categorySelect" model="['categories':categories,'selectText':'Filter by...']" />
</div>