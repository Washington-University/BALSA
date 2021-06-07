<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Tag Categories</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Tag Categories</div>
				<div class="btn-group btn-bar">
					<a class="btn btn-primary" data-toggle="modal" data-target="#createCategoryModal">New Category</a>
				</div>
				<div class="card-body">
					<div id="categoryAccordion" class="accordion">
						<g:each in="${categoryInstanceList.sort({ it.name })}" var="categoryInstance">
						<div class="card">
							
							<div class="card-header h5 mb-0 pointer" data-toggle="collapse" data-target="#${categoryInstance.name.replaceAll("\\s","")}Info">
								${categoryInstance.name}
							</div>
							<div id="${categoryInstance.name.replaceAll("\\s","")}Info" class="collapse" data-parent="#categoryAccordion">
								<div class="btn-group btn-bar">
									<button class="btn btn-primary" type="submit" form="${categoryInstance.name.replaceAll("\\s","")}Form">Save</button>
									<g:link class="btn btn-light" action="deleteCategory" id="${categoryInstance.id}" onclick="return confirm('Delete category?')">Delete</g:link>
								</div>
								<div class="card-body">
									<g:form action="updateCategory" class="ajaxForm" data-success="successfulSave(data)" id="${categoryInstance.id}" name="${categoryInstance.name.replaceAll("\\s","")}Form" autocomplete='off'>
										<div class="row">
											<div class="col-3">
												<div class="form-group">
													<label for="options">What short description of this category should users see as a tooltip?</label><br>
													<textarea class="form-control" name="description" rows="4" cols="50">${categoryInstance.description}</textarea>
												</div>
												<div class="form-group">
													<label for="searchType">How should users search this category?</label><br>
													<g:radioGroup name="searchType"
																  labels="['Checkboxes','Dropdown menu','Radio buttons','Text field']"
																  values="['CHECKBOX','DROPDOWN','RADIO','FIELD']"
																  value="${categoryInstance.searchType}"
																  onchange="jQuery.ajax({type:'POST',data:jQuery(this.form).serialize(), url:'/tagging/changeType',success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){}});return false">
														<div>${it.radio} ${it.label}</div>
													</g:radioGroup>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<label for="options">What options can users select from? (One option per line.)</label><br>
													<textarea class="form-control" name="options" rows="10" cols="50"><g:join in="${categoryInstance.options}" delimiter="\r\n"/></textarea>
												</div>
											</div>
											<div class="col-3 handleContainer">
												<label>What tag handles should BALSA scan files / filenames for?</label>
												<g:each in="${categoryInstance.handles}">
												<div class="card m-1">
													<div class="card-body p-2">
														<input type="hidden" name="handle" value="${it.handle}:${it.value}">
														${it.handle} &nbsp; &#8594; &nbsp; ${it.value}
														<button type="button" class="close" onclick="this.parentElement.parentElement.remove()">&times;</button>
													</div>
												</div>
												</g:each>
											</div>
											<div class="col-3">
												<label>Add new tag handle</label>
												<div class="form-group">
													<label for="searchType">Handle</label>
													<input type="text" class="form-control tagHandle">
												</div>
												<div class="form-group">
													<label for="searchType">Value</label>
													<input type="text" class="form-control tagValue" placeholder="leave blank to use captured group">
												</div>
												<button type="button" class="btn btn-light" onclick="addTagHandle('${categoryInstance.name.replaceAll("\\s","")}Info')">Add tag handle</button>
											</div>
										</div>
									</g:form>
								</div>
							</div>
						</div>
						</g:each>
					</div>
				</div>
			</div>
		</div>
		<div id="createCategoryModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">New Category</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body">
						<g:form name="createCategoryForm" action="saveCategory">
							<div class="form-group row">
								<label class="col-4 col-form-label" for="categoryName">Category Name</label>
								<div class="col-8">
									<input name="name" class="form-control" id="categoryName"/>
								</div>
							</div>
						</g:form>
					</div>
					<div class="modal-footer">
						<div class="btn-group">
							<button type="submit" class="btn btn-primary" form="createCategoryForm">Create</button>
							<button type="button" class="btn btn-light" data-toggle="modal" data-target="#createCategoryModal">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script id="tagHandleTemplate" type="text/template">
			<div class="card m-1">
				<div class="card-body p-2">
					<input type="hidden" name="handle" value="TAGHANDLE:TAGVALUE">
					TAGHANDLE &nbsp; &#8594; &nbsp; TAGVALUE
					<button type="button" class="close" onclick="this.parentElement.parentElement.remove()">&times;</button>
				</div>
			</div>
		</script>
	</body>
</html>