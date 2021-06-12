<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.Study" %>
<%@ page import="balsa.scene.SceneLine" %>
<%@ page import="balsa.Dataset.Status" %>
<%@ page import="balsa.Study.DateRedirect" %>
<%@ page import="balsa.security.Terms" %>
<%@ page import="balsa.security.Approval" %>
<%@ page import="balsa.authorityControl.Institution" %>
<%@ page import="balsa.authorityControl.Publication" %>
<%@ page import="balsa.Species" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Edit ${datasetTermUppercase('item':datasetInstance)}</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden ${datasetTerm('item':datasetInstance)}">
				<div class="card-header h3 mb-0">Edit ${datasetTermUppercase('item':datasetInstance)}: ${datasetInstance.shortTitle ?: datasetInstance.title}</div>
				<div class="btn-group btn-bar">
					<button class="btn btn-primary" form="datasetForm" name="followup" value="edit">Save Changes</button>
					<g:link class="btn btn-light" controller="about" action="editing" target="_blank">About Editing</g:link>
					<g:link class="btn btn-light" action="show" resource="${datasetInstance}">Return to ${datasetTermUppercase('item':datasetInstance)}</g:link>
				</div>
				<div class="card-body pb-0 pr-0 pl-0">
					<ul class="nav nav-tabs border-bottom-0" role="tablist">
						<li role="presentation" class="nav-item col-3">
							<a class="nav-link active border-left-0 transparent-tab" href="#details" aria-controls="details" role="tab" data-toggle="tab">
								${datasetTermUppercase('item':datasetInstance)} Details
							</a>
						</li>
						<g:if test="${datasetInstance instanceof Study}">
						<li role="presentation" class="nav-item col-3">
							<a class="nav-link transparent-tab" href="#publication" aria-controls="publication" role="tab" data-toggle="tab">
								Publication
							</a>
						</li>
						</g:if>
						<li role="presentation" class="nav-item col-3">
							<a class="nav-link transparent-tab" href="#ownership" aria-controls="ownership" role="tab" data-toggle="tab">
								<g:if test="${datasetInstance instanceof Study}">Ownership and </g:if>Access
							</a>
						</li>
						<li role="presentation" class="nav-item col-3">
							<a class="nav-link transparent-tab<g:if test="${datasetInstance instanceof Study}"> border-right-0</g:if>" href="#presentation" aria-controls="presentation" role="tab" data-toggle="tab">
								Presentation
							</a>
						</li>
					</ul>
					<g:form name="datasetForm" action="update" id="${datasetInstance.id}" params="[version: versionInstance.id]" class="ajaxForm" data-success="successfulSave(data)">
						<div class="tab-content tab-padded">
							<div role="tabpanel" class="tab-pane active round-tab-pane" id="details">
								<div class="row">
									<div class="col-9">
										<div class="form-group ${hasErrors(bean: datasetInstance, field: 'title', 'error')}">
											<label for="name">Title</label>
											<g:field class="form-control" type="text" name="title" value="${datasetInstance.title}" maxlength="200" />
										</div>
										<div class="row">
											<div class="col-8">
												<div class="form-group ${hasErrors(bean: datasetInstance, field: 'shortTitle', 'error')}">
													<label for="name" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Optional: Enter a shortened version of your study title to display on home and study pages">
														Display Title<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
													</label>
													<g:field class="form-control" type="text" name="shortTitle" value="${datasetInstance.shortTitle}" maxlength="100" />
												</div>
											</div>
											<div class="col-3 pr-0">
												<div class="form-group ${hasErrors(bean: datasetInstance, field: 'extract', 'error')}">
													<label for="extract" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="The extraction directory is the name of the directory a user will see when they extract the contents of a zip file of your study data. You can customize the first part of this directory name, but it will always conclude with your study ID as a suffix to ensure your study data stays separate from other downloaded and extracted studies.">
														Extraction Directory Prefix<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
													</label>
													<g:field class="form-control prefix-field" type="text" name="extract" value="${datasetInstance.extract}" maxlength="50" />
												</div>
											</div>
											<div class="col-1 pl-0">
												<div class="form-group">
													<label for="extract">Suffix</label>
													<input class="form-control suffix-field" type="text" placeholder="_${datasetInstance.id}" readonly>
												</div>
											</div>
										</div>
									</div>
									<div class="col-3">
										<div class="form-group ${hasErrors(bean: datasetInstance, field: 'releaseDate', 'error')}">
											<label for="releaseDate">Release Date</label>
											<div id="releaseDatePicker" class="input-group">
												<input class="form-control datetimepicker" type="text" name="releaseDate" value="${versionInstance.releaseDate?.format('MM/dd/yyyy h:mm a')}"/>
											</div>
										</div>
										<div class="form-group">
											<label>Species</label>
											<div class="pt-2">
												<g:each in="${Species.list()}" var="species">
												<div class="checkbox d-inline mr-4">
													<label>
														<input type="checkbox" id="species" name="species" value="${species.id}" <g:if test="${datasetInstance.species.contains(species)}">checked</g:if>>
														<span>${species.name}</span>
													</label>
												</div>
												</g:each>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-6 form-group mb-0">
										<label for="studyAbstract" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Enter Publication abstract">
											Abstract<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
										</label>
										<g:textArea class="form-control static" name="studyAbstract" id="studyAbstract" value="${versionInstance.studyAbstract}" rows="18"/>
									</div>
									<div class="col-6 form-group mb-0">
										<label for="description" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Add more information about the dataset that you would like users to see displayed in BALSA (e.g. README information important for interpreting results displayed in scenes, links to code or software, or links to project data shared outside of BALSA)">
											Description<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
										</label>
										<g:textArea class="form-control static" name="description" value="${versionInstance.description}" rows="18"/>
									</div>
								</div>
							</div>

							
							<g:if test="${datasetInstance instanceof Study}">
							<div role="tabpanel" class="tab-pane round-tab-pane" id="publication">
								<div class="row">
									<div class="col-3">
										<div class="form-group ${hasErrors(bean: datasetInstance, field: 'pmid', 'error')}">
											<label for="pmid">PMID</label>
											<div class="row">
												<div class="col">
													<g:field class="form-control" type="text" name="pmid" value="${versionInstance.pmid}" id="pmidField" oninput="updatePubMedButton(this)"/>
												</div>
												<div class="form-group pr-4 mb-0">
													<label data-toggle="popover" data-trigger="hover" data-placement="right" data-content="BALSA can use data from PubMed to populate the DOI, publication data, author lists, and study abstract">
														<button id="pubMedDataModalButton" type="button" class="btn btn-light" data-toggle="modal" data-target="#pubMedDataModal" onclick="fetchPubMedData()">
															Get data from PubMed<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
														</button>
													</label>
												</div>
											</div>
										</div>

										<div class="form-group ${hasErrors(bean: datasetInstance, field: 'doi', 'error')}">
											<label for="doi">DOI</label>
											<g:field class="form-control" type="text" name="doi" value="${versionInstance.doi}" id="doi"/>
										</div>

										<div class="form-group">
											<label for="preprint" class="mr-5">Preprint</label>
											<div class="pt-2">
												<g:checkBox name="preprint" value="${versionInstance.preprint}" /><span class="ml-3 text-secondary">Check this box to mark this as a preprint version.</span>
											</div>
										</div>
									</div>
									<div class="col-6">
										<div class="row h-50">
											<div class="col-6">
												<div class="form-group ${hasErrors(bean: datasetInstance, field: 'publication', 'error')}">
													<label for="publication">Journal</label>
													<input class="form-control" id="publicationName" name="publicationName" type="text" value="${versionInstance.publication?.officialName}" />
												</div>
											</div>
											<div class="col-6">
												<div class="form-group">
													<label for="epubDate">Search for Journals</label>
													<g:field class="form-control mb-2" type="text" id="publicationSearch" name="publicationSearch" autocomplete="off" onkeyup="searchPublications()"/>
													<g:select class="form-control mb-2" multiple="true" id="publicationSearchResults" name="publicationSearchResults" from="${(Publication.list()*.officialName).sort()}" />
													<button type="button" class="btn btn-light" onclick="setPublication()">
														<span class="glyphicon glyphicon-arrow-left mr-3"></span>Use Selected
													</button>
												</div>
											</div>
										</div>
										<div class="row h-50">
											<div class="col-6">
												<div class="form-group">
													<label for="institutions">Institutions (one per line)</label>
													<span class="float-right text-secondary">shift-enter for new line</span>
													<textarea class="form-control static" rows="10" id="institutionsList" name="institutionsList" ><g:join in="${versionInstance.institutions*.canonicalName.sort()}" delimiter="\r\n"/></textarea>
												</div>
											</div>
											<div class="col-6">
												<div class="form-group">
													<label for="epubDate">Search for Institutions</label>
													<g:field class="form-control mb-2" type="text" id="institutionSearch" name="institutionSearch" autocomplete="off" onkeyup="searchInstitutions()"/>
													<g:select class="form-control mb-2" id="institutionSearchResults" name="institutionSearchResults" from="${(Institution.list()*.canonicalName).sort()}" multiple="true"/>
													<button type="button" class="btn btn-light" onclick="addToInstitutions()">
														<span class="glyphicon glyphicon-arrow-left mr-3"></span>Add Selected
													</button>
													<span class="float-right text-secondary">control-click to select multiple</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-3">
										<div class="form-group mb-0 ${hasErrors(bean: datasetInstance, field: 'authors', 'error')}">
											<label for="datasetAbstract">Authors (one per line)</label>
											<span class="float-right text-secondary">shift-enter for new line</span>
											<textarea class="form-control static" rows="25" name="authors" id="authors"><g:join in="${versionInstance.authors}" delimiter="\r\n"/></textarea>
										</div>
									</div>
								</div>
							</div>
							</g:if>

									
							<div role="tabpanel" class="tab-pane round-tab-pane" id="ownership">
								<g:if test="${datasetInstance instanceof Study}">
								<div class="row">
									<div class="form-group float-left col-4 ${hasErrors(bean: datasetInstance, field: 'owners', 'error')}">
										<label data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Owners are users who have ownership rights to your study data in BALSA, generally your co-authors. They may access the edit page, upload data, and handle submission of the study. By default your username will already be listed here. Note that owners you add will still need to agree to the Data Submission Terms before they have these permissions. They are added by their usernames, which means they must already have an account in BALSA (or have logged into BALSA at least once if they are using an HCP account).">
											Owners (one per line)<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
										</label>
										<textarea class="form-control static" id="ownerNames" name="ownerNames" rows="11"><g:join in="${datasetInstance.owners*.username.sort()}" delimiter="\r\n"/></textarea>
									</div>
									<div class="form-group float-left col-4">
										<label for="searchTerm">Search Users by Name or Email</label><br>
										<div>
											<input class="form-control" type="text" id="search_text" name="search_text" autocomplete="off" onkeyup="userSearch()"/>
										</div>
										<span style="color:gray">control-click to unselect or select multiple</span>
										<select class="form-control h-50 mb-2" rows="7" id="usernames" name="usernames" multiple></select>
										<button class="btn btn-light float-left" type="button" onclick="addToOwners()"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;&nbsp;Add Selected</button>
										<button class="btn btn-light float-right" type="button" onclick="addToViewers()">Add Selected&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></button>
									</div>

									<div class="form-group float-left col-4 ${hasErrors(bean: datasetInstance, field: 'viewer', 'error')}">
										<label data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Viewers are users who you would like to be able to view and download study data prior to that data being made publically available, allowing for reviewers. They are added by their usernames, which means they must already have an account in BALSA (or have logged into BALSA at least once if they are using an HCP account).">
											Viewers (one per line)<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
										</label>
										<textarea class="form-control static" id="viewerNames" name="viewerNames" rows="11"><g:join in="${datasetInstance.viewers*.username.sort()}" delimiter="\r\n"/></textarea>
									</div>
								</div>
								</g:if>
								<div class="form-group ${hasErrors(bean: datasetInstance, field: 'accessAgreement', 'error')} ">
									<label data-toggle="popover" data-trigger="hover" data-placement="right" data-content="You may select (or create with the New Data Access Terms section below) a set of terms to which a user must agree before they download your study data.">
										Data Access Terms<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
									</label>
									<div class="card">
										<div class="card-body" style="padding-top:12px;padding-bottom:3px">
											<g:each in="${Terms.findAllByTitleNotEqual('Terms and Conditions for Uploading Data to the Washington University (WU) BALSA Database')}" var="terms">
											<div class="checkbox" style="margin-top:0">
												<label>
													<input type="checkbox" id="accessAgreements" name="accessAgreements" value="${terms.id}" <g:if test="${datasetInstance.accessAgreements.contains(terms)}">checked</g:if>>
													<span style="padding-right:5px">${terms.title}</span>

												</label>
												<a data-toggle="modal" data-target="#${terms.id}TermsModal">preview</a>
												<div id="${terms.id}TermsModal" class="modal fade" role="dialog">
													<div class="modal-dialog modal-wide">
														<div class="modal-content">
															<div class="modal-header">
																<h4 class="modal-title">${terms.title}</h4>
																<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
															</div>
															<div class="modal-body">
																<g:encodeAs codec="PreserveWhitespace">${terms.contents}</g:encodeAs>
															</div>
														</div>
													</div>
												</div>
											</div>
											</g:each>
										</div>
									</div>
								</div>
								<div class="form-group mb-0">
									<label>New Data Access Terms</label>
									<div id="customTermsTitleDisplay" style="margin-bottom:11px">${datasetInstance.customTermsTitle ?: 'No new terms'}</div>
									<button type="button" class="btn btn-light" data-toggle="modal" data-target="#customTermsModal">Add/Edit New DAT</button>
								</div>
							</div>

							
							<div role="tabpanel" class="tab-pane round-tab-pane" id="presentation">
								<div class="row">
									<div class="col-7">
										<div class="form-group">
											<label data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Here you can change the order in which your scene files are displayed on your study page.">
												Scene File Order<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
											</label>
											<span class="float-right text-secondary">drag and drop boxes to change order</span>
											<input type=hidden id="sceneFileOrder" name="sceneFileOrder" />
											<div style="height:37.4em">
												<g:if test="${versionInstance.sceneFiles().size() == 0}">
												No scene files yet
												</g:if>
												<ul class="list-group" id="sceneFileSort">
													<g:each in="${versionInstance.sceneFiles()}" var="sceneFile">
													<li class="card mb-1" id="${sceneFile.filename}">
														<div class="card-body">
														<span class="glyphicon glyphicon-sort"></span>&nbsp;&nbsp; ${sceneFile.label ?: sceneFile.filename}
														</div>
													</li>
													</g:each>
												</ul>
											</div>
										</div>
									</div>
									<div class="col-5">
										<div class="form-group mb-0 ${hasErrors(bean: datasetInstance, field: 'focusScene', 'error')} " >
											<label for="focusScene" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Here you can select a single scene preview to be the starting point in the scene preview carousel, allowing you to specify an exemplar image for your study.">
												Focus Scene<span class="text-info ml-2 glyphicon glyphicon-info-sign"></span>
											</label>
											<div>
												<div id="focusScenePreview" class="scalingBox mb-3" style="width:621.25px;height:490px;">
													<g:if test="${versionInstance.focusScene}">
													<img src="/scene/image/${versionInstance.focusScene().id}"/>
													</g:if>
												</div>
												<select class="form-control" id="focusScene" name="focusScene" onchange="changeFocusScene()">
													<g:each in="${versionInstance.scenes()}" var="scene">
													<option value="${scene.sceneLine.id}" data-preview="${scene.id}" <g:if test="${scene.sceneLine == versionInstance.focusScene}">selected</g:if>>${scene.name}</option>
													</g:each>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="customTermsModal" class="modal fade" role="dialog">
								<div class="modal-dialog modal-wide">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title">Custom Data Access Terms</h4>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										</div>
										<div class="modal-body">
											<div class="form-group">
												<label for="username" class="control-label">Title</label>
												<g:field class="form-control" type="text" id="customTermsTitle" name="customTermsTitle" value="${datasetInstance.customTermsTitle}" />
											</div>
											<div class="form-group" style="margin-bottom:0">
												<label for="password" class="control-label">Contents</label>
												<g:textArea class="form-control" id="customTermsContent" name="customTermsContent" value="${datasetInstance.customTermsContent}" style="max-width:100%;resize:none" rows="20"/>
											</div>
										</div>
										<div class="modal-footer">
											<div class="btn-group">
												<button type="button" class="btn btn-light" data-toggle="modal" data-target="#customTermsModal" onclick="customTermsCancel()">Cancel</button>
												<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#customTermsModal" onclick="customTermsOkay()">Okay</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</g:form>
				</div>
			</div>
		</div>
		<div id="pubMedDataModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-wide">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">PubMed Data</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body" id="pubMedDataModalContents">
						<div id="pubMedDataLoading">
							Loading...
						</div>
						<div id="pubMedData">
							<div class="form-group">
								<label>DOI</label>
								<div id="pubMedDoi"></div>
							</div>
							<div class="form-group">
								<label>Journal</label>
								<div id="pubMedJournal"><</div>
							</div>
							<div class="form-group">
								<label>Authors</label>
								<div id="pubMedAuthors" style="max-height:300px;overflow-y:scroll"></div>
							</div>											
							<div class="form-group">
								<label>Abstract</label>
								<div id="pubMedAbstract" style="max-height:300px;overflow-y:scroll"></div>
							</div>
						</div>
						<div id="pubMedError">
						</div>
					</div>
					<div class="modal-footer">
						<div class="btn-group">
							<button type="button" class="btn btn-light" data-toggle="modal" data-target="#pubMedDataModal">Cancel</button>
							<button type="button" id="populatePubMedButton" class="btn btn-primary" data-toggle="modal" data-target="#pubMedDataModal" onclick="populatePubMedData()" disabled>Use this data</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
