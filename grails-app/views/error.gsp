<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Error</div>
				<div class="card-body">
					<g:if env="development">
						<p>
							<g:renderException exception="${exception}" />
						</p>
					</g:if>
					<g:else>
						<p>
							BALSA has encountered a problem in handling your request.
							If you continue to experience this issue,
							<a href="#" data-toggle="modal" data-target="#reportIssuesModal">
								please contact our technical personnel.
							</a>
						</p>
					</g:else>
				</div>
			</div>
		</div>
	</body>
</html>
