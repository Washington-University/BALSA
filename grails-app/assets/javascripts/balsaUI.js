// stuff to do on page load
let chart = require('chart.js');

window.onload = function() {
	$('.tablesorter.filterer').each(function(index, value) {
		tablesorterInit(value);
	});
	$('.tablesorter:not(.filterer)').each(function(index, value) {
		tableInit(value);
	});
	$('.pager').each(function(index, value) {
		pagerInit(value);
	});
	
	$('.ajaxForm').each(function(index, value) {
		$(value).submit(function(event) {
			ajaxSubmitForm(event);
		});
	});
	
	$('.datasetLabels').each(function(index, value) {
		$(value).change(function(event) {
			adjustDatasetTabLabels();
		});
	});
	
	$('#addFilterButton').click(function() {
		addSearchFilter();
	});
	
	$('[data-toggle="popover"]').popover();
	
	if ($('#latestNews').length) {
		$.get("/news/latest", 
			function(data, status){
				$('#latestNews').html(data);
			}
		);
		updateRecentActivity();
	}
	
	if ($('#searchForm').length) {
		$('.searchRefresh').each(function(index, value) {
			$(value).change(function(event) {
				refreshSearchResults();
			});
		});
		$('.searchRefreshText').keyup(function() {
			refreshSearchResults();
		});
		
		refreshSearchResults();
	}
	
	if ($('#downloadModal').length) {
		$.get('/download/downloadModalContents/' + $('#datasetIdField').val() + '?version=' + $('#versionIdField').val(),
			function(data, status) {
				$('#downloadModalContents').html(data);

				// turn on indeterminate checkboxes and set up triggers
				IndeterminateCheckbox.init();
				$('input[type="checkbox"].indeterminate-checkbox').change(function() {
					checkFiles();
				});
				$('input[name=file]').change(function() {
					estimateSize();
				});

				// check initial item and all item descendents and dependent files
				var initialId = $('#itemIdField').val();
				$('#' + initialId).prop('checked', true);
				$('#' + initialId).trigger('change');

				// initialize table sorter
				tablesorterInit($('#downloadFileTable'));
				pagerInit($('#downloadFileTable'));

				// initialize tooltips
				$('[data-toggle="popover"]').popover();

				// if initial item is file rather than hierarchy, change tabs
				if ($('#' + initialId).attr("class") !== 'indeterminate-checkbox') {
					$('#filesTabTab').tab('show');
				}
			}
		);
	}
	
	if ($('#downloadStatsModal').length) {
		$.get("/" + $('#datasetTermField').val() + "/downloadStats/" + $('#datasetIdField').val(), function(data, status){
			$('#downloadStatsModalContents').html(data);
		});
	}
	
	if($('#logoutButton').length) {
		setInterval(checkRecentActivity, 5000);
		function checkRecentActivity() {
			var activityCookie = document.cookie.match('(^|;) ?balsaRecentActivityTime=([^;]*)(;|$)');
			var mostRecentActivity = activityCookie ? activityCookie[2] : 0;

			if (mostRecentActivity && !timeoutAlert && Date.now() - mostRecentActivity > 1740000) {
				timeoutAlert = bootbox.alert({
					message: "You will soon be automatically logged out due to lack of activity. Do you wish to remain logged in?",
					buttons: {
						ok: {label: 'Yes'}
					},
					callback: function() {
						$.get("/login/keepAlive");
						updateRecentActivity();
					}
				});
			}
			else if (mostRecentActivity && timeoutAlert && Date.now() - mostRecentActivity < 1740000) {
				timeoutAlert.modal('hide');
				timeoutAlert = null;
			}
			if (mostRecentActivity && Date.now() - mostRecentActivity > 1860000) {
				location.reload(true);
			}
		}
	}

	$('.accordion .card:first-child .card-body').addClass('show');
	
	$('#sceneFileSort').sortable({update: function(event, ui) {updateSceneFileOrder();} });

	$('.rlistcaret').each(function() {
		$(this).click(function() {
			$(this).siblings('.rlist').toggleClass('active');
			$(this).toggleClass('rlistcaret-down')
		});
	});

	updateRecentActivity();
};

