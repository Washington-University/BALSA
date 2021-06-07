<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${submissionTerms.title}</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">${submissionTerms.title}</div>
				<div class="btn-group btn-bar">
					<sec:ifLoggedIn>
					<g:if test="${alreadyAgreed}">
					<button type="button" class="btn btn-light float-right" disabled="disabled"><span class="text-success">&#10003;</span> Agreed</button>
					</g:if>
					<g:else>
					<button type="button" class="btn btn-primary float-right" onclick="$.ajax('agree/${submissionTerms.id}').done(function() {window.location.reload(true);})">Agree</button>
					</g:else>
					</sec:ifLoggedIn>
					<sec:ifNotLoggedIn>
					<button type="button" class="btn btn-light float-right" disabled="disabled">Login to Agree</button>
					</sec:ifNotLoggedIn>
					<button class="btn btn-light float-right" type="button" onclick="printDiv('termsContents')"/>Print</button>
				</div>
				<div class="card-body">
					<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${submissionTerms}" field="contents"/></g:encodeAs>
				</div>
			</div>
		</div>
	</body>
</html>