<li class="list-group-item filterListItem">
	<select class="form-control float-left w-auto categorySelect" id="categorySelect" name="categorySelect">
		<option value="" selected>${selectText}</option>
		<g:each in="${categories.sort { it.name }}">
		<option value="${it.name}" title="${it.description}">${it.name}</option>
		</g:each>
	</select>
	<div class="float-left pl-4"></div>
	<button type="button" class="close removeFilterButton"><span aria-hidden="true">&times;</span></button>
</li>