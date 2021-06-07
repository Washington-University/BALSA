<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="BALSA"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
		<link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
		<g:layoutHead/>
	</head>
	<body>
		<div class="card border-0">
			<div class="card-header d-flex">
				<div id="balsaLogo">
					<a href="/">
						<g:img dir="images" file="BALSALogo.png" width="100%" height="100%" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="The Brain Analysis Library of Spatial Maps and Atlases" />
					</a>
				</div>

				<div class="float-right align-self-center ml-auto">
					<sec:ifNotLoggedIn>
					<div class="btn-group">
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">Login</button>
						<g:link class="btn btn-light" controller="register" action="register">Register</g:link>
					</div>
					</sec:ifNotLoggedIn>
					<sec:ifLoggedIn>
					Logged in as <g:link controller="profile" action="mine"><sec:username/></g:link>&nbsp;&nbsp;
					<div class="btn-group">
						<button type="button" id="logoutButton" class="btn btn-light" onclick="location.href='/logout';">Logout</button>
					</div>
					</sec:ifLoggedIn>
					<div class="btn-group ml-2" role="group">
						<div class="btn-group" role="group">
							<button id="navigation" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Navigation
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu <sec:ifNotLoggedIn>dropdown-menu-right</sec:ifNotLoggedIn>" aria-labelledby="navigation">
								<a href="/" class="dropdown-item">Main Page</a>
								<g:link controller="about" class="dropdown-item">About BALSA</g:link>
								<g:link controller="about" action="help" class="dropdown-item">BALSA Help</g:link>
								<g:link controller="about" action="submission" class="dropdown-item">About Data Submission</g:link>
								<sec:ifNotGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
								<g:link controller="terms" action="submission" class="dropdown-item">Become a BALSA Submitter</g:link>
								</sec:ifNotGranted>
								<sec:ifAnyGranted roles="ROLE_SUBMITTER">
								<a href="#" data-toggle="modal" data-target="#createStudyModal" class="dropdown-item">Create New Study</a>
								<g:link controller="study" action="mine" class="dropdown-item">Manage Your Studies</g:link>
								</sec:ifAnyGranted>
								<g:link controller="study" class="dropdown-item">Studies Index</g:link>
								<g:link controller="reference" class="dropdown-item">Reference Index</g:link>
							</div>
						</div>
						<sec:ifAnyGranted roles="ROLE_CURATOR">
						<div class="btn-group" role="group">
							<button id="curator_navigation" type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Curators
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu" aria-labelledby="curator_navigation">
								<g:link url="/curation" class="dropdown-item">Curate Studies</g:link>
								<a href="#" data-toggle="modal" data-target="#createReferenceModal" class="dropdown-item">Create New Reference</a>
								<g:link controller="tagging" action="index" class="dropdown-item">Manage Tag Categories</g:link>
								<g:link controller="news" action="create" class="dropdown-item">Post News Item</g:link>
								<g:link controller="curation" action="tweet" class="dropdown-item">Post to BALSA Twitter</g:link>
								<g:link controller="institution" class="dropdown-item">Institution Authority Control</g:link>
								<g:link controller="publication" class="dropdown-item">Publication Authority Control</g:link>
							</div>
						</div>
						</sec:ifAnyGranted>
						<sec:ifAnyGranted roles="ROLE_ADMIN">
						<div class="btn-group" role="group">
							<button id="admin_navigation" type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Administrator
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu" aria-labelledby="admin_navigation">
								<g:link controller="download" action="stats" class="dropdown-item">Download Statistics</g:link>
								<g:link controller="contact" action="techMessages" class="dropdown-item">Technical Issues</g:link>
								<g:link controller="contact" action="resolvedTechMessages" class="dropdown-item">Resolved Issues</g:link>
								<g:link controller="securityInfo" action="config" class="dropdown-item">Security</g:link>
							</div>
						</div>
						</sec:ifAnyGranted>
						<sec:ifAnyGranted roles="ROLE_USER">
						<div class="btn-group" role="group">
							<button id="contact_navigation" type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Contact Us
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu" aria-labelledby="contact_navigation">
								<a data-toggle="modal" data-target="#contactUsModal" class="dropdown-item">Contact Our Curators</a>
								<a data-toggle="modal" data-target="#reportIssuesModal" class="dropdown-item">Report a Technical Issue</a>
							</div>
						</div>
						</sec:ifAnyGranted>
						<g:link class="btn-square btn btn-light" controller="about" action="help">?</g:link>
					</div>
				</div>
				
			</div>
			<div class="card-body">
				<g:layoutBody/>
			</div>
			<div class="card-footer">
				<span class="float-right">©<g:formatDate format="yyyy" date="${new Date()}"/> Washington University School of Medicine</span>
			</div>
		</div>
		
		
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
		<g:render template="/templates/createDatasetModal" model="['datasetType':'Study']"/>
		</sec:ifAnyGranted>
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR">
		<g:render template="/templates/createDatasetModal" model="['datasetType':'Reference']"/>
		</sec:ifAnyGranted>
		<g:render template="/login/auth"/>
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER,ROLE_USER">
		<g:render template="/templates/contactUsModal"/>
		<g:render template="/templates/reportIssuesModal"/>
		</sec:ifAnyGranted>
	</body>
</html>
