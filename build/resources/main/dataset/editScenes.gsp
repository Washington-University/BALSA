<%@ page import="balsa.file.FileMetadata" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Edit Scenes</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden <g:datasetTerm item="${datasetInstance}"/>">
				<div class="card-header h3 mb-0">Edit ${datasetTermUppercase('item':datasetInstance)} Scenes: ${datasetInstance.shortTitle ?: datasetInstance.title}</div>
				<div class="btn-group btn-bar">
					<button class="btn btn-primary" form="editScenesForm">Save Changes</button>
					<g:link class="btn btn-light" action="show" resource="${datasetInstance}">Return to ${datasetTermUppercase('item':datasetInstance)}</g:link>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-4">
							<div class="list-group list-group-root well">
								<div class="list-group-item scene-list-item <g:if test="${!startId}">active</g:if>" onclick="changeSceneListItem(this);viewDatasetData();">All Scenes in ${datasetTermUppercase('item':datasetInstance)}</div>
								<g:each in="${versionInstance.sceneFiles()}" var="sceneFile">
								<div class="list-group">
									<div class="list-group-item scene-list-item <g:if test="${startId == sceneFile.id}">active</g:if>" onclick="changeSceneListItem(this);viewSceneFileData('${sceneFile.id}');">
										${sceneFile.filename}
									</div>
									<g:each in="${sceneFile.scenesSorted()}" var="scene">
									<div class="list-group">
										<div class="list-group-item scene-list-item <g:if test="${startId == scene.sceneLine.id}">active</g:if>" onclick="changeSceneListItem(this);viewSceneData('${scene.sceneLine.id}');">
											${scene.name}
										</div>
									</div>
									</g:each>
								</div>
								</g:each>
							</div>
						</div>
						<div class="col-4">
							<div class="card">
								<div class="card-body">
									<g:form name="editScenesForm" action="saveSceneEdits" id="${datasetInstance.id}" params="[version: versionInstance.id]" class="ajaxForm" data-success="successfulSave(data)">
									<div class="sceneData <g:if test="${startId}">d-none</g:if>" id="datasetData">
										<label>Tags for all scenes in ${datasetTerm('item':datasetInstance)}</label>
										<div class="tagContainer">
											<g:each in="${versionInstance.tagsInAllScenes()}">
											<div class="tagCategory category${it.substring(0, it.indexOf(':')).replaceAll(' ', '')} card m-1" data-tag="${it}">
												<div class="card-body p-2">
													${it}
													<button type="button" name="removeTagButton" class="close" onclick="removeTag('${it}')">
														<span class="glyphicon glyphicon-arrow-right ml-2" style="font-size: 1.25rem;"></span>
													</button>
												</div>
											</div>
											</g:each>
										</div>
										<g:if test="${!startId}"><g:set var="initialTags" value="${versionInstance.tagsInAllScenes()}"/></g:if>
									</div>
									<g:each in="${versionInstance.sceneFiles()}" var="sceneFile">
									<div class="sceneData sceneFileData <g:if test="${startId != sceneFile.id}">d-none</g:if>" id="sceneFileData${sceneFile.id}">
										<div class="form-group">
											<label for="label${sceneFile.id}" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Add a description of the scene file to distinguish it from other scene files in the dataset">
												Scene File Description<span class="text-info ml-2 glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
											</label>
											<g:field class="form-control" type="text" name="label${sceneFile.id}" value="${sceneFile.label}"/>
										</div>
										<label>Tags for all scenes in scene file</label>
										<div class="tagContainer">
											<g:each in="${sceneFile.tagsInAllScenes()}">
											<div class="tagCategory category${it.substring(0, it.indexOf(':')).replaceAll(' ', '')} card m-1" data-tag="${it}">
												<div class="card-body p-2">
													${it}
													<button type="button" name="removeTagButton" class="close" onclick="removeTag('${it}')">
														<span class="glyphicon glyphicon-arrow-right ml-2" style="font-size: 1.25rem;"></span>
													</button>
												</div>
											</div>
											</g:each>
										</div>
										<g:if test="${startId == sceneFile.id}"><g:set var="initialTags" value="${sceneFile.tagsInAllScenes()}"/></g:if>
									</div>
									<g:each in="${sceneFile.scenesSorted()}" var="scene">
									<div class="sceneData <g:if test="${startId != scene.sceneLine.id}">d-none</g:if>" id="sceneData${scene.sceneLine.id}" data-scenefile="sceneFileData${sceneFile.id}">
										<div class="form-group">
											<label for="shortName${scene.id}" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Alternative scene name for display in BALSA that distinguishes the scene from other scenes in the dataset or in BALSA generally. Useful if scene names in scene file are not particularly descriptive, e.g., Fig 1, Fig 2, etc., or are too long for display in the homepage scene carousel.">
												BALSA Scene Title<span class="text-info ml-2 glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
											</label>
											<g:field class="form-control" type="text" name="shortName${scene.id}" value="${scene.shortName}"/>
										</div>
										<label>Tags</label>
										<div class="tagContainer">
											<input type="hidden" id="tags${scene.id}" name="tags${scene.id}" class="tagString" value="${scene.tags.join(',')}">
											<g:each in="${scene.tags}">
											<div class="tagCategory category${it.substring(0, it.indexOf(':')).replaceAll(' ', '')} card m-1" data-tag="${it}">
												<div class="card-body p-2">
													${it}
													<button type="button" name="removeTagButton" class="close" onclick="removeTag('${it}')">
														<span class="glyphicon glyphicon-arrow-right ml-2" style="font-size: 1.25rem;"></span>
													</button>
												</div>
											</div>
											</g:each>
										</div>										
										<div id="focusScenePreview" class="scalingBox mt-3" style="width:452.33px;height:290px">
											<img src="${'/scene/image/' + scene.id}"/>
										</div>
										<g:if test="${startId == scene.sceneLine.id}"><g:set var="initialTags" value="${scene.tags}"/></g:if>
									</div>
									</g:each>
									</g:each>
									</g:form>
								</div>
							</div>
						</div>
						<div class="col-4">
							<div class="card">
								<div class="card-header h4">
									Tags
									<g:select class="form-control float-right" style="width:200px" id="filterCategory" name="filterCategory" from="${tagCategories*.name}" noSelection="['':'All categories']" onchange="filterTags()"/>
								</div>
								<div id="tagDisplay" class="card-body tagContainer">
									<g:each in="${tagCategories}" var="category">
									<g:each in="${category.options}" var="option">
									<div class="tagCategory category${category.name.replaceAll(' ', '')} card m-1 <g:if test="${initialTags.contains(category.name + ':' + option)}">d-none</g:if>" data-tagvalue="${category.name + ':' + option}">
										<div class="card-body p-2">
											<button type="button" class="close float-left mr-2" style="font-size: 1.25rem;" onclick="addTag('${category.name + ':' + option}')">
												<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
											</button>
											${category.name + ':' + option}
										</div>
									</div>
									</g:each>
									</g:each>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script id="tagTemplate" type="text/template">
			<div class="tagCategory categoryCATEGORYVALUE card m-1" data-tag="TAGVALUE">
				<div class="card-body p-2">
					TAGVALUE
					<button type="button" name="removeTagButton" class="close" onclick="removeTag('TAGVALUE')">
						<span class="glyphicon glyphicon-arrow-right ml-2" style="font-size: 1.25rem;"></span>
					</button>
				</div>
			</div>
		</script>
	</body>
</html>