<span class="attributeLabel">NEW TAG:</span><br>
<form>
<div style="display:inline-block"><g:render template="/tagging/categorySelect" model="['categories':categories,'selectText':'Tag category']" /></div>
<button type="button" id="addTagButton" class="btn btn-default" onclick="addTags(this.form)" style="margin-top:-26px" disabled>Add Tag</button>
</form>
<script>
	function addTags (form) {
		var category = form.categorySelect.value;
		var tagOrTags = form[category];
		if (tagOrTags instanceof RadioNodeList) {
			for (i = 0; i < tagOrTags.length; i++) {
				var tag = tagOrTags.item(i);
				if (tag.checked) {
					$('#tagCollection').append($('#tagTemplate').html().replace(/TAGVALUE/g, category + ":" + tag.value));
				}
			}
		}
		else {
			$('#tagCollection').append($('#tagTemplate').html().replace(/TAGVALUE/g, category + ":" + tagOrTags.value));
		}
	}
	
	$('#categorySelect').blur(function() {enableDisableButton();});

	// category options are already bound to refreshSearchResults onchange
	function refreshSearchResults() {
		enableDisableButton();
	}

	function enableDisableButton() {
		if ($('#categorySelect').val() && $('#categoryOptions').val()) {
			$('#addTagButton').prop("disabled", false);
		}
		else {
			$('#addTagButton').prop("disabled", true);
		}
	}
	
</script>
<script id="tagTemplate" type="text/html">
	<div class="well" style="margin-bottom:5px;padding:5px;display:inline-block">
		<input type="hidden" name="tags" id="tags" value="TAGVALUE" />
		TAGVALUE
		<button type="button" class="close" style="color:gray;line-height:12.25px;margin-left:5px" onclick="jQuery(this).parent().remove()"><span aria-hidden="true">&times;</span></button>	
	</div>
</script>