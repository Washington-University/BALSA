<li id="approved${it.id}">
	${it.username}
	<g:remoteLink action="revoke" id="${approvalInstance.id}" params="${[userId:it.id]}" onSuccess="approvalRevoked(${it.id});updateRecentActivity();">
		<button type="button" class="btn btn-default">Revoke Access</button>
	</g:remoteLink>
</li>