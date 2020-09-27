<%@ page import="balsa.file.FileMetadata" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Edit Scenes</title>
	</head>
	<body>
		<div role="main">
			<h3>Edit Scenes</h3>
			
			<div class="btn-group">
				<g:link class="btn btn-default" action="show" resource="${datasetInstance}">Cancel</g:link>
				<button class="btn btn-primary" form="editScenesForm">Save Changes</button>
			</div>
			
			<br><br>
			
			<div class="well <g:datasetTerm item="${datasetInstance}"/>" style="overflow: hidden;">
				<g:form name="editScenesForm" action="saveSceneEdits" id="${datasetInstance.id}">
				<div class="panel panel-default pull-left" style="width: 63%; margin-bottom: 0">
					<div class="panel-body panel-group" id="sceneFileList" role="tablist" aria-multiselectable="true" style="margin-bottom:0;padding-right:0">
						<div class="panel panel-default" style="border-width:0">
							<div role="tab" id="headingDataset}">
								<a class="panel-title" role="button" data-toggle="collapse" data-parent="#sceneFileList" href="#panelDataset" aria-expanded="true" aria-controls="panelDataset">
									All Scenes in ${datasetTermUppercase('item':datasetInstance)}
								</a>
							</div>
							<div id="panelDataset" class="panel-collapse collapse ${!startId ? 'in' : ''} datasetPanel" role="tabpanel" aria-labelledby="headingDataset">
								<div class="panel-body" style="padding-right:0">
									<label>Tags for all scenes in ${datasetTerm('item':datasetInstance)}</label>
									<div id="datasetTagAcceptor" class="tagAcceptor">
									</div>
									<button class="btn btn-default" type="button" onclick="addTagsDataset()">Add tags <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span></button>
								</div>
							</div>
						</div>
						<g:each in="${versionInstance.sceneFiles()}" var="sceneFile">
						<div class="panel panel-default" style="border-width:0">
							<div role="tab" id="heading${sceneFile.id}">
								<a class="panel-title sceneFileHeader" role="button" data-toggle="collapse" data-parent="#sceneFileList" href="#${sceneFile.id}panel" aria-expanded="true" aria-controls="${sceneFile.id}panel">
									${sceneFile.filename}
								</a>
							</div>
							<div id="${sceneFile.id}panel" class="panel-collapse collapse ${(sceneFile.id == startId || (sceneFile.scenes*.id).contains(startId)) ? 'in': ''}" role="tabpanel" aria-labelledby="${sceneFile.id}heading">
								<div class="panel-body" style="padding-right:0;padding-top:0;padding-bottom:0">
									<div class="panel-body panel-group" id="${sceneFile.id}sceneList" role="tablist" aria-multiselectable="true" style="margin-bottom:0;padding:0">
										<div class="panel panel-default" style="border-width:0">
											<div role="tab" id="${sceneFile.id}allHeading">
												<a class="panel-title" role="button" data-toggle="collapse" data-parent="#${sceneFile.id}sceneList" href="#${sceneFile.id}allPanel" aria-expanded="true" aria-controls="${sceneFile.id}allPanel">
													All Scenes in Scene File
												</a>
											</div>
											<div id="${sceneFile.id}allPanel" class="panel-collapse collapse ${sceneFile.id == startId ? 'in': ''} sceneFilePanel" role="tabpanel" aria-labelledby="${sceneFile.id}allHeading">
												<div class="panel-body">
													<div class="form-group">
														<label for="${sceneFile.id}label" data-toggle="tooltip" data-placement="right" title="Add a description of the scene file to distinguish it from other scene files in the dataset">
															Scene File Description <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
														</label>
														<g:field class="form-control" type="text" name="${sceneFile.id}label" value="${sceneFile.label}"/>
													</div>
													<label>Tags for all scenes in scene file</label>
													<div id="${sceneFile.id}TagAcceptor" class="tagAcceptor">
													</div>
													<button class="btn btn-default" type="button" onclick="addTagsSceneFile('${sceneFile.id}')">Add tags <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span></button>
												</div>
											</div>
										</div>
										<g:each in="${sceneFile.scenesSorted()}" var="scene">
										<div class="panel panel-default" style="border-width:0">
											<div role="tab" id="${scene.id}heading">
												<a class="panel-title" role="button" data-toggle="collapse" data-parent="#${sceneFile.id}sceneList" href="#${scene.id}panel" aria-expanded="true" aria-controls="${scene.id}panel">
													${scene.name}
												</a>
											</div>
											<div id="${scene.id}panel" data-sceneId="${scene.id}" class="panel-collapse collapse ${scene.id == startId ? 'in': ''} scenePanel" role="tabpanel" aria-labelledby="${scene.id}heading">
												<div class="panel-body">
													<div style="float:left;width:401px">
														<div class="form-group">
															<label for="${scene.id}shortName" data-toggle="tooltip" data-placement="right" title="Alternative scene name for display in BALSA that distinguishes the scene from other scenes in the dataset or in BALSA generally. Useful if scene names in scene file are not particularly descriptive, e.g., Fig 1, Fig 2, etc., or are too long for display in the homepage scene carousel.">
																BALSA Scene Title <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
															</label>
															<g:field class="form-control" type="text" name="${scene.id}shortName" value="${scene.shortName}"/>
														</div>
														<div class="tagAcceptor">
															<label>Tags</label><br>
															<g:each in="${scene.tags}">
															<div class="category${it.substring(0, it.indexOf(':'))} well" style="margin-bottom:5px;padding:5px;display:inline-block">
																<g:field type="hidden" name="${scene.id}tags" value="${it}" />
																${it}
																<button type="button" name="removeTagButton" class="close" style="line-height:12.25px;margin-left:5px" onclick="jQuery(this).parent().remove();returnTagToDisplay('${it}');">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															</g:each>
														</div>
													</div>
													<div id="focusScenePreview" class="pull-right scalingBox" style="width:233px;height:151px">
														<img src="${'/scene/image/' + scene.id}"/>
													</div>
												</div>
											</div>
										</div>
										</g:each>
									</div>
								</div>
							</div>
						</div>
						</g:each>
					</div>
				</div>
				</g:form>
				<div style="float:left;width:37%;padding-left:15px">
					<div class="panel panel-default pull-left" style=";width:100%;margin-bottom: 0">
						<div class="panel-body">
							<div style="height:34px;margin-bottom:10px">
								<div style="font-size:30px;float:left">Tags</div>
								<g:select class="form-control" style="float:right;width:200px" id="filterCategory" name="filterCategory" from="${tagCategories*.name}" noSelection="['':'Filter by category']" onchange="filterTags()"/>
							</div>
							<div id="tagDisplay">
								<g:each in="${tagCategories}" var="category">
								<g:each in="${category.options}" var="option">
								<div class="category${category.name} well" style="margin-bottom:5px;padding:5px;display:inline-block" data-tagValue="${category.name + ':' + option}">
									<button type="button" class="close" style="float:left;margin-right:5px;margin-top:-2px" onclick="moveTagToSelected('${category.name + ':' + option}')">
										<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
									</button>
									${category.name + ':' + option}
								</div>
								</g:each>
								</g:each>
							</div>
						</div>
					</div>
				</div>
				<script>
					function filterTags() {
						var category = $('#filterCategory').val();
						if (category) {
							$("div[class^='category']").hide();
							$('.category'+category).show();
						}
						else {
							$("div[class^='category']").show();
						}
					}

					$('.panel-title.sceneFileHeader').click(function() {setTimeout(function() {showSceneFilePanels();},400);});
					$('.panel-title').click(function() {setTimeout(function() {hideTagsAlreadyInScene();},500);});

					function showSceneFilePanels() {
						$('.collapse:not(".in")').find('.collapse.in').collapse('hide');
						$('.collapse.in').find('.sceneFilePanel').collapse('show');
					}

					function hideTagsAlreadyInScene() {
						var currentScene = $('.in.scenePanel');
						if (currentScene.attr('data-sceneId')) {
							var tagSelector = $("[name='" + currentScene.attr('data-sceneId') + "tags']").each(function() { 
								$('#tagDisplay').find('div[data-tagValue="' + this.value + '"]').hide();
							});
						}
						else {
							filterTags();
						}
					}

					function returnTagToDisplay(tag) {
						$('#tagDisplay').find('div[data-tagValue="' + tag + '"]').show();
					}

					function moveTagToSelected(tag) {
						var currentScene = $('.in.scenePanel,.in.sceneFilePanel,.in.datasetPanel');
						var tagAcceptor = currentScene.find('.tagAcceptor');
						var category = tag.substring(0, tag.indexOf(':'));
						var sceneId = currentScene.attr('id').substring(0, currentScene.attr('id').length-5);
						var newTag = $('#tagTemplate').html().replace(/TAGVALUE/g, tag).replace(/CATEGORYVALUE/g, category).replace(/SCENEIDVALUE/g, sceneId);
						tagAcceptor.append(newTag);
						hideTagsAlreadyInScene();
					}

					function addTagsSceneFile(sceneFileId) {
						$('#' + sceneFileId + 'TagAcceptor').find('input').each(function() {
							var tag = $(this).val();
							var category = tag.substring(0, tag.indexOf(':'));
							$('#' + sceneFileId + 'panel').find('.scenePanel').each(function() {
								var tagAcceptor = $(this).find('.tagAcceptor');
								if (tagAcceptor.find('input[value="' + tag + '"]').length == 0) {
									var sceneId = $(this).attr('id').substring(0, $(this).attr('id').length-5);
									tagAcceptor.append($('#tagTemplate').html().replace(/TAGVALUE/g, tag).replace(/CATEGORYVALUE/g, category).replace(/SCENEIDVALUE/g, sceneId));
								}
							});
						});
						$('#' + sceneFileId + 'TagAcceptor').empty();
						bootbox.alert("Tags added to all scenes in the scene file.");
					}

					function addTagsDataset() {
						$('#datasetTagAcceptor').find('input').each(function() {
							var tag = $(this).val();
							var category = tag.substring(0, tag.indexOf(':'));
							$('.scenePanel').each(function() {
								var tagAcceptor = $(this).find('.tagAcceptor');
								if (tagAcceptor.find('input[value="' + tag + '"]').length == 0) {
									var sceneId = $(this).attr('id').substring(0, $(this).attr('id').length-5);
									tagAcceptor.append($('#tagTemplate').html().replace(/TAGVALUE/g, tag).replace(/CATEGORYVALUE/g, category).replace(/SCENEIDVALUE/g, sceneId));
								}
							});
						});
						$('#datasetTagAcceptor').empty();
						bootbox.alert("Tags added to all scenes.");
					}
					
				</script>
				<script id="tagTemplate" type="text/template">
															<div class="categoryCATEGORYVALUE well" style="margin-bottom:5px;padding:5px;display:inline-block">
																<g:field type="hidden" name="SCENEIDVALUEtags" value="TAGVALUE" />
																TAGVALUE
																<button type="button" name="removeTagButton" class="close" style="line-height:12.25px;margin-left:5px" onclick="jQuery(this).parent().remove();returnTagToDisplay('TAGVALUE');">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
				</script>
			</div>
		</div>
	</body>
</html>
