<g:if test="${!(versionInstance.isSubmitted() || versionInstance.isApproved() || versionInstance.isPublic())}">
<g:if test="${versionInstance.isReadyForSubmission()}">
<g:if test="${versionInstance.dataset instanceof balsa.Study && !versionInstance.submitter}">
<button type="button" class="btn btn-light canedit" onclick="bootbox.prompt({title: 'Required Action', inputType: 'checkbox', 
	inputOptions: [{text: 'Permission is hereby granted to share data from this study with investigators who have requested access to the data and who have agreed to the Data Use Terms associated with this study.', value: 'abc'}],
	callback: function(result) {
		if (result != '') {
			bootbox.confirm({size: 'small', message: 'Submit this study for curation?', 
				callback: function(result) {
					if (result) {
						$(location).attr('href','<g:createLink action="submitForCuration" id="${versionInstance.dataset.id}" params="[version: versionInstance.id]"/>');
					}
				} 
			});
		} 
	} })">
	Submit for Curation
</button>
</g:if>
<g:else>
<button type="button" class="btn btn-light canedit" onclick="bootbox.confirm({size: 'small', message: 'Submit this study for curation?', 
	callback: function(result) {
		if (result) {
			$(location).attr('href','<g:createLink action="submitForCuration" id="${versionInstance.dataset.id}" params="[version: versionInstance.id]"/>');
		} 
	} })">
	Submit for Curation
</button>
</g:else>
</g:if>
<g:else>
<button type="button" class="btn btn-light canedit" disabled="disabled" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="Dataset has unaddressed issues.">Submit for Curation</button>
</g:else>
</g:if>

<sec:ifNotGranted roles="ROLE_CURATOR">
<g:if test="${versionInstance.isSubmitted()}">
<button type="button" class="btn btn-light canedit" onclick="bootbox.confirm({size: 'small', message: 'Withdraw this study from curation?', 
	callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="withdrawFromCuration" id="${versionInstance.dataset.id}" params="[version: versionInstance.id]"/>');} } })">
	Withdraw from Curation
</button>
</g:if>
</sec:ifNotGranted>

<g:if test="${versionInstance.isApproved()}">
<button type="button" class="btn btn-light canedit" onclick="bootbox.confirm({size: 'small', message: 'Withdraw this study from approval?', 
	callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="removeFromApproved" id="${versionInstance.dataset.id}" params="[version: versionInstance.id]"/>');} } })">
Make non-public
</button>
</g:if>

<g:if test="${versionInstance.isPublic()}">
<button type="button" class="btn btn-light canedit" onclick="bootbox.confirm({size: 'small', message: 'Make this study non-public?', 
	callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="removeFromPublic" id="${versionInstance.dataset.id}" params="[version: versionInstance.id]" />');} } })">
Make non-public
</button>
</g:if>

<sec:ifAnyGranted roles="ROLE_CURATOR,ROLE_ADMIN">
<g:if test="${versionInstance.isSubmitted()}">
<g:if test="${versionInstance.isReadyForApproval()}">
<button type="button" class="btn btn-light canedit" onclick="bootbox.confirm({size: 'small', message: 'Approve this study?', 
	callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="approve" id="${versionInstance.dataset.id}" params="[version: versionInstance.id]"/>');} } })">
	Approve
</button>
</g:if>
<g:else>
<button type="button" class="btn btn-light canedit" disabled="disabled" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="Dataset has unresolved issues.">Approve</button>
</g:else>
<button type="button" class="btn btn-light canedit" onclick="bootbox.confirm({size: 'small', message: 'Return this study to its owners for revision?', 
	callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="revise" id="${versionInstance.dataset.id}"  params="[version: versionInstance.id]"/>');} } })">
	Return for revision
</button>
</g:if>
</sec:ifAnyGranted>