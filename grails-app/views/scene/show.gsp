<%@ page import="balsa.scene.Scene" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${sceneInstance.name}</title>
	</head>
	<body>
		<div role="main">
			<h3>Scene: <g:fieldValue bean="${sceneInstance}" field="name"/> <g:if test="${sceneInstance.shortName}">/ <g:fieldValue bean="${sceneInstance}" field="shortName"/></g:if></h3>
			
			<div id="fullSizeModal" class="modal fade" role="dialog">
				<div class="modal-dialog" style="width:auto;display: table;">
					<div class="modal-content fullsceneview">
						<div style="background-color:lightgrey;text-align:right;height:30px;border-bottom:1px solid grey">
							<a  href="" data-toggle="modal" data-target="#fullSizeModal">
								<span style="font-size:20px;line-height:29px;margin-right:4px;color:dimgrey" class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</a>
						</div>
						<div class="modal-body">
							<img src=<g:createLink controller="scene" action="image" id="${sceneInstance?.id}"/>/>
						</div>
					</div>
				</div>
			</div>
			
			<g:render template="/templates/terms" bean="${sceneInstance.terms()}" var="terms" />
			<g:render template="/download/downloadModal" bean="${sceneInstance}" var="item" />
			<div class="btn-group">
				<g:if test="${item?.canEdit()}">
					<g:link class="btn btn-default" action="edit" id="${item.id}">Edit</g:link>
				</g:if>
				<g:render template="/templates/buttons" bean="${sceneInstance}" var="item" />
				<button type="button" class="btn btn-default" role="button" data-toggle="modal" data-target="#fileModal">Supporting Files</button>
			</div>
			<g:render template="/templates/fileModal" model="['files':sceneInstance.dependencies(),'datasetInstance':sceneInstance.sceneFile.dataset, 'versionId': versionId]" />
			<br><br>
			<div class="well <g:datasetTerm item="${sceneInstance}"/>" style="overflow: hidden;">
				<div class="pull-right" style="padding-left:10px">
					<div data-toggle="modal" data-target="#fullSizeModal" class="scalingBox" style="width:580px;height:380px;">
						<img src=<g:createLink controller="scene" action="image" id="${sceneInstance?.id}"/>/>
					</div>
				</div>
				
				<p>
					<span class="attributeLabel">${datasetTerm('item':sceneInstance)}:</span><br>
					<g:link action="show" controller="${datasetTerm('item':sceneInstance)}" id="${sceneInstance.sceneFile.dataset.id}">${sceneInstance.sceneFile.dataset.shortTitle ?: sceneInstance.sceneFile.dataset.title}</g:link>
				</p>
				
				<p>
					<span class="attributeLabel">SCENE FILE:</span><br>
					<g:link action="show" controller="sceneFile" id="${sceneInstance.sceneFile.id}">${sceneInstance.sceneFile.filename.replace('.scene','')}</g:link>
				</p>
				
				<p>
					<span class="attributeLabel">SCENE:</span><br>
					<g:fieldValue bean="${sceneInstance}" field="name"/>
				</p>
				
				<p>
					<span class="attributeLabel">DESCRIPTION:</span><br>
					<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${sceneInstance}" field="description"/></g:encodeAs>
				</p>
				
				<p>
					<span class="attributeLabel">TAGS:</span><br>
					<g:join in="${sceneInstance.tags}" delimiter=", "/>
				</p>
			</div>
		</div>
	</body>
</html>
