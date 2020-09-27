<p>Save failed.<p>
<p>The following field<g:if test="${datasetInstance.errors.allErrors*.field.size() > 1 }">s</g:if> failed to pass pre-save validation:</p>
<ul>
	<g:each in="${datasetInstance.errors.allErrors*.field}">
	<li>${it.toUpperCase()}</li>
	</g:each>
</ul>