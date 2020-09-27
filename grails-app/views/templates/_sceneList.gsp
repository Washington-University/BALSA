<ul>
	<g:each in="${sceneFiles}" var="sceneFile">
	<li>
		<g:link action="show" controller="sceneFile" id="${sceneFile.id}">
			${sceneFile.filename} 
			<g:if test="${sceneFile.canView()}">
			<span class="canedit">(ID: ${sceneFile.id})</span>
			</g:if>
		</g:link>
		<br>
		<g:if test="${sceneFile.label}">
			<span class="attributeLabel">DESCRIPTION:</span> ${sceneFile.label}<br>
		</g:if>
		
		<span class="attributeLabel">SCENES:</span>
		<ul>
			<g:each in="${sceneFile.scenesSorted()}" var="scene">
			<li>
				<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.name + (scene.shortName ? ' - ' + scene.shortName : '')}</g:link>
				<g:if test="${scene.canView()}">
				<span class="canedit">(Index: ${scene.index}, ID: ${scene.sceneLine.id})</span>
				</g:if>
			</li>
			</g:each>
		</ul>
		<p></p>
	</li>
	</g:each>
</ul>