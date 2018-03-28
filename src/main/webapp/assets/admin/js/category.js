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
	
	$(document).on('input', '#parent-input', function() {
		if($(this).val() == null || $(this).val() == '')
			$('#parent-input-hidden').val(-1);
	});
	
	$(document).on('submit', 'form#update-category', function(e) {
		e.preventDefault();    
	    var formData = new FormData(this);
	    $.ajax({
	        url: $(this).attr('action'),
	        type: 'POST',
	        data: formData,
	        success: function (data) {
                if(isError(data)) {
                    $('.error').remove();                    
                    var errorMsgs = [];                 
                    $(data).filter('.error').each(function(index, error)  {
                        $('input[name=' + $(error).attr('data-name') + ']').after(error);
                    });
                } else {
                    var url = $(data).filter('.redirect').attr('href');
                    window.location.replace(url);
                }       
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });		
	});	
	
    function isError(data) {
        return $(data).filter('.error').length;
    }     	
});
