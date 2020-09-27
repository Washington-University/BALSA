<div style="overflow:hidden">
	<select class="form-control" id="categorySelect" style="float:left;width:auto" name="categorySelect" 
		onchange="jQuery(this).next().load('/tagging/categoryOptions',{category:this.value},function(){jQuery(this).children().first().focus();});">
		<option value="" selected>${selectText}</option>
		<g:each in="${categories}">
		<option value="${it.name}" title="${it.description}">${it.name}</option>
		</g:each>
	</select>
	<div style="display:block;overflow:hidden;padding-left:5px;position:relative;line-height:34px"></div>
</div>