<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>New Approval Process</title>
	</head>
	<body>
		<div id="edit-approval" role="main">
			<h1>New Approval Process</h1>
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
			<g:form url="[resource:approvalInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Create" />
					<g:link class="btn btn-default" uri="/">Cancel</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
