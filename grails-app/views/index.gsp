<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>BALSA</title>
	</head>
	<body>
		<div role="main">
			<div id="sidebar" role="complementary" style="float:left;width:300px">
				<sec:ifNotLoggedIn>
				<div id="explanation" class="panel panel-default">
					<div class="panel-heading">Welcome to BALSA</div>
					<div class="panel-body" style="padding-bottom:5px">
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
					<div class="panel-footer"><g:link url="/about">More about BALSA</g:link></div>
					<div class="panel-footer"><g:link url="/about/submission">About Data Submission</g:link></div>
				</div>
				</sec:ifNotLoggedIn>
				<div id="news" class="panel panel-default">
					<div class="panel-heading">News</div>
					<div id="latestNews" class="panel-body">
						<script>
							$( document ).ready(function() {
								$.get("${createLink(controller: 'news', action: 'latest')}", function(data, status){$('#latestNews').html(data)});
								updateRecentActivity();
							});
						</script>
					</div>
					<div class="panel-footer"><g:link controller="news">More BALSA news</g:link></div>
				</div>
				<sec:ifLoggedIn>
				<div class="sidebar-nav">
					<div class="navbar navbar-default" role="navigation">
						<div class="navbar-collapse collapse sidebar-navbar-collapse">
							<ul class="nav navbar-nav">
								<li><g:link controller="about">About BALSA</g:link></li>
								<li><g:link controller="about" action="help">BALSA Help</g:link></li>
								<li><g:link controller="about" action="submission">About Data Submission</g:link></li>
								<sec:ifNotGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
								<li><g:link controller="terms" action="submission">Become a BALSA Submitter</g:link></li>
								</sec:ifNotGranted>
								
								<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
								<li><a href="#" data-toggle="modal" data-target="#createStudyModal">Create New Study</a></li>
								<li><g:link controller="study" action="mine">Manage Your Studies</g:link></li>
								</sec:ifAnyGranted>
								
								<li><g:link controller="study">Studies Index</g:link></li>
								<li><g:link controller="reference">Reference Index</g:link></li>
								
								<sec:ifAnyGranted roles="ROLE_CURATOR">
								<li>
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Curators <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><g:link url="/curation">Curate Studies</g:link></li>
										<li><a href="#" data-toggle="modal" data-target="#createReferenceModal">Create New Reference</a></li>
										<li><g:link controller="tagging" action="categories">Manage Tag Categories</g:link></li>
										<li><g:link controller="news" action="create">Post News Item</g:link></li>
										<li><g:link controller="curation" action="tweet">Post to BALSA Twitter</g:link></li>
									</ul>
								</li>
								<li>
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Authority Control <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><g:link controller="institution">Institutions</g:link></li>
										<li><g:link controller="publication">Publications</g:link></li>
									</ul>
								</li>
								</sec:ifAnyGranted>
								
								<sec:ifAnyGranted roles="ROLE_ADMIN">
								<li>
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administrator <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><g:link controller="download" action="stats">Download Statistics</g:link></li>
										<li><g:link controller="contact" action="techMessages">Technical Issues</g:link></li>
										<li><g:link controller="contact" action="resolvedTechMessages">Resolved Issues</g:link></li>
										<li><g:link controller="securityInfo" action="config">Security</g:link></li>
									</ul>
								</li>
								</sec:ifAnyGranted>
								
								<sec:ifAnyGranted roles="ROLE_USER">
								<li>
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Contact Us <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a data-toggle="modal" data-target="#contactUsModal">Contact Our Curators</a></li>
										<li><a data-toggle="modal" data-target="#reportIssuesModal">Report a Technical Issue</a></li>
									</ul>
								</li>
								</sec:ifAnyGranted>
							</ul>
						</div><!--/.nav-collapse -->
					</div>
				</div>
				</sec:ifLoggedIn>
			</div>
			<div class="panel panel-default" id="page-body" style="margin-left:320px">
				
				<div class="panel-heading">Browse/Search Scenes</div>
				<div class="panel-body">
					<g:render template="/search/search"/>
				</div>
			</div>
		</div>
		<script>
		$(document).ready(function() {
			$('.dropdown-toggle').dropdown();
		});
		</script>
	</body>
</html>
