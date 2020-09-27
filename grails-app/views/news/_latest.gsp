<b>${newsInstance.dateCreated.format("MMM. dd, yyyy")}</b><br>
<g:encodeAs codec="PreserveWhitespace">${newsInstance.firstLine()}</g:encodeAs>
<g:if test="${newsInstance.subsequentLines()}">
<div class="collapse" id="collapseNews">
	<g:encodeAs codec="PreserveWhitespace">${newsInstance.subsequentLines()}</g:encodeAs>
</div>
<div id="newsToggle" style="color:gray;margin-top:10px;font-style:italic">show more</div>
</g:if>
<script>
$('#newsToggle').click(function () {
	$('#collapseNews').collapse('toggle');
	if($('#newsToggle').text() == "show more")
	{
		$('#newsToggle').text("show less"); 
	}
	else
	{
		$('#newsToggle').text("show more"); 
	}
}); 
</script>