<%@ page import="balsa.file.SceneFile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:fieldValue bean="${file}" field="filename"/></title>
	</head>
	<body>
		<div role="main">
			<g:render template="/templates/terms" bean="${file.terms()}" var="terms" />
			<g:render template="/download/downloadModal" bean="${file}" var="item" />
			<g:render template="/templates/fileModal" model="['files':dependencies,'datasetInstance':file.dataset, 'versionId': versionId]" />
			
			<div class="card overflow-hidden <g:datasetTerm item="${file}"/>">
				<div class="card-header h3 mb-0">Scene File: <g:fieldValue bean="${file}" field="filename"/></div>
				<div class="btn-group btn-bar">
					<g:if test="${item?.canEdit()}">
						<g:link class="btn btn-light" action="edit" id="${item.id}">Edit</g:link>
					</g:if>
					<g:render template="/templates/buttons" bean="${file}" var="item" />
					<g:if test="${file?.canEdit()}">
					<g:link class="btn btn-light" controller="${datasetTerm('item':file)}" action="editScenes" id="${file.dataset.id}" params="[startId: file.id]">Edit Scenes</g:link>
					</g:if>
					<button type="button" class="btn btn-light" role="button" data-toggle="modal" data-target="#fileModal">Supporting Files</button>
					<g:if test="${file?.canEdit()}">
					<button type="button" class="btn btn-light" onclick="bootbox.confirm({size: 'small', message: 'Remove this file?<g:if test="${file?.isPublic()}"> Removing a file creates a new working version of the dataset, but the file will remain in the current public version.</g:if>', 
						callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="remove" id="${file.id}"/>');} } })">
						Remove file
					</button>
					</g:if>
				</div>
				<div class="card-body">
					<div class="carousel-container">
						<g:set var="focusSceneIndex" value="${ file.scenesSorted().findIndexOf { it == focusScene } }"/>
						<g:set var="focusSceneIndex" value="${ focusSceneIndex >= 0 ? focusSceneIndex : 0 }"/>
						<div id="sceneCarousel" class="carousel slide" data-ride="carousel" data-interval="false">

							<div class="carousel-inner" role="listbox">
								<g:each in="${file.scenesSorted()}" var="scene" status="index">
								<div <g:if test="${index == focusSceneIndex}">class="carousel-item active"</g:if><g:else>class="carousel-item"</g:else>>
									<g:link controller="scene" action="show" id="${scene.sceneLine.id}" >
									<div class="scalingBox carousel-image">
										<img src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
									</div>

									<div class="scene-caption">${scene.name}</div>
									</g:link>
								</div>
								</g:each>
							</div>

							<a id="carouselLeft" class="carousel-control carousel-control-prev" href="#sceneCarousel" role="button" data-slide="prev" onclick="scrollToHighlight()"></a>
							<a id="carouselRight" class="carousel-control carousel-control-next" href="#sceneCarousel" role="button" data-slide="next" onclick="scrollToHighlight()"></a>

							<div id="thumbnails" class="thumbnail-container">
								<div  class="carousel-indicators">
									<g:each in="${file.scenesSorted()}" var="scene" status="index">
									<div <g:if test="${index == focusSceneIndex}">class="balsaThumbnail active"</g:if><g:else>class="balsaThumbnail"</g:else> id="thumbnail${index}" data-target="#sceneCarousel" data-slide-to="${index}" style="display:inline-block;padding:5px;">
										<div class="scalingBox carousel-thumbnail">			
											<img data-toggle="popover" data-trigger="hover" data-content="${scene.shortName ?: scene.name}" src=<g:createLink controller="scene" action="image" id="${scene?.id}"/> />
										</div>
									</div>
									</g:each>
								</div>
							</div>
						</div>
					</div>

					<p>
						<span class="attributeLabel">${datasetTerm('item':file)}:</span><br>
						<g:if test="${versionId}">
						<g:link action="show" controller="${datasetTerm('item':file)}" id="${file.dataset.id}" params="[version: versionId]">${file.dataset.title}</g:link>
						</g:if>
						<g:else>
						<g:link action="show" controller="${datasetTerm('item':file)}" id="${file.dataset.id}">${file.dataset.title}</g:link>
						</g:else>
					</p>

					<g:if test="${file.canEdit()}">
					<p>
						<span class="attributeLabel">FILE ADDED:</span><br>
						${file.added.format('yyyy-MM-dd h:mm a z')}
					</p>
					</g:if>

					<p>
						<span class="attributeLabel">PATH:</span><br>
						${file.filepath.replace('/',' / ')}
					</p>

					<g:if test="${file.label}">
					<p>
						<span class="attributeLabel">DESCRIPTIVE LABEL:</span><br>
						${file.label}
					</p>
					</g:if>

					<div>
						<span class="attributeLabel">SCENES:</span><br>
						<ul>
							<g:each in="${file.scenesSorted()}" var="scene">
							<li>
								<g:link controller="scene" action="show" id="${scene.sceneLine.id}">${scene.name?.encodeAsHTML()}</g:link>
								<g:if test="${scene.canView()}">
								(Index: ${scene.index}, ID: ${scene.sceneLine.id})
								</g:if>
							</li>
							</g:each>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
