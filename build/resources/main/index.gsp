<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>BALSA</title>
	</head>
	<body>
		<div role="main">
			<div class="row">
				<div id="sidebar" role="complementary" class="col sidebar">
					<sec:ifNotLoggedIn>
					<div id="explanation" class="card">
						<div class="card-header h5 mb-0">Welcome to BALSA</div>
						<div class="card-body pb-2">
							BALSA hosts two types of extensively analyzed neuroimaging data:
							<ul>
								<li><b>BALSA Studies:</b> user-submitted neuroimaging data associated with published figures</li>
								<li><b>BALSA Reference:</b> reference data mapped to brain atlas surfaces and volumes in humans and nonhuman primates</li>
							</ul>
							"Scene files" in BALSA facilitate data upload, previews, download, and visualization.
							<ul>
								<li>No login needed to preview scene ‘thumbnails’ in BALSA or to download data having no release constraints.</li>
								<li>Human data: accessible upon agreeing to applicable Data Use Terms.</li>
							</ul>
						</div>
						<div class="card-footer"><g:link url="/about">More about BALSA</g:link></div>
						<div class="card-footer"><g:link url="/about/submission">About Data Submission</g:link></div>
					</div>
					</sec:ifNotLoggedIn>
					<div id="news" class="card">
						<div class="card-header h5 mb-0">News</div>
						<div id="latestNews" class="card-body">
						</div>
						<div class="card-footer"><g:link controller="news">More BALSA news</g:link></div>
					</div>
					<sec:ifLoggedIn>
					<div class="card">
						<div class="btn-group-vertical">
							<g:link class="btn btn-light navbtn" controller="about">About BALSA</g:link>
							<g:link class="btn btn-light navbtn" controller="about" action="help">BALSA Help</g:link>
							<g:link class="btn btn-light navbtn" controller="about" action="submission">About Data Submission</g:link>

							<sec:ifNotGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
							<g:link class="btn btn-light navbtn" controller="terms" action="submission">Become a BALSA Submitter</g:link>
							</sec:ifNotGranted>

							<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
							<a class="btn btn-light navbtn" href="#" data-toggle="modal" data-target="#createStudyModal">Create New Study</a>
							<g:link class="btn btn-light navbtn" controller="study" action="mine">Manage Your Studies</g:link>
							</sec:ifAnyGranted>

							<g:link class="btn btn-light navbtn" controller="study">Studies Index</g:link>
							<g:link class="btn btn-light navbtn" controller="reference">Reference Index</g:link>

							<sec:ifAnyGranted roles="ROLE_CURATOR">
							<div class="w-100">
								<a href="#" class="btn btn-light dropdown-toggle navbtn" data-toggle="dropdown" >
									Curators <span class="caret"></span>
								</a>
								<div class="dropdown-menu w-100" aria-labelledby="curation">
									<g:link class="dropdown-item" url="/curation">Curate Studies</g:link>
									<a class="dropdown-item" href="#" data-toggle="modal" data-target="#createReferenceModal">Create New Reference</a>
									<g:link class="dropdown-item" controller="tagging" action="index">Manage Tag Categories</g:link>
									<g:link class="dropdown-item" controller="news" action="create">Post News Item</g:link>
									<g:link class="dropdown-item" controller="curation" action="tweet">Post to BALSA Twitter</g:link>
								</div>
							</div>

							<div class="w-100">
								<a href="#" class="btn btn-light dropdown-toggle navbtn" data-toggle="dropdown" >
									Authority Control <span class="caret"></span>
								</a>
								<div class="dropdown-menu w-100" aria-labelledby="authorityControl">
									<g:link class="dropdown-item" controller="institution">Institutions</g:link>
									<g:link class="dropdown-item" controller="publication">Publications</g:link>
								</div>
							</div>
							</sec:ifAnyGranted>

							<sec:ifAnyGranted roles="ROLE_ADMIN">
							<div class="w-100">
								<a href="#" class="btn btn-light dropdown-toggle navbtn" data-toggle="dropdown" >
									Administrator <span class="caret"></span>
								</a>
								<div class="dropdown-menu w-100" aria-labelledby="administrator">
									<g:link class="dropdown-item" controller="download" action="stats">Download Statistics</g:link>
									<g:link class="dropdown-item" controller="contact" action="techMessages">Technical Issues</g:link>
									<g:link class="dropdown-item" controller="contact" action="resolvedTechMessages">Resolved Issues</g:link>
									<g:link class="dropdown-item" controller="securityInfo" action="config">Security</g:link>
								</div>
							</div>
							</sec:ifAnyGranted>

							<sec:ifAnyGranted roles="ROLE_USER">
							<div class="w-100">
								<a href="#" class="btn btn-light dropdown-toggle navbtn" data-toggle="dropdown" >
									Contact Us <span class="caret"></span>
								</a>
								<div class="dropdown-menu w-100" aria-labelledby="contactUs">
									<a class="dropdown-item" data-toggle="modal" data-target="#contactUsModal">Contact Our Curators</a>
									<a class="dropdown-item" data-toggle="modal" data-target="#reportIssuesModal">Report a Technical Issue</a>
								</div>
							</div>

							</sec:ifAnyGranted>
						</div>
					</div>
					</sec:ifLoggedIn>
				</div>
				<div class="col-auto main-column">
					<div class="card" id="page-body">

						<div class="card-header h5 mb-0">Browse/Search Scenes</div>
						<g:render template="/search/search"/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
