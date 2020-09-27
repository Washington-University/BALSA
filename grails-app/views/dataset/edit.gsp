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
			<h3>Edit ${datasetTermUppercase('item':datasetInstance)}: ${datasetInstance.shortTitle ?: datasetInstance.title}</h3>
			
			<div class="btn-group">
				<g:link class="btn btn-default" action="show" resource="${datasetInstance}">Return to ${datasetTermUppercase('item':datasetInstance)}</g:link>
				<g:link class="btn btn-default" controller="about" action="editing" target="_blank">About Editing</g:link>
				<button class="btn btn-primary" form="datasetForm" name="followup" value="edit">Save Changes</button>
			</div>
			
			<br><br>
			<div class="well ${datasetTerm('item':datasetInstance)}" style="overflow: hidden;">
				<g:formRemote id="datasetForm" name="datasetForm" url="[controller: datasetTerm('item':datasetInstance), action: 'update', id: datasetInstance.id, params: [version: versionInstance.id]]" method="POST" onSuccess="successfulSave(data);updateRecentActivity();">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active" style="width:25%"><a href="#details" aria-controls="details" role="tab" data-toggle="tab">${datasetTermUppercase('item':datasetInstance)} Details</a></li>
					<g:if test="${datasetInstance instanceof Study}">
					<li role="presentation" style="width:25%"><a class="${hasErrors(bean: datasetInstance, field: 'pmid', 'error')} ${hasErrors(bean: datasetInstance, field: 'doi', 'error')}" href="#publication" aria-controls="publication" role="tab" data-toggle="tab">Publication</a></li>
					</g:if>
					<li role="presentation" style="width:25%"><a href="#ownership" aria-controls="ownership" role="tab" data-toggle="tab"><g:if test="${datasetInstance instanceof Study}">Ownership and </g:if>Access</a></li>
					<li role="presentation" style="width:25%"><a href="#presentation" aria-controls="presentation" role="tab" data-toggle="tab">Presentation</a></li>
				</ul>
				<div class="tab-content">
					<div id="details" class="tab-pane whitetab active" role="tabpanel" style="height:730px">
						<div class="form-group ${hasErrors(bean: datasetInstance, field: 'title', 'error')}">
							<label for="name">Title</label>
							<g:field class="form-control" type="text" name="title" value="${datasetInstance.title}" maxlength="200" />
						</div>
						<div>
							<div class="form-group" style="float:right;width:40%;height:131px">
								<label>Species</label>
								<div class="panel panel-default">
									<div class="panel-body" style="padding-top:12px;padding-bottom:3px">
										<g:each in="${Species.list()}" var="species">
										<div class="checkbox" style="margin-top:0">
											<label>
												<input type="checkbox" id="species" name="species" value="${species.id}" <g:if test="${datasetInstance.species.contains(species)}">checked</g:if>>
												<span style="padding-right:5px">${species.name}</span>
											</label>
										</div>
										</g:each>
									</div>
								</div>
							</div>
							<div class="form-group" style="float:right;width:60%;padding-right:15px">
								<div class="form-group ${hasErrors(bean: datasetInstance, field: 'shortTitle', 'error')}">
									<label for="name" data-toggle="tooltip" data-placement="right" title="Optional: Enter a shortened version of your study title to display on home and study pages">
										Display Title <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
									</label>
									<g:field class="form-control" type="text" name="shortTitle" value="${datasetInstance.shortTitle}" maxlength="100" />
								</div>
								<div class="form-group ${hasErrors(bean: datasetInstance, field: 'extract', 'error')}" style="display:inline-block;margin-bottom:0px;width:550px">
									<label for="extract" data-toggle="tooltip" data-placement="right" title="The extraction directory is the name of the directory a user will see when they extract the contents of a zip file of your study data. You can customize the first part of this directory name, but it will always conclude with your study ID as a suffix to ensure your study data stays separate from other downloaded and extracted studies.">
										Extraction Directory Prefix <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
									</label>
									<g:field class="form-control" type="text" name="extract" value="${datasetInstance.extract}" maxlength="50" />
								</div>
								<div class="form-group" style="display:inline-block;margin-bottom:0px">
									<label for="extract">Suffix</label>
									<div style="margin-top:7px">_${datasetInstance.id}</div>
								</div>
							</div>
						</div>
						<g:if test="${datasetInstance instanceof Study}">
						<div class="form-group">
							<label for="studyAbstract" data-toggle="tooltip" data-placement="right" title="Enter Publication abstract">
								Abstract <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
							</label>
							<g:textArea class="form-control" name="studyAbstract" id="studyAbstract" value="${versionInstance.studyAbstract}" style="max-width:100%;resize:none" rows="9"/>
						</div>
						</g:if>
						<div class="form-group">
							<label for="description" data-toggle="tooltip" data-placement="right" title="Add more information about the dataset that you would like users to see displayed in BALSA (e.g. README information important for interpreting results displayed in scenes, links to code or software, or links to project data shared outside of BALSA)">
								Description <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
							</label>
							<g:textArea class="form-control" name="description" value="${versionInstance.description}" style="max-width:100%;resize:none" rows="9"/>
						</div>
					</div>

					<g:if test="${datasetInstance instanceof Study}">
					<div id="publication" class="tab-pane whitetab" role="tabpanel" style="height:730px">
						<div style="float:left;width:32%;padding-right:7.5px">
							<div class="form-group ${hasErrors(bean: datasetInstance, field: 'pmid', 'error')}" style="margin-bottom:11px">
								<label for="pmid">PMID</label>
								<g:field class="form-control" type="text" name="pmid" value="${versionInstance.pmid}" id="pmidField" oninput="updatePubMedButton(this)"/>
							</div>
							
							<div class="form-group">
								<label data-toggle="tooltip" data-placement="right" title="BALSA can use data from PubMed to populate the DOI, publication data, author lists, and study abstract">
									<button id="pubMedDataModalButton" type="button" class="btn btn-default" data-toggle="modal" data-target="#pubMedDataModal" disabled onclick="fetchPubMedData()">
										Get data from PubMed <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
									</button>
								</label>
							</div>
							
							<div class="form-group ${hasErrors(bean: datasetInstance, field: 'doi', 'error')}" style="margin-bottom:25px">
								<label for="doi">DOI</label>
								<g:field class="form-control" type="text" name="doi" value="${versionInstance.doi}" id="doi"/>
							</div>
							
							<div class="form-group">
								<label for="preprint">Preprint</label><br>
								<g:checkBox name="preprint" value="${versionInstance.preprint}" /> Check this box to mark this as a preprint version.</span>
							</div>
							
							<div class="form-group ${hasErrors(bean: datasetInstance, field: 'dateRedirect', 'error')}">
								<label for="dateRedirect"  data-toggle="tooltip" data-placement="right" title="Select a 'Release Date' type for your study: the date the study will be publicly released on BALSA">
									Release Date <span class="glyphicon glyphicon-info-sign" aria-hidden="true"  style="color:darkgray"></span>
								</label>
								<div id="releaseDateSelect" style="display:table;width:100%">
									<g:select class="form-control" name="dateRedirect" from="${DateRedirect.values()}" optionKey="key" value="${versionInstance.dateRedirect}" noSelection="['':'Select release date']" />
								</div>
							</div>
							
							<div class="form-group ${hasErrors(bean: datasetInstance, field: 'preprintDate', 'error')}">
								<label for="preprintDate">Preprint Date</label>
								<div id="preprintDatePicker" class="input-group date">
									<input class="form-control" type="text" name="preprintDate" value="${versionInstance.preprintDate?.format('MM/dd/yyyy h:mm a')}"/>
								    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							
							<div class="form-group ${hasErrors(bean: datasetInstance, field: 'epubDate', 'error')}">
								<label for="epubDate">Epub Date</label>
								<div id="epubDatePicker" class="input-group date">
									<input class="form-control" type="text" name="epubDate" value="${versionInstance.epubDate?.format('MM/dd/yyyy h:mm a')}"/>
								    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							
							<div class="form-group ${hasErrors(bean: datasetInstance, field: 'journalDate', 'error')}">
								<label for="journalDate">Journal Date</label>
								<div id="journalDatePicker" class="input-group date">
									<input class="form-control" type="text" name="journalDate" value="${versionInstance.journalDate?.format('MM/dd/yyyy h:mm a')}"/>
								    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
						
							<div class="form-group ${hasErrors(bean: datasetInstance, field: 'releaseDate', 'error')}">
								<label for="releaseDate">Custom Date</label>
								<div id="releaseDatePicker" class="input-group date">
									<input class="form-control" type="text" name="releaseDate" value="${versionInstance.releaseDate?.format('MM/dd/yyyy h:mm a')}"/>
								    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
						</div>
						<div style="float:left;width:68%;padding-left:7.5px">
							<div style="height:391px">
								<div style="float:left;width:50%;padding-right:7.5px;height:100%">
									<div class="form-group ${hasErrors(bean: datasetInstance, field: 'publication', 'error')}">
										<label for="publication">Journal</label>
										<input class="form-control" id="publicationName" name="publicationName" type="text" value="${versionInstance.publication?.officialName}" />
									</div>
									<div class="form-group">
										<label for="epubDate">Search for Journals</label>
										<g:field class="form-control" type="text" id="publicationSearch" name="publicationSearch" style="margin-bottom:8px" />
										<g:select class="form-control" multiple="true" id="publicationSearchResults" name="publicationSearchResults" from="" style="margin-bottom:8px;height:195px"/>
										<button type="button" class="btn btn-default" onclick="setPublication()">
											 <span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>&nbsp;&nbsp;Use Selected
										</button>
									</div>
								</div>
								<div class="form-group ${hasErrors(bean: datasetInstance, field: 'authors', 'error')} " style="float:left;width:50%;padding-left:7.5px">
									<label for="datasetAbstract">Authors (one per line)</label>
									<span style="float:right;color:gray">shift-enter for new line</span>
									<textarea class="form-control" name="authors" style="height:352px;resize:none;" id="authors"><g:join in="${versionInstance.authors}" delimiter="\r\n"/></textarea>
								</div>
							</div>
							<div style="height:307px">
								<div style="float:left;height:307px;width:50%;padding-right:7.5px">
									<label for="institutions">Institutions (one per line)</label>
									<span style="float:right;color:gray">shift-enter for new line</span>
									<textarea class="form-control" id="institutionsList" name="institutionsList" style="height:283px;resize:none"><g:join in="${versionInstance.institutions*.canonicalName.sort()}" delimiter="\r\n"/></textarea>
								</div>
								<div style="float:left;width:50%;padding-left:7.5px">
									<label for="epubDate">Search for Institutions</label>
									<g:field class="form-control" type="text" id="institutionSearch" name="institutionSearch" style="margin-bottom:8px" />
									<g:select class="form-control" id="institutionSearchResults" name="institutionSearchResults" from="" multiple="true" style="margin-bottom:8px;height:199px"/>
									<button type="button" class="btn btn-default" onclick="addToInstitutions()">
										 <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;&nbsp;Add Selected
									</button>
									<span style="float:right;color:gray">control-click to select multiple</span>
								</div>
							</div>
						</div>
						<script>
							function updatePubMedButton(textfield) {
								if (textfield.value) {
									$('#pubMedDataModalButton').removeAttr('disabled');
								}
								else {
									$('#pubMedDataModalButton').attr('disabled','disabled');
								}
							};

							var pubMedDoi = '';
							var pubMedJournal = '';
							var pubMedAuthors = '';
							var pubMedAbstract = '';
							
							function fetchPubMedData() {
								$('#pubMedDataLoading').show();
								$('#pubMedData').hide();
								$('#pubMedError').hide();
								$('#populatePubMedButton').attr('disabled','disabled');
								
								pubMedDoi = '';
								pubMedJournal = '';
								pubMedAuthors = '';
								pubMedAbstract = '';
								
								$.get("${createLink(action: 'getPubMedData')}/" + $('#pmidField').val(), function(data, status){
									if (data.errorMessage) {
										$('#pubMedError').html(data.errorMessage);
										$('#pubMedDataLoading').hide();
										$('#pubMedError').show();
									}
									else {
										$('#pubMedDoi').text(data.doi);
										$('#pubMedJournal').text(data.journalName);
										$('#pubMedAuthors').html(data.authorlist.join('<br>'));
										$('#pubMedAbstract').text(data.studyAbstract);

										pubMedDoi = data.doi;
										pubMedJournal = data.journalName;
										pubMedAuthors = data.authorlist.join('\r\n');
										pubMedAbstract = data.studyAbstract;

										$('#populatePubMedButton').removeAttr('disabled');
										$('#pubMedDataLoading').hide();
										$('#pubMedData').show();
									}
								});
								updateRecentActivity();
							};
							function populatePubMedData() {
								$('#doi').val(pubMedDoi);
								$('#publicationName').val(pubMedJournal);
								$('#authors').val(pubMedAuthors);
								$('#studyAbstract').val(pubMedAbstract);
							};
						</script>
						<div id="pubMedDataModal" class="modal fade" role="dialog">
							<div class="modal-dialog" style="width:1000px">
								<div class="modal-content">
									<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title">PubMed Data</h4>
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
											<button type="button" style="width:87px" class="btn btn-default" data-toggle="modal" data-target="#pubMedDataModal">Cancel</button>
											<button type="button" id="populatePubMedButton" class="btn btn-primary" data-toggle="modal" data-target="#pubMedDataModal" onclick="populatePubMedData()" disabled>Use this data</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</g:if>

					<div id="ownership" class="tab-pane whitetab" role="tabpanel" style="height:730px">
						<g:if test="${datasetInstance instanceof Study}">
						<div style="display:table;width:1098px">
							<div class="form-group pull-left ${hasErrors(bean: datasetInstance, field: 'owners', 'error')} " style="width:33%">
								<label data-toggle="tooltip" data-placement="right" title="Owners are users who have ownership rights to your study data in BALSA, generally your co-authors. They may access the edit page, upload data, and handle submission of the study. By default your username will already be listed here. Note that owners you add will still need to agree to the Data Submission Terms before they have these permissions. They are added by their usernames, which means they must already have an account in BALSA (or have logged into BALSA at least once if they are using an HCP account).">
									Owners (one per line) <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
								</label>
								<textarea class="form-control" id="ownerNames" name="ownerNames" rows="12" style="max-width: 100%; resize: none;"><g:join in="${datasetInstance.owners*.username.sort()}" delimiter="\r\n"/></textarea>
							</div>
							
							<div class="form-group pull-left" style="width:34%;padding-right:15px;padding-left:15px">
								<label for="searchTerm">Search Users by Name or Email</label><br>
								<div style="display:table;margin-bottom:4px">
									<input class="form-control" type="text" id="search_text" name="search_text" style="display:table-cell;width:248px;margin-right:8px"/>
									<button class="btn btn-primary" type="button" style="display:table-cell;width:87px;vertical-align:top" onclick="userSearch()">Search</button>
								</div>
								<span style="color:gray">control-click to unselect or select multiple</span>
								<select class="form-control" id="usernames" name="usernames" multiple style="height:155px;margin-bottom:8px;overflow-y:hidden"></select>
								<button class="btn btn-default pull-left" type="button" onclick="addToOwners()"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;&nbsp;Add Selected</button>
								<button class="btn btn-default pull-right" type="button" onclick="addToViewers()">Add Selected&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></button>
							</div>
							
							<div class="form-group pull-left ${hasErrors(bean: datasetInstance, field: 'viewer', 'error')} " style="width:33%">
								<label data-toggle="tooltip" data-placement="right" title="Viewers are users who you would like to be able to view and download study data prior to that data being made publically available, allowing for reviewers. They are added by their usernames, which means they must already have an account in BALSA (or have logged into BALSA at least once if they are using an HCP account).">
									Viewers (one per line) <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
								</label>
								<textarea class="form-control" id="viewerNames" name="viewerNames" rows="12" style="max-width: 100%; resize: none"><g:join in="${datasetInstance.viewers*.username.sort()}" delimiter="\r\n"/></textarea>
							</div>
						</div>
						</g:if>
						<div class="form-group ${hasErrors(bean: datasetInstance, field: 'accessAgreement', 'error')} ">
							<label data-toggle="tooltip" data-placement="right" title="You may select (or create with the New Data Access Terms section below) a set of terms to which a user must agree before they download your study data.">
								Data Access Terms <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
							</label>
							<div class="panel panel-default">
								<div class="panel-body" style="padding-top:12px;padding-bottom:3px">
									<g:each in="${Terms.findAllByTitleNotEqual('Terms and Conditions for Uploading Data to the Washington University (WU) BALSA Database')}" var="terms">
									<div class="checkbox" style="margin-top:0">
										<label>
											<input type="checkbox" id="accessAgreements" name="accessAgreements" value="${terms.id}" <g:if test="${datasetInstance.accessAgreements.contains(terms)}">checked</g:if>>
											<span style="padding-right:5px">${terms.title}</span>
		
										</label>
										<a data-toggle="modal" data-target="#${terms.id}TermsModal">preview</a>
										<div id="${terms.id}TermsModal" class="modal fade" role="dialog">
											<div class="modal-dialog" style="width:1000px">
												<div class="modal-content">
													<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
														<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
														<h4 class="modal-title">${terms.title}</h4>
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
						
						<div class="form-group">
							<label>New Data Access Terms</label>
							<div id="customTermsTitleDisplay" style="margin-bottom:11px">${datasetInstance.customTermsTitle ?: 'No new terms'}</div>
							<button type="button" class="btn btn-default" data-toggle="modal" data-target="#customTermsModal">Add/Edit New DAT</button>
						</div>
					</div>
					
					<div id="presentation" class="tab-pane whitetab" role="tabpanel" style="height:730px">
						<div class="form-group">
							<label data-toggle="tooltip" data-placement="right" title="Here you can change the order in which your scene files are displayed on your study page.">
								Scene File Order <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
							</label>
							<span style="float:right;color:gray">drag and drop boxes to change order</span>
							<input type=hidden id="sceneFileOrder" name="sceneFileOrder" />
							<div class="panel panel-default">
								<g:if test="${versionInstance.sceneFiles().size() == 0}">
								<div class="panel-body">
									No scene files yet
								</div>
								</g:if>
								<ul id="sceneFileSort" class="list-group">
									<g:each in="${versionInstance.sceneFiles()}" var="sceneFile">
									<li class="list-group-item" id="${sceneFile.filename}"><span class="glyphicon glyphicon-sort"></span>&nbsp;&nbsp; ${sceneFile.label ?: sceneFile.filename} </li>
									</g:each>
								</ul>
							</div>
						</div>
						
						<div class="form-group ${hasErrors(bean: datasetInstance, field: 'focusScene', 'error')} " >
							<label for="focusScene" data-toggle="tooltip" data-placement="right" title="Here you can select a single scene preview to be the starting point in the scene preview carousel, allowing you to specify an exemplar image for your study.">
								Focus Scene <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="color:darkgray"></span>
							</label>
							<div>
								<div id="focusScenePreview" class="pull-left scalingBox" style="width:233px;height:151px;margin-right:15px"></div>
								<g:select class="form-control" style="width:850px;display:inline-block" id="focusScene" name="focusScene" from="${versionInstance.scenes()}" optionKey="${{ scene -> "${scene.sceneLine.id}\" data-sceneId=\"${scene.id}"}}" optionValue="name" />
							</div>
						</div>
					</div>
					<div id="customTermsModal" class="modal fade" role="dialog">
						<div class="modal-dialog" style="width:1000px">
							<div class="modal-content">
								<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title">Custom Data Access Terms</h4>
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
										<button type="button" style="width:87px" class="btn btn-default" data-toggle="modal" data-target="#customTermsModal" onclick="customTermsCancel()">Cancel</button>
										<button type="button" style="width:87px" class="btn btn-primary" data-toggle="modal" data-target="#customTermsModal" onclick="customTermsOkay()">Okay</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</g:formRemote>
			</div>
		</div>
		
		<script>
		$(function() {
			$('.input-group').datetimepicker();
		});

		$('#sceneFileSort').sortable({update: function(event, ui) {updateSceneFileOrder();} });

		function updateSceneFileOrder() {
			$('#sceneFileOrder').val($('#sceneFileSort').sortable("toArray"));
		}
		updateSceneFileOrder();

		$('#focusScene').change(function(){
			var sceneId = $(this).children('option:selected').data('sceneid');
			if (sceneId) {
				$('#focusScenePreview').html('<img src="/scene/image/' + sceneId + '"/>');
			}
		});
		if ('${versionInstance.focusScene?.id}') {
			$('#focusScene option[value="${versionInstance.focusScene?.id}"]').prop('selected', true);
		}
		$('#focusScene').trigger('change');
		
		$('#fileTable').tablesorter({
			sortList: [[0,0]]
		});

		$('#search_text').keypress(function(e){
			if (e.keyCode == 13) { 
				e.preventDefault();
				userSearch();
				return false;
			}
		});

		function userSearch() {
			var search_text = $('#search_text').val();
			$.get(
				"${createLink('controller':'profile','action':'search')}",
				{searchText: search_text},
				function(data) {
					$('#usernames').find('option').remove()
					$.each(data, function (i, username) {
						$('#usernames').append($('<option>', {value: username, text: username}));
					});
				}
			);
			updateRecentActivity();
		}

		function addToOwners() {
			$('#usernames option:selected').each(function() {
				$('#ownerNames').val($('#ownerNames').val() + ($('#ownerNames').val() ? '\r\n' : '') + $(this).text());
			});
		}

		function addToViewers() {
			$('#usernames option:selected').each(function() {
				$('#viewerNames').val($('#viewerNames').val() + ($('#viewerNames').val() ? '\r\n' : '') + $(this).text());
			});
		}

		$('#institutionSearch').keypress(function(e){
			if (e.keyCode == 13) { 
				e.preventDefault(); 
				return false;
			}
		});
		
		$('#institutionSearch').keyup(function(){
			var search_text = $('#institutionSearch').val();
			$.get(
				"${createLink('controller':'institution','action':'search')}",
				{searchText: search_text},
				function(data) {
					$('#institutionSearchResults').find('option').remove()
					$.each(data, function (i, institutionName) {
						$('#institutionSearchResults').append($('<option>', {value: institutionName, text: institutionName}));
					});
				}
			);
			updateRecentActivity();
		});
		
		function addToInstitutions() {
			$('#institutionSearchResults option:selected').each(function() {
				$('#institutionsList').val($('#institutionsList').val() + ($('#institutionsList').val() ? '\r\n' : '') + $(this).text());
			});
		}

		$('#publicationSearch').keypress(function(e){
			if (e.keyCode == 13) { 
				e.preventDefault(); 
				return false;
			}
		});
		
		$('#publicationSearch').keyup(function(){
			var search_text = $('#publicationSearch').val();
			$.get(
				"${createLink('controller':'publication','action':'search')}",
				{searchText: search_text},
				function(data) {
					$('#publicationSearchResults').find('option').remove()
					$.each(data, function (i, publicationName) {
						$('#publicationSearchResults').append($('<option>', {value: publicationName, text: publicationName}));
					});
				}
			);
			updateRecentActivity();
		});
		
		function setPublication() {
			$('#publicationSearchResults option:selected').each(function() {
				$('#publicationName').val($(this).text());
			});
		}

		var customTermsTitle=$('#customTermsTitle').val();
		var customTermsContent=$('#customTermsContent').val();

		function customTermsCancel() {
			$('#customTermsTitle').val(customTermsTitle);
			$('#customTermsContent').val(customTermsContent);
		}

		function customTermsOkay() {		
			customTermsTitle=$('#customTermsTitle').val();
			customTermsContent=$('#customTermsContent').val();
			if (customTermsTitle) {
				$('#customTermsTitleDisplay').text(customTermsTitle);
			}
			else {
				$('#customTermsTitleDisplay').text('No custom terms');
			}
		}
		
		function successfulSave(data) {
			bootbox.alert(data);
		}
		</script>
	</body>
</html>
