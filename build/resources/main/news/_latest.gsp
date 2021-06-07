<b>${newsInstance.formattedDate()}</b><br><br>
<g:encodeAs codec="PreserveWhitespace">${newsInstance.firstLine()}</g:encodeAs><br>
<g:if test="${newsInstance.subsequentLines()}">
<div class="collapse" id="collapseNews">
	<g:encodeAs codec="PreserveWhitespace">${newsInstance.subsequentLines()}</g:encodeAs>
</div>
<div id="newsToggle" class="toggle-text" onclick="newsToggle()">show more</div>
</g:if>