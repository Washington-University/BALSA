<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Edit Approval</title>
	</head>
	<body>
		<div id="edit-approval" class="content scaffold-edit" role="main">
			<h1>Edit Approval</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${approvalInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${approvalInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:approvalInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${approvalInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<div id="balsaButtons" class="btn-group">
					<g:actionSubmit class="btn btn-primary" action="update" value="Update" />
					<g:link class="btn btn-default" action="show" resource="${approvalInstance}">Cancel</g:link>
				</div>
			</g:form>
		</div>
	</body>
</html>
