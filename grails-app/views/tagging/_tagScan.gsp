<g:each in="${tags}">
<div class="well" style="margin-bottom:5px;padding:5px;display:inline-block">
	<g:field type="hidden" name="tags" value="${it}" />
	${it}
	<button type="button" class="close" style="color:gray;line-height:12.25px;margin-left:5px" onclick="jQuery(this).parent().remove()"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	<button type="button" class="close" style="color:gray;line-height:12.25px;margin-left:5px" onclick="jQuery('#tagCollection').append(jQuery(this).parent());jQuery(this).remove()"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span></button>
</div>
</g:each>