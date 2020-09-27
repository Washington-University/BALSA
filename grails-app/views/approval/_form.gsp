<%@ page import="balsa.security.Approval" %>


<div class="fieldcontain ${hasErrors(bean: approvalInstance, field: 'title', 'error')} ">
	<label for="title">Title</label><g:field type="text" name="title" value="${approvalInstance?.title}" />
</div>

<div class="fieldcontain ${hasErrors(bean: datasetInstance, field: 'contents', 'error')} ">
	<label for="contents">Contents</label>
	<g:textArea id="contents" name="contents" value="${approvalInstance?.contents}" rows="10" cols="40"/>
</div>

<div class="fieldcontain ${hasErrors(bean: approvalInstance, field: 'link', 'error')} ">
	<label for="link">Link</label><g:field type="text" name="link" value="${approvalInstance?.link}" />
</div>