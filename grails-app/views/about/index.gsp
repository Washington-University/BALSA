<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>About BALSA</title>
	</head>
	<body>
		<div role="main">
			<h3>About BALSA</h3>
			
			<div class="btn-group">
				<a class="btn btn-default" href="/">Main</a>
				<g:link class="btn btn-default" action="submission">About Data Submission</g:link>
			</div>
			
			<br><br>
			
			<div class="well" style="overflow: hidden;">
				<h3>About BALSA</h3>
				<p>
					BALSA (Brain Analysis Library of Spatial maps and Atlases) is a database for hosting and sharing neuroimaging and neuroanatomical 
					datasets for human and primate species.
				</p>
				<p>
					BALSA houses curated, user-created <b>Study</b> datasets, extensively analyzed neuroimaging data associated with published figures 
					and <b>Reference</b> datasets mapped to brain atlas surfaces and volumes in human and nonhuman primates as a general resource (e.g., published cortical parcellations).
				</p>
				
				<h4>Scenes and wb_view</h4>
				<p>
					Datasets in BALSA are organized as “scenes” generated in wb_view, a brain visualization program distributed as part of the 
					<a href="https://www.humanconnectome.org/software/connectome-workbench">Connectome Workbench</a> software platform. 
				</p>
				<p>
					"Scene files" in BALSA facilitate data upload, previews, download, and visualization:
				</p>
				<ul>
					<li>Complex datasets can be shared with custom display/labelling options exactly as study owners want them viewed by users.</li>
					<li>Users can quickly load and flexibly interact with the data in the 3D wb_view workspace.</li>
					<li>No login needed to preview scene ‘thumbnails’ in BALSA or to download data having no release constraints.</li>
					<li>Human data: accessible upon agreeing to applicable Data Use Terms.</li>
				</ul>
				<p>
					Similar formats to scenes created in other neuroimaging platforms will be supported in future.
				</p>
				<h4>Linking of BALSA datasets to publications</h4>
				<p>
					When data is uploaded, each scene file, and every individual scene within it, is assigned a unique URL and webpage in BALSA, allowing it 
					to be cited and accessed directly from a publication, a webpage, or from social media posts. 
				</p>
				<p>	
					Each dataset in BALSA, may include a PMID and/or DOI that links it directly to a relevant publication.
				</p>
				<h4>Why submit to BALSA?</h4>
				<p>
					<b>Promote your work.</b> Sharing your dataset on BALSA boosts the visibility and impact of your research.
				</p>
				<p>
					<b>Enable evaluation and comparison.</b> Sharing your data on BALSA facilitates evaluation of your study and encourages researchers to include your study in cross-study analyses. 
				</p>
				<p>
					<b>Connect and collaborate.</b> Contributing to BALSA will connect you with interested scientists engaging with your work, which may lead to fruitful interactions and collaborations.
				</p>
				<p>
					<b>Accelerate advances.</b> Stimulate scientific discussion and new investigations by adding to the collection of neuroimaging and neuroanatomical datasets available.
				</p>
				<h4>Get started!</h4>
				<p>
					<g:link controller="register" action="register">Register</g:link> for a BALSA account, or use an existing <a href="https://db.humanconnectome.org">ConnectomeDB</a> account to log in. 
				</p>
				<p>
					To create a study or submit data to BALSA, you must log in to your BALSA account and agree to <g:link controller="terms" action="submission">terms to become a BALSA submitter</g:link>. 
				</p>
				<p>
					Learn more about <g:link action="submission">Submitting to BALSA</g:link>.
				</p>
				<h4>Questions and comments?</h4>
				<p>
					If you have questions, comments, or suggestions about BALSA, please use the  
					<a href="mailto:HCP-Users@humanconnectome.org">HCP-Users list</a>. To subscribe,
					<a href="http://www.humanconnectome.org/contact/#subscribe">click here</a>, 
					as your inputs may be of interest to other BALSA users and HCP users more broadly.
				</p>
				<h4>Reference</h4>
				<p>
					Van Essen DC et al. (2016). <a href="https://doi.org/10.1016/j.neuroimage.2016.04.002">The Brain Analysis Library of Spatial maps and Atlases (BALSA) database</a>. NeuroImage 144B: 270-274.
				</p>
			</div>
		</div>
	</body>
</html>