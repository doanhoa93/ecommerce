$(document).ready(function() {
	$(document).on('click', '.tr-href-js', function(e) {
	    var url = $(this).data('href');
		$.ajax({
		    method: 'GET',
		    headers: {
		    	'Accept' : 'text/html',
		    	'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
		    },
		    url: url,
		}).done(function(data) {
			$('#form-order').modal('show');
        	$('#form-order').html(data);
        });
	});
	
	$(document).on('click', '.btn-order-update', function() {
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
				if(data.warning)
					status = data.warning;
				
				$('#form-order').html('');
				$('#form-order').modal('hide');
				$('.order-' + id + ' .order-status').attr('class', 'order-status status-' + status);
				$('.order-' + id + ' .order-status').html(status);
				table.rows().invalidate();
			}
        })	    
    });	
});
