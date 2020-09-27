<li id="seeker${it.id}">
	${it.username}
	<g:remoteLink action="approve" id="${approvalInstance.id}" params="${[userId:it.id]}" onSuccess="approvalGranted(${it.id}, data);updateRecentActivity();">
		<button type="button" class="btn btn-primary">Grant Access Request</button>
	</g:remoteLink>	
	<g:remoteLink action="deny" id="${approvalInstance.id}" params="${[userId:it.id]}" onSuccess="approvalDenied(${it.id});updateRecentActivity();">
		<button type="button" class="btn btn-primary">Deny Access Request</button>
	</g:remoteLink>
</li>