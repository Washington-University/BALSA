<%@ page import="balsa.security.BalsaUser" %>
<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.Study" %>
<%@ page import="balsa.curation.Issue" %>
<%@ page import="balsa.Version" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${datasetInstance.title}</title>
	</head>
	<body>
		<div role="main">
			<h3><g:datasetTermUppercase item="${datasetInstance}"/>: ${datasetInstance.shortTitle ?: datasetInstance.title}</h3>
			
			<g:render template="/templates/terms" bean="${datasetInstance.terms()}" var="terms" />
			<g:render template="/download/downloadModal" bean="${datasetInstance}" var="item" />
			<g:render template="/templates/fileModal" model="['files':versionInstance.files, 'datasetInstance': datasetInstance, 'versionInstance': versionInstance]" />
			<g:if test="${datasetInstance.canEdit()}">
				<g:render template="/templates/notesModal" bean="${datasetInstance}" var="dataset" />
				<g:render template="/templates/downloadStatsModal" model="['dataset':datasetInstance]"/>
			</g:if>
			
			<div class="btn-group">
				<g:if test="${datasetInstance.canEdit()}">
					<button type="button" id="togglePreview" class="btn btn-default" role="button" data-toggle="tooltip" data-placement="right" title="Preview what this page will look like to users." onclick="$('.canedit').toggle();$('#togglePreview').tooltip('hide');">
						<span class="canedit">Show</span><span class="canedit" style="display:none">Hide</span> Preview
					</button>
					<g:link class="btn btn-default canedit" action="edit" id="${datasetInstance.id}" params="[version: versionInstance.id]">Edit</g:link>
					<g:link class="btn btn-default canedit" action="editScenes" id="${datasetInstance.id}" params="[version: versionInstance.id]">Edit Scenes</g:link>
					<g:if test="${!versionInstance.isPublic()}">
						<g:link class="btn btn-default canedit" action="issues" id="${datasetInstance.id}">Issues: ${datasetInstance.issues?.count({ it.status != Issue.Status.RESOLVED })}</g:link>
					</g:if>
					<button id="notesModalButton" type="button" class="btn btn-default canedit" role="button" data-toggle="modal" data-target="#notesModal">
						Notes <g:if test="${!datasetInstance.hasReadNotes()}">(new)</g:if>
					</button>
					<a class="btn btn-default canedit" data-toggle="modal" data-target="#contactCuratorsModal">Contact Curators</a>
				</g:if>
				<g:if test="${datasetInstance?.canView() || versionInstance?.isPublic()}">
					<g:if test="${datasetInstance?.hasAccess()}">
						<button class="btn btn-default" data-toggle="modal" data-target="#downloadModal">Download</button>
					</g:if>
					<g:else>
						<sec:ifLoggedIn>
							<button class="btn btn-default disabled" >Download (Agreement to terms required)</button>
						</sec:ifLoggedIn>
						<sec:ifNotLoggedIn>
							<button class="btn btn-default disabled" >Download (Login required)</button>
						</sec:ifNotLoggedIn>
					</g:else>
				</g:if>
				<g:if test="${datasetInstance?.terms()?.numberOfTerms > 0}">
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#termsModal">Data Use Terms</button>
				</g:if>
				<button type="button" class="btn btn-default" role="button" data-toggle="modal" data-target="#fileModal">Files</button>
				
				<g:if test="${datasetInstance.canEdit()}">
					<button type="button" class="btn btn-default canedit" role="button" data-toggle="modal" data-target="#downloadStatsModal">Download Stats</button>
					<g:render template="/templates/submissionButtons" bean="${versionInstance}" var="versionInstance" />
					<button type="button" class="btn btn-default canedit" onclick="bootbox.confirm({size: 'small', message: 'Do you really want to delete this dataset from BALSA?', 
						callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="delete" id="${versionInstance.dataset.id}"/>');} } })">
						Delete
					</button>
				</g:if>
			</div>
			
			<br><br>
			<div class="well ${datasetTerm('item':datasetInstance)}" style="overflow: hidden;">
				<g:if test="${versionInstance.sceneFiles().size() > 0}">
				<g:render template="/templates/carouselWithThumbnails" model="['datasetVersion': versionInstance]" />
				</g:if>
				
				<g:if test="${datasetInstance.canEdit()}">
				<div class="h4 canedit" style="margin-top:0">
					<g:if test="${versionInstance == datasetInstance.workingVersion() && versionInstance.isNonpublic()}">
					This ${datasetTerm('item':datasetInstance)} dataset is not yet public. It must be submitted for review and approved by our curators.
					</g:if>
					
					<g:if test="${versionInstance != datasetInstance.workingVersion() && versionInstance.isNonpublic()}">
					This is a previous version of this ${datasetTerm('item':datasetInstance)} dataset. To make this the dataset's working version, click <g:link action="setWorkingVersion" id="${datasetInstance.id}" params="[version: versionInstance.id]">here</g:link>
					</g:if>
					
					<g:if test="${versionInstance == datasetInstance.publicVersion()}">
					This ${datasetTerm('item':datasetInstance)} dataset is public.
					</g:if>
					
					<g:if test="${versionInstance == datasetInstance.preprintVersion()}">
					This preprint ${datasetTerm('item':datasetInstance)} dataset is public.
					</g:if>
					
					<g:if test="${versionInstance == datasetInstance.submittedVersion()}">
					This ${datasetTerm('item':datasetInstance)} dataset has been submitted for review by our curators.
					</g:if>
					
					<g:if test="${versionInstance == datasetInstance.approvedVersion()}">
					This ${datasetTerm('item':datasetInstance)} dataset has been approved by our curators.
					<g:if test="${versionInstance.actualReleaseDate()}">
					It will become public at ${versionInstance.actualReleaseDate().format("yyyy-MM-dd h:mm a z")}.
					</g:if>
					<g:else>
					No release date has been set. You can set a release date on the <g:link action="edit" id="${datasetInstance.id}">Edit</g:link> screen.
					</g:else>
					</g:if>
					
					<g:if test="${datasetInstance.publicVersion() && versionInstance != datasetInstance.publicVersion()}">
					<br>There is a different version of this ${datasetTerm('item':datasetInstance)} dataset that is public <g:link action="show" id="${datasetInstance.id}" params="[version: 'public']">here</g:link>.
					</g:if>
					<g:if test="${datasetInstance.preprintVersion() && versionInstance != datasetInstance.preprintVersion()}">
					<br>There is a preprint version of this ${datasetTerm('item':datasetInstance)} dataset that is public <g:link action="show" id="${datasetInstance.id}" params="[version: 'preprint']">here</g:link>.
					</g:if>
					<g:if test="${datasetInstance.submittedVersion() && versionInstance != datasetInstance.submittedVersion()}">
					<br>There is a different version of this ${datasetTerm('item':datasetInstance)} dataset that has been submitted for curation <g:link action="show" id="${datasetInstance.id}" params="[version: 'submitted']">here</g:link>.
					</g:if>
					<g:if test="${datasetInstance.approvedVersion() && versionInstance != datasetInstance.approvedVersion()}">
					<br>There is a different version of this ${datasetTerm('item':datasetInstance)} dataset that has been approved by our curators <g:link action="show" id="${datasetInstance.id}" params="[version: 'approved']">here</g:link>.
					</g:if>
					<g:if test="${datasetInstance.workingVersion() && versionInstance != datasetInstance.workingVersion()}">
					<br>The current working version of this ${datasetTerm('item':datasetInstance)} dataset is <g:link action="show" id="${datasetInstance.id}" params="[version: 'working']">here</g:link>.
					</g:if>
					<select id="selectedVersion" class="form-control" name="selectedVersion" onchange="gotoVersion(this)" style="width: 170px; margin-top:10px">
						<option value="">ALL VERSIONS</option>
						<g:each in="${datasetInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}}" var="version">
						<option value="/${datasetTerm('item':datasetInstance)}/show/${datasetInstance.id}?version=${version.id}">${version.id} - ${version.status} - ${version.updatedDate}</option>
						</g:each>
					</select>
					<script>
					function gotoVersion(selectObject) {
						var url = selectObject.value;
						if (url) {
							window.location = url;
						}
						return false;
					}
					</script>
				</div>
				
				<p class="canedit">
					<span class="attributeLabel">STUDY ID:</span><br>
					<g:fieldValue bean="${datasetInstance}" field="id"/>
				</p>
				</g:if>
				
				<g:if test="${versionInstance.isPublic() && !versionInstance.preprint && datasetInstance.preprintVersion()}">
				<p class="h4">
					There is also a preprint version of this study <g:link action="show" id="${datasetInstance.id}" params="[version: 'preprint']">here</g:link>.
				</p>
				</g:if>
				<g:if test="${versionInstance.isPublic() && versionInstance.preprint && datasetInstance.publicVersion()}">
				<p class="h4">
					This is a preprint version of this study. The final version can be found <g:link action="show" id="${datasetInstance.id}">here</g:link>.
				</p>
				</g:if>
				
				<p>
					<span class="attributeLabel">FULL TITLE:</span><br>
					<span>${datasetInstance.title}</span>
				</p>
				
				<g:if test="${datasetInstance?.species}">
				<p>
					<span class="attributeLabel">SPECIES:</span><br>
					<g:join in="${datasetInstance.species.name}" delimiter=", "/>
				</p>
				</g:if>
				
				<g:if test="${versionInstance?.description}">
				<p>
					<span class="attributeLabel">DESCRIPTION:</span><br>
					<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${versionInstance}" field="description"/></g:encodeAs>
				</p>
				</g:if>
				
				<g:if test="${datasetInstance instanceof Study}">
				<g:if test="${versionInstance?.studyAbstract}">
				<p>
					<span class="attributeLabel">ABSTRACT:</span><br>
					<span>${versionInstance?.studyAbstract}</span>
				</p>
				</g:if>
				
				<g:if test="${versionInstance?.publication}">
				<p>
					<span class="attributeLabel">PUBLICATION:</span><br>
					<span>${versionInstance?.publication?.officialName.encodeAsHTML()}</span>
					
					<g:if test="${versionInstance?.doi}">
						- DOI:
						<a href="https://doi.org/${versionInstance.doi}" target="_blank"><g:fieldValue bean="${versionInstance}" field="doi"/></a>
					</g:if>
					<g:if test="${versionInstance?.pmid}">
						- PMID:
						<a href="https://www.ncbi.nlm.nih.gov/pubmed/${versionInstance.pmid}" target="_blank"><g:fieldValue bean="${versionInstance}" field="pmid"/></a>
					</g:if>
				</p>
				</g:if>
				
				<g:if test="${versionInstance?.authors}">
				<div>
					<span class="attributeLabel">AUTHORS:</span><br>
					<ul>
						<g:each in="${versionInstance.authors}">
						<li>${it}</li>
						</g:each>
					</ul>
				</div>
				</g:if>
				
				<g:if test="${versionInstance?.institutions}">
				<div>
					<span class="attributeLabel">INSTITUTIONS:</span><br>
					<ul>
						<g:each in="${versionInstance.institutions}">
						<li>${it.canonicalName}</li>
						</g:each>
					</ul>
				</div>
				</g:if>
				</g:if>
				
				<g:if test="${versionInstance.sceneFiles().size() > 0}">
				<div>
					<span class="attributeLabel">SCENE FILES:</span><br>
					<g:render template="/templates/sceneList" model="['sceneFiles': versionInstance.sceneFiles()]" />
				</div>
				</g:if>
				
				<g:if test="${versionInstance.linkedScenes.size() > 0}">
				<div>
					<span class="attributeLabel">LINKED SCENES:</span><br>
					<g:render template="/templates/linkedSceneList" model="['currentDatasetVersion': versionInstance]" />
				</div>
				</g:if>
			</div>
		</div>
		<div id="contactCuratorsModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Contact Curators</h4>
					</div>
					<div class="modal-body">
						<g:formRemote name="contactCuratorsForm" class="form-horizontal" style="padding-right:15px;padding-left:15px" url="[controller: datasetTerm('item':datasetInstance), action: 'messageCurator', id: datasetInstance.id]" method="POST" onSuccess="curatorSendingSuccess();updateRecentActivity();">
							<div class="form-group">
								<label class="control-label" for="name">Email Address For Replies</label>
								<div>
									<input type="text" class="form-control" name="emailAddress" value="${BalsaUser.get(sec.loggedInUserInfo(field:"id"))?.profile?.emailAddress}">
								</div>
							</div>
						
							<div class="form-group">
								<label class="control-label" for="subject">Subject</label>
								<div>
									<input type="text" class="form-control" name="subject">
								</div>
							</div>
							
							<div class="form-group">
								<div>
									<textArea class="form-control" rows="10" name="contents" placeholder="Write your message here."></textArea>
								</div>
							</div>
						</g:formRemote>
					</div>
					<div class="modal-footer">
						<div class="btn-group">
							<button type="submit" class="btn btn-primary" form="contactCuratorsForm">Send Message</button>
							<button style="width:87px" class="btn btn-default" data-toggle="modal" data-target="#contactCuratorsModal">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
		function curatorSendingSuccess() {
			$('#contactCuratorsModal').modal('hide');
			bootbox.alert('Message sent.');
		}
		</script>
	</body>
</html>
