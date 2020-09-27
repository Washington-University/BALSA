// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require jquery-ui
//= require moment
//= require_tree .
//= require_self
//= require bootstrap
//= require bootstrap-datetimepicker
//= require bootbox.min
//= require jquery.tablesorter
//= require indeterminateCheckbox
//= require uploadr.manifest

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}
