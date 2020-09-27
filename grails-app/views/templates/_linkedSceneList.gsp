<ul>
	<g:each in="${currentDatasetVersion.linkedDatasets()}" var="dataset">
	<li>
		<g:link action="show" controller="${datasetTerm('item':dataset)}" id="${dataset.id}">
			${dataset.shortTitle ?: dataset.title}
		</g:link>
		<ul>
			<g:each in="${currentDatasetVersion.linkedScenesByDataset(dataset)}" var="scene">
			<li>
				<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.name?.encodeAsHTML()}</g:link>
			</li>
			</g:each>
		</ul>
		<p></p>
	</li>
	</g:each>
</ul>