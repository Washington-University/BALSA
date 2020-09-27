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
	<body style="width:1200px">
		<div id="balsaBanner" role="banner">
			<div id="balsaLogo">
				<a href="/">
					<g:img dir="images" file="BALSALogo.png" width="100%" height="100%" data-toggle="tooltip" data-placement="right" title="The Brain Analysis Library of Spatial Maps and Atlases" />
				</a>
			</div>

			<div style="float:right;margin-right:20px;position:relative;top:50%;transform:translateY(-50%);z-index:999">
				<sec:ifNotLoggedIn>
				<div class="btn-group">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal" style="width:87px">Login</button>
					<g:link class="btn btn-default" controller="register" action="register" style="width:87px">Register</g:link>
				</div>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
				Logged in as <g:link controller="profile" action="mine"><sec:username/></g:link>&nbsp;&nbsp;
				<div class="btn-group">
					<button type="button" class="btn btn-default" onclick="location.href='/logout';" style="width:87px">Logout</button>
				</div>
				</sec:ifLoggedIn>
				<div class="btn-group">
					<div class="btn btn-default">
						<div class="dropdown-toggle" id="navigation" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="margin: -12px -6px; padding: 12px 6px">
							Navigation
							<span class="caret"></span>
						</div>
						<ul class="dropdown-menu" aria-labelledby="balsaLogo" style="z-index: 10000;">
							<li><a href="/">Main Page</a></li>
							
							<li><g:link controller="about">About BALSA</g:link></li>
							<li><g:link controller="about" action="help">BALSA Help</g:link></li>
							<li><g:link controller="about" action="submission">About Data Submission</g:link></li>
							<sec:ifNotGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
							<li><g:link controller="terms" action="submission">Become a BALSA Submitter</g:link></li>
							</sec:ifNotGranted>
							<sec:ifAnyGranted roles="ROLE_SUBMITTER">
							<li><a href="#" data-toggle="modal" data-target="#createStudyModal">Create New Study</a></li>
							<li><g:link controller="study" action="mine">Manage Your Studies</g:link></li>
							</sec:ifAnyGranted>
							<li><g:link controller="study">Studies Index</g:link></li>
							<li><g:link controller="reference">Reference Index</g:link></li>
						</ul>
					</div>
					<sec:ifAnyGranted roles="ROLE_CURATOR">
					<div class="btn btn-default">
						<div class="dropdown-toggle" id="navigation" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="margin: -12px -6px; padding: 12px 6px">
							Curators
							<span class="caret"></span>
						</div>
						<ul class="dropdown-menu" aria-labelledby="balsaLogo" style="z-index: 10000;">
							<li><g:link url="/curation">Curate Studies</g:link></li>
							<li><a href="#" data-toggle="modal" data-target="#createReferenceModal">Create New Reference</a></li>
							<li><g:link controller="tagging" action="categories">Manage Tag Categories</g:link></li>
							<li><g:link controller="news" action="create">Post News Item</g:link></li>
							<li><g:link controller="curation" action="tweet">Post to BALSA Twitter</g:link></li>
							<li><g:link controller="institution">Institution Authority Control</g:link></li>
							<li><g:link controller="publication">Publication Authority Control</g:link></li>
						</ul>
					</div>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
					<div class="btn btn-default">
						<div class="dropdown-toggle" id="navigation" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="margin: -12px -6px; padding: 12px 6px">
							Administrator
							<span class="caret"></span>
						</div>
						<ul class="dropdown-menu" aria-labelledby="balsaLogo" style="z-index: 10000;">
							<li><g:link controller="download" action="stats">Download Statistics</g:link></li>
							<li><g:link controller="contact" action="techMessages">Technical Issues</g:link></li>
							<li><g:link controller="contact" action="resolvedTechMessages">Resolved Issues</g:link></li>
							<li><g:link controller="securityInfo" action="config">Security</g:link></li>
						</ul>
					</div>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_USER">
					<div class="btn btn-default">
						<div class="dropdown-toggle" id="navigation" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="margin: -12px -6px; padding: 12px 6px">
							Contact Us
							<span class="caret"></span>
						</div>
						<ul class="dropdown-menu" aria-labelledby="balsaLogo" style="z-index: 10000;">
							<li><a data-toggle="modal" data-target="#contactUsModal">Contact Our Curators</a></li>
							<li><a data-toggle="modal" data-target="#reportIssuesModal">Report a Technical Issue</a></li>
						</ul>
					</div>
					</sec:ifAnyGranted>
					<g:link class="btn btn-default helpButton" controller="about" action="help">?</g:link>
				</div>
			</div>
		</div>
		
		<g:layoutBody/>
		<div class="footer" role="contentinfo">
			&nbsp;
			<span class="pull-right">©<g:formatDate format="yyyy" date="${new Date()}"/> Washington University School of Medicine</span>
		</div>
		
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER">
		<g:render template="/templates/createDatasetModal" model="['datasetType':'Study']"/>
		</sec:ifAnyGranted>
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR">
		<g:render template="/templates/createDatasetModal" model="['datasetType':'Reference']"/>
		</sec:ifAnyGranted>
		<div id="loginModal" class="modal fade" role="dialog">
			<g:render template="/login/auth" />
		</div>
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER,ROLE_USER">
		<g:render template="/templates/contactUsModal"/>
		<g:render template="/templates/reportIssuesModal"/>
		</sec:ifAnyGranted>
		
		<script>
			$('[data-toggle="tooltip"]').tooltip();
			$(".modal-dialog").draggable({
			    handle: ".panel-heading"
			});

			function updateRecentActivity() {			
				var d = new Date();
				var t = d.getTime();
				d.setHours(d.getHours() + 1);
				document.cookie = "balsaRecentActivityTime=" + t + ";path=/;expires=" + d.toUTCString();
			}

			updateRecentActivity();
		</script>
		<sec:ifLoggedIn>
		<script>
			var timeoutAlert = null;
			
			setInterval(checkRecentActivity, 5000);
			function checkRecentActivity() {
				var activityCookie = document.cookie.match('(^|;) ?balsaRecentActivityTime=([^;]*)(;|$)');
				var mostRecentActivity = activityCookie ? activityCookie[2] : 0;
				
				if (mostRecentActivity && !timeoutAlert && Date.now() - mostRecentActivity > 1740000) {
					timeoutAlert = bootbox.alert({
						message: "You will soon be automatically logged out due to lack of activity. Do you wish to remain logged in?",
						buttons: {
							ok: {label: 'Yes'}
						},
						callback: function() {
							$.get("${createLink(controller: 'login', action:'keepAlive')}");
							updateRecentActivity();
						}
					});
				}
				else if (mostRecentActivity && timeoutAlert && Date.now() - mostRecentActivity < 1740000) {
					timeoutAlert.modal('hide');
					timeoutAlert = null;
				}
				if (mostRecentActivity && Date.now() - mostRecentActivity > 1860000) {
					location.reload(true);
				}
			}
		</script>
		</sec:ifLoggedIn>
	</body>
</html>
