<div style="width:820px;padding-top:5px;font-size:0;margin-left:-6px; margin-bottom:-5px;">
<g:each in="${searchResults.datasets}" var="dataset">
	<g:render template="datasetSearchResult" model="['dataset':dataset]" />
</g:each>
<g:if test="${searchResults.datasets.size() < params.max}">
<g:each in="${(searchResults.datasets.size() .. (params.max - 1))}" status="i" var="thing">
	<g:render template="emptySearchResult" />
</g:each>
</g:if>
</div>

<g:if test="${searchResults.totalCount > params.max}">
<div style="text-align:center;margin-top:-10px;margin-bottom:-25px">
<util:remotePaginate controller="search" action="datasetSearch" total="${searchResults.totalCount}" params="${params}" update="datasetSearchResults" prev="&lt;&lt;" next="&gt;&gt;" onsuccess="updateRecentActivity();"/> 
</div>
</g:if>
<g:else>
<div style="height:47px;"></div>
</g:else>

<script>
changeSceneFileVisibility();
</script>