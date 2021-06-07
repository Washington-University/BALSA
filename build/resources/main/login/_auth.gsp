<div id="loginModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Login to BALSA</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			
			<div class="modal-body">
				<g:form controller="login" action="authenticate" name="loginModalForm" autocomplete='off' class="ajaxForm" data-success="isLoggedIn(data)">
					<div id="loginError" class="form-group has-error" style="text-align:center;vertical-align:middle;display:none;">
						<label id="loginErrorLabel" class="control-label p-0"></label>
					</div>
					<div class="form-group row">
						<label class="col-4 col-form-label" for="username">Username</label>
						<div class="col-8">
							<input name="username" class="form-control" id="usernameModal" placeholder="Username"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-4 col-form-label" for="password">Password</label>
						<div class="col-8">
							<input name="password" class="form-control" id="passwordModal" placeholder="Password" type="password"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-4">
							<div class="form-check">
								<input class="form-check-input" name="remember-me" id="remember_meModal" type="checkbox" checked>
								<label class="form-check-label" for="remember_meModal">Remember me</label>
							</div>
						</div>
						<div class="col-8 d-flex">
							<g:link class="mr-auto" action="forgotUsername" controller="register">Forgotten Username?</g:link>
							<g:link action="forgotPassword" controller="register">Forgotten Password?</g:link>
						</div>
					</div>
				</g:form>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<button type="submit" form="loginModalForm" class="btn btn-primary">Login</button>
					<g:link  class="btn btn-light" action="register" controller="register">Register</g:link>
					<button type="button" class="btn btn-light" data-toggle="modal" data-target="#loginModal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>