// utility functions
function printDiv(divName) {
	 w=window.open();
	 w.document.write($('#'+divName).html());
	 w.print();
	 w.close();
}

function gotoDatasetVersion(selectObject) {
	var url = selectObject.value;
	if (url) {
		window.location = url;
	}
	return false;
}

function updateRecentActivity() {			
	var d = new Date();
	var t = d.getTime();
	d.setHours(d.getHours() + 1);
	document.cookie = "balsaRecentActivityTime=" + t + ";path=/;expires=" + d.toUTCString();
}


var timeoutAlert = null;

// approval functions
// <editor-fold>
function approvalGranted(userId, data) {
	$("#seeker"+userId).remove();
	$("#granted-approvals").append(data);
}

function approvalDenied(userId) {
	$("#seeker"+userId).remove();
}

function approvalRevoked(userId) {
	$("#approved"+userId).remove();
}
//</editor-fold>





// tablesorter functions
// <editor-fold>
function tableInit(table) {
	$(table).tablesorter({ 
		theme: 'bootstrap', 
		sortList: $(table).data('sort'),
		widthFixed: false
	});
}

function tablesorterInit(table) {
	$(table).tablesorter({ 
		theme: 'bootstrap', 
		sortList: $(table).data('sort'),
		widthFixed: false, 
		widgets: ['filter'],
		widgetOptions: {
			filter_reset: '.reset',
			filter_cssFilter: $(table).data('cssfilter')
		}
	});
}

function pagerInit(table) {
	$(table).tablesorterPager({
		container: $(table).find('.ts-pager'),
		cssGoto: '.pagenum',
		removeRows: false,
		output: '{startRow} - {endRow} / {filteredRows} ({totalRows})'
	});
	var rows = $(table).data('rows') || '20';
	$(table).find('.pagesize').val(rows);
	$(table).find('.pagesize').change();
}
// </editor-fold>


function newsToggle() {
	$('#collapseNews').collapse('toggle');
	if($('#newsToggle').text() === "show more")
	{
		$('#newsToggle').text("show less"); 
	}
	else
	{
		$('#newsToggle').text("show more"); 
	}
}



// bunch of form submissions on different pages
function ajaxSubmitForm(event) {
	event.preventDefault();
	
	var form = $(event.target);
	$.ajax({
		method: "POST",
		url: window.location.origin + form.attr('action'),
		data: form.serialize(),
		success: function(data) {
			eval(form.data('success'));
			updateRecentActivity();
		},
		error: function(request, status, error) {
			eval(form.data('error'));
			updateRecentActivity();
		}
	});
}

function reportingSuccess() {
	$('#reportIssuesModal').modal('hide');
	bootbox.alert('Message sent.');
	$('#issueTitle').val('');
	$('#issueContents').val('');
}

function sendingSuccess() {
	$('#contactUsModal').modal('hide');
	bootbox.alert('Message sent.');
}

function curatorSendingSuccess() {
	$('#contactCuratorsModal').modal('hide');
	bootbox.alert('Message sent.');
}

function passwordChanged() {
	$('#changePasswordModal').modal('hide');
	bootbox.alert('Password changed.');
}

// download modal functions
// <editor-fold>
function checkFiles() {
	var checkboxes = $("input:checked[class=indeterminate-checkbox]");
	var filesToCheck = ",";
	checkboxes.each(function() {
		filesToCheck += $(this).attr('dependencies') + ",";
	});

	var files = $("input[name=file]");
	files.each(function() {
		if (filesToCheck.includes(',' + this.id + ',')) {
			$(this).prop('checked', true);
		}
		else {
			$(this).prop('checked', false);
		}
		$(this).prev().html(this.checked ? '0' : '1');
	});

	$('#downloadFileTable').trigger('update');

	estimateSize();
}

