$(document).ready(function() {
    table = $('#dataTables-example').DataTable({
        responsive: true
    });
    
    var pathName = window.location.protocol + '//' + window.location.host + window.location.pathname;   
    var maxEntries = '<li class="dropdown dropdown-max-entries">' + 
    	'Max entries: <a class="dropdown-toggle toggle-max-entries" data-toggle="dropdown">' +
    	'<i class="max-entries"></i>' +
        '<i class="fa fa-caret-down"></i></a>' +
    	'<ul class="dropdown-menu">' +
    	'<li><a href="' + pathName + '?entries=10' + '">10</a></li>' +
    	'<li><a href="' + pathName + '?entries=25' + '">25</a></li>' +
    	'<li><a href="' + pathName + '?entries=50' + '">50</a></li>' +
    	'<li><a href="' + pathName + '?entries=100' + '">100</a></li>' +
    	'<li><a href="' + pathName + '?entries=all' + '">All</a></li></ul></li>';    
    
	$('.dataTables_wrapper .dataTables_length').prepend(maxEntries);
	
	var url = new URL(window.location.href);
	if(url.searchParams.get("entries") == '' || url.searchParams.get("entries") == null)
		$('.max-entries').text(10);
	else
		$('.max-entries').text(capitalizeFirstLetter(url.searchParams.get("entries")));
	
	function capitalizeFirstLetter(string) {
	    return string.charAt(0).toUpperCase() + string.slice(1);
	}	
});
