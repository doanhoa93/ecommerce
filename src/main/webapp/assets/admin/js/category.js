$(document).ready(function() {
	$(document).on('click', '.edit-category', function() {
		var url = $(this).data('href');
		$.ajax({
		    method: 'GET',
		    headers: {
		    	'Accept' : 'text/html',
		    	'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
		    },
		    url: url,
		}).done(function(data) {
			$('#form-category').modal('show');
        	$('#form-category').html(data);
        });		
	});
	
	$(document).on('click', '.delete-category', function() {
		var url = $(this).data('href');
		$.ajax({
		    method: 'DELETE',
		}).done(function(data) {
			$('#form-category').modal('show');
        	$('#form-category').html(data);
        });		
	});	
});
