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
	        	data = JSON.parse(data);
	        	if(data.errors != undefined) {
	        		$('.error').remove();
	        		$.each(data.errors, function(index, error) {
	        			if(error.field == 'information')
	        				$('textarea[name=' + error.field + ']').after('<div class="error">' + error.defaultMessage + '</div>');
	        			else if(error.field == 'user') 
	        				$('.form-suggest').before('<div class="error">' + error.defaultMessage + '</div>');
	        			else
	        				$('input[name=' + error.field + ']').after('<div class="error">' + error.defaultMessage + '</div>');
	        		});	        	
	        	} else {
	        		window.location.replace(data.url);
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
	        	data = JSON.parse(data);
	        	if(data.errors != undefined) {
	        		$('.error').remove();
	        		$.each(data.errors, function(index, error) {
	        			if(error.field == 'information')
	        				$('textarea[name=' + error.field + ']').after('<div class="error">' + error.defaultMessage + '</div>');
	        			else if(error.field == 'user') 
	        				$('.form-suggest').before('<div class="error">' + error.defaultMessage + '</div>');
	        			else
	        				$('input[name=' + error.field + ']').after('<div class="error">' + error.defaultMessage + '</div>');
	        		});	        	
	        	} else {
	        		window.location.replace(data.url);
	        	} 
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });		
	});		
});
