$(document).ready(function() {
	$(document).on('change', '.suggest-form .avatar', function() {
		readURL(this, $('.suggest-avatar-panel'));
	});
	
	function readURL(input, image) {
	    if (input.files && input.files[0]) {
	    	var reader = new FileReader();
	    	reader.onload = function (e) {
	    		image.attr('src', e.target.result);
	    	}
	    	reader.readAsDataURL(input.files[0]);
	    }
	}
	
	$(document).on('submit', 'form#create-suggest', function(e) {
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
                        if($(error).filter('.information').length)
                            $('textarea[name=' + $(error).attr('data-name') + ']').after(error);                        
                        else if($(error).filter('.user').length)
                            $('.form-suggest').before(error);
                        else                     
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
	
	$(document).on('submit', 'form#update-suggest', function(e) {
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
                        if($(error).filter('.information').length)
                            $('textarea[name=' + $(error).attr('data-name') + ']').after(error);                        
                        else if($(error).filter('.user').length)
                            $('.form-suggest').before(error);
                        else                     
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
