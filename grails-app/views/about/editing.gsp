<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Editing a BALSA Study</title>
	</head>
	<body>
		<div role="main">
			<h3>Editing a BALSA Study</h3>
			
			<div class="btn-group">
				<a class="btn btn-default" href="/">Main</a>
				<g:link class="btn btn-default" action="index">About BALSA</g:link>
				<g:link class="btn btn-default" action="submission">About Data Submission</g:link>
			</div>
			
			<br><br>
			
			<div class="well" style="overflow: hidden;">
				<p>
					The Edit Study page allows you to add and adjust various metadata about your BALSA study. You can begin immediately upon study creation, and you can 
					return to the edit page at any time by clicking the 'Edit' button on your study's main page. The edit page contains various fields, including:
				</p>
				<p>
					<ul>
						<li>
							<b>Study Details</b>
							<ul>
								<li>
									<b>Display Title</b> - This field allows you to enter a shortened version of your study title for brevity and clarity.
								</li>
								<li>
									<b>Extraction Directory</b> - This field allows you to set the name of the directory a user will see when they 
									extract the contents of a zip file of your study data. (The Study_ID will be automatically added as a suffix.)
								</li>
							</ul>
						</li>
						<li>
							<b>Publication</b>
							<ul>
								<li>
									<b>Dates</b> - Use this date-pickers to select the preprint, epub, and journal publication dates of your study. You 
									can then select any of those dates (or a separate date you set) as the 'Release Date', i.e. the date that you would 
									like your study data on BALSA to become publicly available.
								</li>
							</ul>
						</li>
						<li>
							<b>Publication</b>
							<ul>
								<li>
									<b>Owners and Viewers</b> - The 'Owners' area is a list of users who have ownership rights to your study data in BALSA, 
									generally your co-authors. They may access the edit page, upload data, and handle submission of the study. By default 
									your username will already be listed here. Note that owners you add will still need to agree to the Data Submission 
									Terms before they have these permissions. The 'Viewers' area is a list of users who you would like to be able to view 
									and download study data prior to that data being made publically available, allowing for reviewers. In both cases, 
									users are added by their usernames, which means they must already have an account in BALSA (or have logged into BALSA 
									at least once if they are using an HCP account).
								</li>
								<li>
									<b>Data Access Terms and Restricted Data Access Process</b> - These fields allow you to select a set of terms to which a 
									user must agree before they download your study data.
								</li>
							</ul>
						</li>
						<li>
							<b>Presentation</b>
							<ul>
								<li>
									<b>Scene File Order</b> - Here you can change the order in which your scene files are displayed.
								</li>
								<li>
									<b>Focus Scene</b> - Here you can select a single scene preview to be the starting point in the scene preview carousel, 
									allowing you to specify an exemplar image for your study.
								</li>
							</ul>
						</li>
					</ul>
				</p>
			</div>
		</div>
	</body>
</html>





