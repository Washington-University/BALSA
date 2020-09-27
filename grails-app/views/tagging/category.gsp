<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${categoryInstance.name}</title>
	</head>
	<body>
		<div id="list-fileMetadata" class="content scaffold-list" role="main">
			<g:link class="btn btn-default" action="categories">Back to categories</g:link>
			<h1>Tag Category: ${categoryInstance.name}</h1>
			<g:form action="updateDescription" id="${categoryInstance.id}">
				<div class="form-group">
					<label for="options">What short description of this category should users see as a tooltip?</label><br>
					<g:textArea name="description" rows="5" cols="50" value="${categoryInstance.description}" />
					<br>
					<g:submitButton name="updateDescription" value="Update" />
				</div>
			</g:form>
			<form>
				<input type="hidden" name="id" value="${categoryInstance.id}">
				<div class="form-group">
					<label for="searchType">How should users search this category?</label><br>
					<g:radioGroup name="searchType"
					              labels="['Dropdown menu','Text field','Radio buttons', 'Checkboxes']"
					              values="['DROPDOWN','FIELD','RADIO','CHECKBOX']"
					              value="${categoryInstance.searchType}"
					              onchange="jQuery.ajax({type:'POST',data:jQuery(this.form).serialize(), url:'/tagging/changeType',success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){}});return false">
						<div style="display:inline-block;margin-right:20px;">${it.radio} ${it.label}</div>
					</g:radioGroup>
				</div>
			</form>
			<g:form action="updateOptions" id="${categoryInstance.id}">
				<div class="form-group">
					<label for="options">What options can users select from? (One option per line.)</label><br>
					<textarea name="options" rows="10" cols="50"><g:join in="${categoryInstance.options}" delimiter="\r\n"/></textarea>
					<br>
					<g:submitButton name="updateOptions" value="Update" />
				</div>
			</g:form>
			
			<label>What tag handles should BALSA scan files/filenames for?</label><br>
			<g:each in="${categoryInstance.handles}">
			<div>Value: ${it.value} &nbsp;&nbsp;&nbsp; Handle: ${it.handle} &nbsp;&nbsp;&nbsp; <g:link action="deleteHandle" id="${it.id}">&times;</g:link></div>
			</g:each>
			<br>
			<g:each in="${categoryInstance.options}" var="option">
			<g:if test="${!categoryInstance.handles.find({it.value == option})}">
			No handles created for ${option}<br>
			</g:if>
			</g:each>
			
			<br>
			<label>Add new tag handle</label>
			<g:form class="form-inline" action="addHandle">
				<input type="hidden" name="category" value="${categoryInstance.id}">
				<div class="form-group">
					<label for="searchType">Value</label>
					<input type="text" class="form-control" id="value" name="value" placeholder="leave blank to use captured group">
				</div>
				<div class="form-group">
					<label for="searchType">Handle</label>
					<input type="text" class="form-control" id="handle" name="handle">
				</div>
				<button type="submit" class="btn btn-default">Add tag handle</button>
			</g:form>
			
			<br><br><br>
			<div><g:link class="btn btn-default" action="deleteCategory" id="${categoryInstance.id}" onclick="return confirm('Delete category?')">Delete category</g:link></div>
		</div>
	</body>
</html>