function estimateSize() {
	var totalSize = 0;
	var scenes = $("input:checked[name=scene]");
	scenes.each(function() {
		totalSize += parseInt($(this).attr('zipsize'), 10);
	});
	var files = $("input:checked[name=file]");
	files.each(function() {
		totalSize += parseInt($(this).attr('zipsize'), 10);
	});
	$('#zipsize').text(formatBytes(totalSize));
}

function formatBytes(bytes) {
	if(bytes < 1024) return bytes + " bytes";
	else if(bytes < 1048576) return(bytes / 1024).toFixed(0) + " KB";
	else if(bytes < 1073741824) return(bytes / 1048576).toFixed(1) + " MB";
	else return(bytes / 1073741824).toFixed(2) + " GB";
}

function presubmitCheck() {
	var checkboxes = $("input:checked[class=indeterminate-checkbox]");
	var filesToCheck = ",";
	checkboxes.each(function() {
		filesToCheck += $(this).attr('dependencies') + ",";
	});

	var allSupportingFiles = true;
	var files = $("input[name=file]");
	files.each(function() {
		if (filesToCheck.includes(',' + this.id + ',')) {
			if (!$(this).prop('checked')) {
				allSupportingFiles = false;
			}
		}
	});

	if (!allSupportingFiles) {
		bootbox.confirm("The items you have selected for download do not include all of the files marked as required by the scenes you have selected. This could cause problems with displaying the selected scenes. Do you wish to proceed?",
			function (result) {
				if (result) {
					$('#downloadForm').submit();
					$('#downloadModal').modal('hide');
				}
			}
		);
	}
	else {
		$('#downloadForm').submit();
		$('#downloadModal').modal('hide');
	}
}
// </editor-fold>

// carousel with thumbnails
function scrollToHighlight() {
	window.setTimeout(
		function() {
			var container = $('#thumbnails');
			if (container.length) {
				var currentScroll = container.scrollTop();
				var containerTop = container.offset().top;
				var containerBottom = containerTop + container.height();

				var elem = $('.carousel-indicators .active');
				var elemTop = elem.offset().top -35;
				var elemBottom = elemTop + elem.height() + 45;

				if (elemTop < containerTop) {
					container.animate({
						scrollTop: currentScroll - containerTop + elemTop
					}, 600);
				}

				if (elemBottom > containerBottom) {
					container.animate({
						scrollTop: currentScroll - containerBottom + elemBottom
					}, 600);
				}
			}

		}, 0);
}
scrollToHighlight();


$('.dropdown.keep-open').on({
    "shown.bs.dropdown": function() { this.closable = false; },
    "click":             function() { this.closable = true; },
    "hide.bs.dropdown":  function() { return this.closable; }
});


