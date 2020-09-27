<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${submissionTerms.title}</title>
	</head>
	<body>
		<div role="main">
			<h3>${submissionTerms.title}</h3>
			<div class="well" style="overflow: hidden;">
				<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${submissionTerms}" field="contents"/></g:encodeAs>
				<div>
					<sec:ifLoggedIn>
					<g:if test="${alreadyAgreed}">
						<button type="button" class="btn btn-default pull-right" disabled="disabled"><span class="glyphicon glyphicon-ok" style="color:green"></span> Agreed</button>
					</g:if>
					<g:else>
					<g:remoteLink controller="terms" action="agree" id="${submissionTerms.id}" onSuccess="window.location.reload(true)">
						<button type="button" id="submissionAgreeButton" class="btn btn-primary pull-right">I Agree</button>
					</g:remoteLink>
					</g:else>
					</sec:ifLoggedIn>
					<sec:ifNotLoggedIn>
						<button type="button" class="btn btn-primary pull-right" disabled="disabled">Login/Register to Agree</button>
					</sec:ifNotLoggedIn>
				</div>
			</div>
		</div>
	</body>
</html>