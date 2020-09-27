<%@ page import="balsa.News" %>

<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'contents', 'error')} required">
	<label for="contents">
		Contents
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="contents" required="" value="${newsInstance?.contents}" rows="25" cols="100"/>

</div>