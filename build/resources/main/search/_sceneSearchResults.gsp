<div style="margin: -5px -5px 5px;font-size:0" onload="changeSceneFileVisibility();">
<g:set var="thumbnailSize" value="${1143/params.columns.toInteger()}" />

<g:each in="${searchResults.scenes}" var="scene">
	<g:render template="sceneSearchResult" model="['scene':scene]" />
</g:each>
<g:if test="${searchResults.scenes.size() < params.max}">
<g:each in="${(searchResults.scenes.size() .. (params.max - 1))}" status="i" var="thing">
	<g:render template="emptySearchResult" />
</g:each>
</g:if>
</div>

<div class="balsaPaginate">
<util:remotePaginate name="thing" controller="search" action="sceneSearch" total="${searchResults.totalCount}" params="${params}" update="sceneSearchResults" prev="&lt;" next="&gt;" onSuccess="updateRecentActivity();changeSceneFileVisibility();"/>
</div>