// Dataset Edit Page
// <editor-fold>
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

	$.get("/study/getPubMedData/" + $('#pmidField').val(), function(data, status){
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

$(function() {
	$('.datetimepicker').datetimepicker();
});

function updateSceneFileOrder() {
	$('#sceneFileOrder').val($('#sceneFileSort').sortable("toArray"));
}

function changeFocusScene() {
	var sceneId = $('#focusScene :selected').data('preview');
	if (sceneId) {
		$('#focusScenePreview').html('<img src="/scene/image/' + sceneId + '"/>');
	}
}

function userSearch() {
	var search_text = $('#search_text').val();
	if (!search_text) {
		$('#usernames').find('option').remove();
	}
	else {
		$.get(
			"/profile/search",
			{searchText: search_text},
			function(data) {
				$('#usernames').find('option').remove();
				$.each(data, function (i, username) {
					$('#usernames').append($('<option>', {value: username, text: username}));
				});
			}
		);
		updateRecentActivity();
	}
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
	if (e.keyCode === 13) { 
		e.preventDefault(); 
		return false;
	}
});

function searchInstitutions() {
	var search_text = $('#institutionSearch').val();
	$.get(
		"/institution/search",
		{searchText: search_text},
		function(data) {
			$('#institutionSearchResults').find('option').remove();
			$.each(data, function (i, institutionName) {
				$('#institutionSearchResults').append($('<option>', {value: institutionName, text: institutionName}));
			});
		}
	);
	updateRecentActivity();
}

function addToInstitutions() {
	$('#institutionSearchResults option:selected').each(function() {
		$('#institutionsList').val($('#institutionsList').val() + ($('#institutionsList').val() ? '\r\n' : '') + $(this).text());
	});
}

function searchPublications() {
	var search_text = $('#publicationSearch').val();
	$.get(
		"/publication/search",
		{searchText: search_text},
		function(data) {
			$('#publicationSearchResults').find('option').remove();
			$.each(data, function (i, publicationName) {
				$('#publicationSearchResults').append($('<option>', {value: publicationName, text: publicationName}));
			});
		}
	);
	updateRecentActivity();
}

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

function toggleToken(studyId) {
	if ($('#viewToken').prop("checked")) {
		$.ajax({
			url: window.location.origin + '/study/tokenOn/' + studyId,
			success: function(data) {
				$('#viewLink').val(window.location.origin + '/study/view/' + studyId + '?token=' + data);
				updateRecentActivity();
			},
			error: function(request, status, error) {
				updateRecentActivity();
			}
		});
	}
	else {
		$.ajax({
			url: window.location.origin + '/study/tokenOff/' + studyId,
			success: function(data) {
				$('#viewLink').val('disabled');
				updateRecentActivity();
			},
			error: function(request, status, error) {
				updateRecentActivity();
			}
		});
	}
}

function copyLinkToClipboard() {
	let link = $('#viewLink').val();
	if (link && link != 'disabled') {
		navigator.clipboard.writeText(link).then(
			function() {
				bootbox.alert('Link copied to clipboard.');
			}
		);
	}
}

function successfulSave(data) {
	bootbox.alert(data);
}
// </editor-fold>


// Edit Scenes Page Functions
// <editor-fold>
function filterTags() {
	var category = $('#filterCategory').val();
	
	$('#editScenesForm ' + (category ? '.category' + category.replaceAll(' ', '') : '') + '.tagCategory:not(:visible)').removeClass('d-none');
	
	if (category) {
		$('.tagCategory:not(.category' + category.replaceAll(' ', '') + ')').addClass('d-none');
	}
	
	var tags = [];
	$('#editScenesForm .tagCategory:visible').each(function() {
		tags.push($(this).data('tag'));
	});
		
	$('#tagDisplay ' + (category ? '.category' + category.replaceAll(' ', '') : '') + ':not(:visible)').each(function() {
		if (!tags.includes($(this).data('tagvalue'))) {
			$(this).removeClass('d-none');
		}
	});
	
	for (var i = 0; i < tags.length; i++) {
		var tagitem = $('#tagDisplay [data-tagvalue="' + tags[i] + '"]:visible');
		tagitem.addClass('d-none');
	}
}

function changeSceneListItem(item) {
	$('.scene-list-item.active').removeClass('active');
	$(item).addClass('active');
}

function viewDatasetData() {
	$('.sceneData:not(.d-none)').addClass('d-none');
	$('#datasetData').removeClass('d-none');
	filterTags();
}

function viewSceneFileData(sceneFileId) {
	$('.sceneData:not(.d-none)').addClass('d-none');
	$('#sceneFileData' + sceneFileId).removeClass('d-none');
	filterTags();
}

function viewSceneData(sceneId) {
	$('.sceneData:not(.d-none)').addClass('d-none');
	$('#sceneData' + sceneId).removeClass('d-none');
	filterTags();
}

function addTag(tagData) {
	var target = $('.sceneData:not(.d-none)');
	var tagContainers;
	if (target.attr('id') === 'datasetData') {
		// add tag to any tagContainer that doesn't currently have a copy
		tagContainers = $('.tagContainer:not(:has([data-tag="' + tagData + '"]))');
	}
	else if (target.attr('id').startsWith('sceneFileData')) {
		// add tag to any tagContainer for sceneFile that doesn't currently have a copy
		tagContainers = $('#' + target.attr('id') + ' .tagContainer:not(:has([data-tag="' + tagData + '"])), ' + 
				'[data-scenefile="' + target.attr('id') + '"] .tagContainer:not(:has([data-tag="' + tagData + '"]))');
	}
	else {
		// add tag to tagContainer if doesn't currently have a copy
		var tagContainers = $('#' + target.attr('id') + ' .tagContainer:not(:has([data-tag="' + tagData + '"]))');
	}
	tagContainers.append($('#tagTemplate').html().replace(/TAGVALUE/g, tagData).replace(/CATEGORYVALUE/g, tagData.substring(0, tagData.indexOf(':')).replaceAll(' ', '')));
	tagContainers.find('.tagString').val(function() {return (this.value + (this.value ? ',' : '') + tagData);});
	
	// if scene, check if all scenes in scene file have new tag, if so append to scene file
	if (target.attr('id').startsWith('sceneData')) {
		if ($('[data-scenefile="' + target.data('scenefile') + '"]:not(:has([data-tag="' + tagData + '"]))').length === 0) {
			$('#' + target.data('scenefile') + ' .tagContainer').append($('#tagTemplate').html().replace(/TAGVALUE/g, tagData).replace(/CATEGORYVALUE/g, tagData.substring(0, tagData.indexOf(':')).replaceAll(' ', '')));
		}
	}
	// if scene or scene file, check if all scene files have new tag, if so append to dataset
	if (target.attr('id').startsWith('sceneData') || target.attr('id').startsWith('sceneFileData')) {
		if ($('.sceneFileData:not(:has([data-tag="' + tagData + '"]))').length === 0) {
			$('#datasetData .tagContainer').append($('#tagTemplate').html().replace(/TAGVALUE/g, tagData).replace(/CATEGORYVALUE/g, tagData.substring(0, tagData.indexOf(':')).replaceAll(' ', '')));
		}
	}
	filterTags();
}

function removeTag(tagData) {
	var target = $('.sceneData:not(.d-none)');
	var tagContainers;
	if (target.attr('id') === 'datasetData') {
		// remove all copies of tag
		tagContainers = $('.tagContainer:has([data-tag="' + tagData + '"])');
	}
	else if (target.attr('id').startsWith('sceneFileData')) {
		// remove all copies of tag from scene file, scenes for scene file, and dataset
		tagContainers = $('#' + target.attr('id') + ':has([data-tag="' + tagData + '"]), ' +
				'[data-scenefile="' + target.attr('id') + '"]:has([data-tag="' + tagData + '"]), ' +
				'#datasetData:has([data-tag="' + tagData + '"])');
	}
	else {
		// remove tag for scene, sceneFile, and dataset
		tagContainers = $('#' + target.attr('id') + ':has([data-tag="' + tagData + '"]), ' +
				'#' + target.data('scenefile') + ':has([data-tag="' + tagData + '"]), ' +
				'#datasetData:has([data-tag="' + tagData + '"])');
	}
	tagContainers.find('[data-tag="' + tagData + '"]').remove();
	tagContainers.find('.tagString').val(function() {return (this.value.substring(0, this.value.indexOf(tagData)) + this.value.substring(this.value.indexOf(tagData) + tagData.length)).replace(',,',',').replace(/(^,)|(,$)/g, "");});
	filterTags();
}

//function hideTagsAlreadyInScene() {
//	var currentScene = $('.in.scenePanel');
//	if (currentScene.attr('data-sceneId')) {
//		var tagSelector = $("[name='" + currentScene.attr('data-sceneId') + "tags']").each(function() { 
//			$('#tagDisplay').find('div[data-tagValue="' + this.value + '"]').hide();
//		});
//	}
//	else {
//		filterTags();
//	}
//}
// </editor-fold>


// upload page functions
// <editor-fold>
function uploadFile() {
	$('#uploadAccordionButton').click();
	var file = $('#fileUpload').prop('files')[0];
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/" + $('#fileUpload').data('controller') + '/handleUpload/' + $('#fileUpload').data('datasetid')); 
	xhr.setRequestHeader("X-File-Name", encodeURIComponent((file.name) ? file.name : file.fileName));
	xhr.setRequestHeader("X-File-Size", (file.size) ? file.size : file.fileSize);
	xhr.upload.addEventListener("progress", function(event) { $('#uploadProgressBar').width((event.loaded / event.total) * 100 + '%'); }, false);
	xhr.upload.addEventListener("load", function(event) { $('#processUploadButton').removeClass('disabled'); $('#processUploadButton').removeClass('btn-secondary'); $('#processUploadButton').addClass('btn-primary'); }, false);
	xhr.send(file);
}


function uploadMyelinFile() {
	$('#uploadAccordionButton').click();
	var file = $('#fileUpload').prop('files')[0];
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/" + $('#fileUpload').data('controller') + '/handleUpload?dirName=' + $('#fileUpload').data('dir') + '&dirPass=' + $('#dirPass').val());
	xhr.setRequestHeader("X-File-Name", encodeURIComponent((file.name) ? file.name : file.fileName));
	xhr.setRequestHeader("X-File-Size", (file.size) ? file.size : file.fileSize);
	xhr.upload.addEventListener("progress", function(event) { $('#uploadProgressBar').width((event.loaded / event.total) * 100 + '%'); }, false);
	xhr.upload.addEventListener("load", function(event) { setTimeout(() => { location.reload(); }, 500) }, false);
	xhr.send(file);
}
// </editor-fold>



// terms modal functions
var agreementChanged = false;

function agree(id) {
	$('#title'+id).html('&#10003;');
	$('#title'+id).removeClass('text-danger');
	$('#title'+id).removeClass('text-warning');
	$('#title'+id).addClass('text-success');
	$('#button'+id).removeClass('btn-primary');
	$('#button'+id).addClass('btn-light');
	$('#button'+id).attr('disabled','disabled');
	$('#button'+id).html('<span class="text-success font-weight-bold">&#10003;</span> Agreed');
}
function checkTermsReload() {
	if ($('.terms-title .text-danger').length === 0) {
		location.reload();
	}
}

$('#termsModal').on('hidden.bs.modal', function(e) {if (agreementChanged) {location.reload(true);} });





// login modal
$('#loginModal').on('shown.bs.modal', function () {
	$('#username').focus();
});

function isLoggedIn(data) {
	if (data.success) {
		location.reload(true);
	}
	else if (data.error) {
		$('#loginError').slideDown(500);
		//$('#loginErrorLabel').text(data.error);
	}
}

function sendPasswordResetEmailModal() {
	if ($('#usernameModal').val()) {
		$.ajax({
			method: "POST",
			url: "register/forgotPassword",
			data: "username="+$('#usernameModal').val(),
			success: function(data) {
				$("#loginModalEmailSent").show();
			}
		});
	}
	else {
		bootbox.alert('Please enter your username before clicking the link.');
	}
}

function sendPasswordResetEmail() {
	if ($('#j_username').val()) {
		$.ajax({
			method: "POST",
			url: "register/forgotPassword",
			data: "username="+$('#j_username').val(),
			success: function(data) {
				$("#loginEmailSent").show();
			}
		});
	}
	else {
		bootbox.alert('Please enter your username before clicking the link.');
	}
}

// search functions
var timeout = null;
$('#keyword').keydown(function() {
	clearTimeout(timeout);
	timeout = setTimeout(function () {refreshSearchResults();}, 750);
});

function refreshSearchResults() {
	$.ajax({
		method:'POST',
		url:'search/datasetSearch',
		data:$('#searchForm').serialize(), 
		success:function(data){
			$('#datasetSearchResults').html(data);
			populateCarousel();
		},
		error:function(request, status, error){}});
	$.ajax({
		method:'POST',
		url:'search/sceneSearch',
		data:$('#searchForm').serialize(), 
		success:function(data){
			$('#sceneSearchResults').html(data);
		},
		error:function(request, status, error){}});
	return false;
}

function adjustDatasetTabLabels() {
	var studiesChecked = $('#searchDomainStudies').is(':checked');
	var referenceChecked = $('#searchDomainReference').is(':checked');
	if (studiesChecked) {
		$('#studiesLabel').show();
	}
	else {
		$('#studiesLabel').hide();
	}
	
	if (referenceChecked) {
		$('#referenceLabel').show();
	}
	else {
		$('#referenceLabel').hide();
	}
	
	if (studiesChecked && referenceChecked) {
		$('#spacerLabel').show();
	}
	else {
		$('#spacerLabel').hide();
	}
}

function addSearchFilter() {
	$.get('/search/searchFilter', function(data) {
		$('#searchFilters').append(data);
		$('.categorySelect').change(function() {
			$(this).next().load('/tagging/categoryOptions', {category:$(this).val()}, 
				function(){
					$('.searchRefresh').change(function() {
						refreshSearchResults();
					});
					$('.searchRefreshText').on('input', 'input:text', function() {
						refreshSearchResults();
					});
					$(this).children().first().focus();
				});
		});
		$('.removeFilterButton').click(function() {
			$(this).parent().remove();
			refreshSearchResults();
		});
	});
	updateRecentActivity();
}


function populateCarousel() {
	$('.carouselToPopulate').each(function(index, value) {
		var datasetId = value.dataset['datasetid'];
		var thumbnailSize = parseInt(value.dataset['thumbnailsize']);
		populateCarouselContents(datasetId, thumbnailSize);
	});
}

function populateCarouselContents(versionId, thumbnailSize) {
	$.ajax({
		url:'search/carouselContents/' + versionId + '?ts=' + thumbnailSize,
		success:function(data,textStatus){$('#innerCarousel' + versionId).html(data); loadSlideImage(versionId); changeSceneFileVisibility();},
		error:function(XMLHttpRequest,textStatus,errorThrown){}
	});
}

function loadPrevSlideImage(versionId) {
	var slideIndex = parseInt($('#index' + versionId).val());
	if (slideIndex - 1 < 0) {
		$('#index' + versionId).val($('#lastIndex' + versionId).val());
	}
	else {
		$('#index' + versionId).val(slideIndex - 1);
	}
	loadSlideImage(versionId);
}

function loadNextSlideImage(versionId) {
	var slideIndex = parseInt($('#index' + versionId).val());
	if (slideIndex + 1 > parseInt($('#lastIndex' + versionId).val())) {
		$('#index' + versionId).val(0);
	}
	else {
		$('#index' + versionId).val(slideIndex + 1);
	}
	loadSlideImage(versionId);
}

function loadSlideImage(versionId) {
	var slideIndex = $('#index' + versionId).val();
	var box = $('#' + versionId + 'scenePreview' + slideIndex);
	var boxHtml = '<img src="/scene/image/' + box.attr('sceneImageId') + '">';
	box.html(boxHtml);
}

function changeSceneFileVisibility() {
	var value = $('#visibilityControl').val();
	if (value==="Hide") {
		$('.sceneFileAbove').removeClass('show');
	}
	if (value==="Show") {
		$('.sceneFileAbove').addClass('show');
	}
}

function addTagHandle(categoryId) {
	var handle = $('#' + categoryId + ' .tagHandle').val();
	var value = $('#' + categoryId + ' .tagValue').val();
	$('#' + categoryId + ' .handleContainer').append($('#tagHandleTemplate').html().replace(/TAGHANDLE/g, handle).replace(/TAGVALUE/g, value));
	$('#' + categoryId + ' .tagHandle').val('');
	$('#' + categoryId + ' .tagValue').val('');
}