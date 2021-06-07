<tfoot>
	<tr>
		<th class="ts-pager  tablesorter-pager" colspan="${cols}">
			<div class="form-inline">
				<div class="mx-auto">
					<div class="btn-group mr-3" role="group">
						<button type="button" class="btn btn-light first" title="first">First</button>
						<button type="button" class="btn btn-light prev" title="previous">←</button>
					</div>
					<span class="pagedisplay"></span>
					<div class="btn-group ml-3" role="group">
						<button type="button" class="btn btn-light next" title="next">→</button>
						<button type="button" class="btn btn-light last" title="last">Last</button>
					</div>
				</div>
				<select class="form-control custom-select ml-3 pagesize d-none" title="Select page size">
					<option value="5">5</option>
					<option value="10">10</option>
					<option selected="selected" value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
				</select>
			</div>
		</th>
	</tr>
</tfoot>