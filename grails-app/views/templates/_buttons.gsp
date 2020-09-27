<g:if test="${item?.hasAccess()}">
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

<g:if test="${item?.terms()?.numberOfTerms > 0}">
	<button type="button" class="btn btn-default" data-toggle="modal" data-target="#termsModal">Data Use Terms</button>
</g:if>