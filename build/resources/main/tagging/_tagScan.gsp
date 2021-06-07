<g:each in="${tags}">
<div class="card" style="margin-bottom:5px;padding:5px;display:inline-block">
	<div class="card-body">
		<g:field type="hidden" name="tags" value="${it}" />
		${it}
		<button type="button" class="close" style="color:gray;line-height:12.25px;margin-left:5px" onclick="jQuery(this).parent().remove()"><span aria-hidden="true">&times;</span></button>
		<button type="button" class="close" style="color:gray;line-height:12.25px;margin-left:5px" onclick="jQuery('#tagCollection').append(jQuery(this).parent());jQuery(this).remove()">&#8679;</button>
	</div>
</div>
</g:each>