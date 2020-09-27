<g:each in="${category.options}">
<input type="checkbox" id="categoryOptions" name="${category.name}" value="${it}" onchange="refreshSearchResults()" checked />
<span style="padding-right:5px">${it}</span>
</g:each>
<input type="hidden" name="${category.name}" value="none" />