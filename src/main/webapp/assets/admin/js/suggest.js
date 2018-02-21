$(document).ready(function() {
	$(document).on('click', '.tr-href', function() {
	    var url = $(this).data('href');
		$.ajax({
		    method: 'GET',
		    headers: {
		    	'Accept' : 'text/html',
		    	'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
		    },
		    url: url,
		}).done(function(data) {
	        $('#form-suggest').modal('show');
	        $('#form-suggest').html(data);
        })	    
    });
	
	$(document).on('click', '.btn-suggest-update', function() {
	    var status = $(this).data('status');
	    var id = $(this).parent().data('id');
	    data = {status: status};
		$.ajax({
		    method: 'PATCH',
			dataType: 'json',
		    contentType: 'application/json',
		    url: window.location.pathname + '/' + id,
		    data: JSON.stringify(data)
		}).done(function(data) {
			if(data.msg == 'success') {
				$('#form-suggest').html('');
	        	$('#form-suggest').modal('hide');
	        	$('.suggest-' + id + ' .suggest-status').attr('class', 'suggest-status status-' + status);
	        	$('.suggest-' + id + ' .suggest-status').html(status);
	        	table.rows().invalidate();
			}
        })	    
    });	
});
