$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
        beforeSend : function(xhr) {
	        xhr.setRequestHeader(header, token);
        }
    });
	
	if($('#editor').length)
	    CKEDITOR.replace('editor');
	
	$(document).on('shown.bs.modal', function() {
		$('input[list]').on('input', function(e) {
		    var $input = $(e.target),
		        $options = $('#' + $input.attr('list') + ' option'),
		        $hiddenInput = $('#' + $input.attr('id') + '-hidden'),
		        $hiddentInputName = $('#' + $input.attr('id') + '-name-hidden'),
		        label = $input.val();
	
		    var exist = false;
		    for(var i = 0; i < $options.length; i++) {
		        var $option = $options.eq(i);
	
		        if($option.val() === label) {
		        	$hiddenInput.val($option.attr('data-value'));
		        	exist = true;
		            break;
		        }
		    }
		    
		    if(!exist && label != '') {
		    	$hiddenInput.val(0);
		    	if($hiddentInputName.length != 0) 
		    		$hiddentInputName.val(label);
		    }
		});
	});
	
	$(document).on('click', '.new-category', function() {
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
	
	$(document).on('submit', 'form#create-category', function(e) {
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
