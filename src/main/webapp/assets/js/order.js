$(document).ready(function() {
	$('.order-tr').on('click', function() {
		var href = $(this).data('href');
		window.location.replace(getContextPath() + href);
	});
	
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
});
