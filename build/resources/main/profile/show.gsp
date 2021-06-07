
<%@ page import="balsa.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:fieldValue bean="${profileInstance}" field="fullname"/></title>
	</head>
	<body>
		<div id="show-profile" role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">User: ${profileInstance.fullname ?: profileInstance.user.username}</div>
				<div class="btn-group btn-bar">
					<g:if test="${ownProfile}">
					<button class="btn btn-light" data-toggle="modal" data-target="#editProfileModal">Edit Profile</button>
					<button class="btn btn-light" data-toggle="modal" data-target="#changePasswordModal">Change Password</button>
					<g:link class="btn btn-light" controller="terms" action="mine">Agreed Terms</g:link>
					<g:link class="btn btn-light" controller="download" action="mine">Download History</g:link>
					<button type="button" class="btn btn-light" onclick="bootbox.confirm({size: 'small', message: 'Are you certain you wish to delete your BALSA user account?', 
						callback: function(result) {if (result) {$(location).attr('href','<g:createLink controller="register" action="delete" />');} } })">
						Delete Account
					</button>
					</g:if>
				</div>
				<div class="card-body">
					<p>
						<span class="attributeLabel">FULL NAME:</span><br>
						<g:fieldValue bean="${profileInstance}" field="fullname"/>
					</p>

					<p>
						<span class="attributeLabel">USER ID:</span><br>
						${profileInstance.user.username}
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
		</div>
		
		<g:if test="${ownProfile}">
		<div class="modal fade" id="changePasswordModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Change Password</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body">
						<div class="text-secondary mb-4">Passwords requirements: at least eight characters, must contain at least one letter, one number, and one special character (!@#$%^&).</div>
						<g:form name="changePasswordForm" class="ajaxForm" controller="register" action="changePassword" autocomplete="off" method="POST" data-success="passwordChanged()">
							<div class="form-group row">
								<label for="oldPassword" class="col-4 col-form-label">Old Password</label>
								<div class="col-8">
									<input name="oldPassword" class="form-control" id="oldPassword" placeholder="Old Password" type="password"/>
								</div>
							</div>
							<div class="form-group row">
								<label for="newPassword" class="col-4 col-form-label">New Password</label>
								<div class="col-8">
									<input name="newPassword" class="form-control" id="newPassword" placeholder="New Password" type="password"/>
								</div>
							</div>
						</g:form>
					</div>
					<div class="modal-footer">
						<div class="btn-group">
							<button type="submit" form="changePasswordForm" class="btn btn-primary">Submit</button>
							<button type="button" class="btn btn-light" data-toggle="modal" data-target="#changePasswordModal">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="editProfileModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Edit Profile</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body">
						<g:if test="${profileInstance.version == 0}">
						<div class="text-secondary mb-4">This is your first time viewing your profile. Please take a moment to fill in some information.</div>
						</g:if>
						<g:form name="profileForm" action="update" id="${profileInstance.id}" autocomplete="off" method="POST">
							<div class="form-group row">
								<label for="fullname" class="col-4 col-form-label">Full Name</label>
								<div class="col-8">
									<input name="fullname" class="form-control" id="usernameModal" value="${profileInstance.fullname}"/>
								</div>
							</div>
							<div class="form-group row">
								<label for="emailAddress" class="col-4 col-form-label">Email Address</label>
								<div class="col-8">
									<input name="emailAddress" class="form-control" id="usernameModal" value="${profileInstance.emailAddress}"/>
								</div>
							</div>
							<div class="form-group row">
								<label for="phone" class="col-4 col-form-label">Phone Number</label>
								<div class="col-8">
									<input name="phone" class="form-control" id="usernameModal" value="${profileInstance.phone}"/>
								</div>
							</div>
							<div class="form-group row mb-0">
								<div class="form-check col-10 offset-1">
									<div class="row mb-0">
										<div class="d-flex col-1">
											<input class="form-check-input align-self-center ml-auto" name="contactInfoPublic" id="contactInfoPublic" type="checkbox" value="${profileInstance?.contactInfoPublic}">
										</div>
										<label class="form-check-label col-11" for="contactInfoPublic">Would you like your contact information to be visible to other BALSA users?</label>
									</div>
								</div>
							</div>
						</g:form>
					</div>
					<div class="modal-footer">
						<div class="btn-group">
							<button type="submit" form="profileForm" class="btn btn-primary">Update</button>
							<button type="button" class="btn btn-light" data-toggle="modal" data-target="#editProfileModal">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<g:if test="${profileInstance.version == 0}">
		<script>
			$('#editProfileModal').modal('show');
		</script>
		</g:if>
		</g:if>
	</body>
</html>
