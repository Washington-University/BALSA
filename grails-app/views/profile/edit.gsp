<%@ page import="balsa.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Update Your User Profile</title>
	</head>
	<body>
		<div id="edit-profile" role="main">
			<div style="width:600px;margin:0 auto;">
				<h3 style="margin-bottom:20px">Update profile for <sec:username/></h3>
				<g:form url="[resource:profileInstance, action:'update']" method="PUT">
					<g:hiddenField name="version" value="${profileInstance?.version}" />
					<div class="form-group">
						<label for="fullname" class="control-label">Full Name</label>
						<g:textField class="form-control" name="fullname" value="${profileInstance?.fullname}" />
					</div>
					<div class="form-group">
						<label for="emailAddress" class="control-label">Email Address</label>
						<g:textField class="form-control" name="emailAddress" value="${profileInstance?.emailAddress}" />
					</div>
					<div class="form-group">
						<label for="phone" class="control-label">Phone Number</label>
						<g:textField class="form-control" name="phone" value="${profileInstance?.phone}" />
					</div>
					<div class="form-group">
						<div class="checkbox">
							<label>
								<g:checkBox name="contactInfoPublic" value="${profileInstance?.contactInfoPublic}" /> Would you like your contact information to be visible to other BALSA users?
							</label>
						</div>
					</div>
					<div style="text-align:right">
						<div class="btn-group">
							<button type="submit" class="btn btn-primary">Update</button>
							<g:link class="btn btn-default" action="show" id="${profileInstance.user.id}">Cancel</g:link>
						</div>
					</div>
				</g:form>
			</div>
		</div>
		<script>
			function closeModal() {
				$('#changePasswordModal').modal('hide');
			}
		</script>
	</body>
</html>
