<div class="mt-2">
<g:each in="${category.options}">
<input type="checkbox" id="categoryOptions" name="${category.name}" value="${it}" class="searchRefresh" checked />
<span class="mr-4">${it}</span>
</g:each>
<input type="hidden" name="${category.name}" value="none" />
</div>