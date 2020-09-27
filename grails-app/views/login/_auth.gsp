<div class="modal-dialog">
	<div class="modal-content">
		<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color:white"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">Login to BALSA</h4>
		</div>
		<g:formRemote class="form-horizontal" action="/j_spring_security_check" url="[controller: 'j_spring_security_check', action: '']" 
			id="loginForm" name="loginForm" autocomplete='off' onSuccess="isLoggedIn(data)">
			<div class="modal-body">
				<div class="sign-in">
					<div id="loginError" class="form-group has-error" style="text-align:center;vertical-align:middle;display:none;">
						<label id="loginErrorLabel" class="control-label" style="padding:0"></label>
					</div>
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">Username</label>
						<div class="col-sm-10">
							<input name="j_username" class="form-control" id="username" placeholder="Username"/>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input name="j_password" class="form-control" id="password" placeholder="Password" type="password"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label>
									<input name="_spring_security_remember_me" id="remember_me" type="checkbox" checked> Remember me
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-left" style="line-height: 2.3em;">
					<g:link action="forgotUsername" controller="register">Forgotten Username?</g:link> &nbsp;
					<g:link action="forgotPassword" controller="register">Forgotten Password?</g:link>
				</div>
				<div class="btn-group">
					<button type="submit" class="btn btn-primary" style="width:87px">Login</button>
					<g:link  class="btn btn-default" action="register" controller="register" style="width:87px">Register</g:link>
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#loginModal" style="width:87px">Cancel</button>
				</div>
			</div>
		</g:formRemote>
	</div>
</div>

<script>
$('#loginModal').on('shown.bs.modal', function () {
	$('#username').focus()
})


$(document).ready(function() {
	$('#username').focus();
});

function isLoggedIn(data) {
	if (data.success) {
		location.reload(true);
	}
	else if (data.error) {
		$('#loginError').slideDown(500);
		$('#loginErrorLabel').text(data.error);
	}
}
</script>