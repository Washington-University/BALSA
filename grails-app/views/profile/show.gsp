
<%@ page import="balsa.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:fieldValue bean="${profileInstance}" field="fullname"/></title>
	</head>
	<body>
		<div id="show-profile" role="main">
			<h3>User: ${profileInstance.fullname ?: profileInstance.user.username}</h3>
			
			<div class="btn-group">
				<g:if test="${ownProfile}">
				<g:link class="btn btn-default" action="edit" resource="${profileInstance}">Edit Profile</g:link>
				<button id="changePasswordButton" type="button" class="btn btn-default" data-toggle="modal" data-target="#changePasswordModal">Change Password</button>
				<g:link class="btn btn-default" controller="terms" action="mine">Agreed Terms</g:link>
				<g:link class="btn btn-default" controller="download" action="mine">Download History</g:link>
				<button type="button" class="btn btn-default" onclick="bootbox.confirm({size: 'small', message: 'Are you certain you wish to delete your BALSA user account?', 
					callback: function(result) {if (result) {$(location).attr('href','<g:createLink controller="register" action="delete" />');} } })">
					Delete Account
				</button>
				</g:if>
			</div>
			
			<br><br>
			<div class="well">
				<p>
					<span class="attributeLabel">FULL NAME:</span><br>
					<g:fieldValue bean="${profileInstance}" field="fullname"/>
				</p>
				
				<p>
					<span class="attributeLabel">USER ID:</span><br>
					<g:fieldValue bean="${profileInstance}" field="user.username"/>
				</p>
				
				<g:if test="${ownProfile || profileInstance.contactInfoPublic}">
				<g:if test="${profileInstance.emailAddress}">
				<p>
					<span class="attributeLabel">EMAIL ADDRESS:</span><br>
					<a href="mailto:${profileInstance.emailAddress}"><g:fieldValue bean="${profileInstance}" field="emailAddress"/></a>
				</p>
				</g:if>
				<g:if test="${profileInstance.phone}">
				<p>
					<span class="attributeLabel">PHONE NUMBER:</span><br>
					<g:fieldValue bean="${profileInstance}" field="phone"/>
				</p>
				</g:if>
				</g:if>
				
				<g:if test="${profileInstance.citednames && profileInstance.citednames.size() > 0}">
				<div>
					<span class="attributeLabel">CITED NAMES:</span>
					<ul>
					<g:each in="${profileInstance.citednames}">
						<li>${it}</li>
					</g:each>
					</ul>
				</div>
				</g:if>
				
				<g:if test="${profileInstance.institutions && profileInstance.institutions.size() > 0}">
				<div>
					<span class="attributeLabel">INSTITUTIONS:</span>
					<ul>
					<g:each in="${profileInstance.institutions}">
						<li>${it.canonicalName}</li>
					</g:each>
					</ul>
				</div>
				</g:if>
			</div>
		</div>
		
		<div class="modal fade" id="changePasswordModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="background-color:#24296b;border-top-left-radius:5px;border-top-right-radius:5px;color:white">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color:white"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Change Password</h4>
					</div>
					<g:formRemote class="form-horizontal" url="[controller: 'register', action: 'changePassword']" method='POST' id="changePasswordForm" name="changePasswordForm" autocomplete='off' onSuccess="closeModal();updateRecentActivity();">
						<div class="modal-body">
							<div class="form-group">
								<label for="oldPassword" class="col-sm-3 control-label">Old Password</label>
								<div class="col-sm-9">
									<input name="oldPassword" class="form-control" id="oldPassword" placeholder="Old Password" type="password"/>
								</div>
							</div>
							<div class="form-group">
								<label for="newPassword" class="col-sm-3 control-label">New Password</label>
								<div class="col-sm-9">
									<input name="newPassword" class="form-control" id="newPassword" placeholder="New Password" type="password"/>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</g:formRemote>
				</div>
			</div>
		</div>
	</body>
</html>
