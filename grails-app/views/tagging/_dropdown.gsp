<g:select class="form-control" id="categoryOptions" name="${category.name}" from="${category.options}" noSelection="${['': 'Select ' + category.name]}" onchange="refreshSearchResults()"/>