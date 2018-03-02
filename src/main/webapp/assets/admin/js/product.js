$(document).ready(function() {
	$(document).on('change', '.product-form .avatar', function() {
		readURL(this, $('.product-avatar-panel'));
		$(this).after('<span>Type: ' + this.files[0].type + 
				', Size: ' + (this.files[0].size / 1048576).toFixed(2) + ' MB</span>');			
	});
	
	function readURL(input, image) {
	    if (input.files && input.files[0]) {
	    	setURL(input.files[0], image);
	    }
	}	
	
	$(document).on('change', '.product-form .image', function() {
		for(var i = 0; i < this.files.length; i++) {
			var imagePanel = '<img class="img-responsive product-image-panel" />';
			$(this).next().html(imagePanel);
			var productPanel = $(this).next().children().first(); 
			setURL(this.files[i], productPanel);
			$(this).after('<span>Type: ' + this.files[0].type + 
					', Size: ' + (this.files[0].size / 1048576).toFixed(2) + ' MB</span>');			
		}
	});
	
	function setURL(file, image) {
    	var reader = new FileReader();
    	reader.onload = function (e) {
    		image.attr('src', e.target.result);
    	}
    	reader.readAsDataURL(file);
	}
	
	$(document).on('change', '.has-sale-off', function() {
		$('.promotion-product').toggle();
	});
	
	$(document).on('click', '.add-image', function() {
		var form = '<i class="fa fa-minus remove-image" aria-hidden="true"> Remove image</i>' +
                   '<div class="product-image-form">' +
                   '<input id="imagesStatus" name="imagesStatus" type="hidden" value="0" />' +
                   '<input id="imageIds" name="imageIds" type="hidden" value="0" />' +
		           '<input id="imageFiles" name="imageFiles" type="file" class="image" value="">' +
                   '<div class="product-image"></div><hr></div>';
		$('.product-image-forms').append(form);
	});
	
	$(document).on('click', '.remove-image', function() {
		$(this).next().children().first().val(-1);
		$(this).next().hide();
		$(this).hide();
	});
	
	$(document).on('submit', 'form#create-product', function(e) {
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
	        		var errorMsgs = [];
	        		$.each(data.errors, function(index, error) {
	        			if(error.field == 'imageFiles') {
	        				if(!errorMsgs.includes(error.defaultMessage)) {
	        					errorMsgs.push(error.defaultMessage);
	        					$('.product-image-label').after('<div class="error">' + error.defaultMessage + '</div>');
	        				}
	        			} else
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
	
	$(document).on('submit', 'form#update-product', function(e) {
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
	        		var errorMsgs = [];
	        		$.each(data.errors, function(index, error) {
	        			if(error.field == 'imageFiles') {
	        				if(!errorMsgs.includes(error.defaultMessage)) {
	        					errorMsgs.push(error.defaultMessage);
	        					$('.product-image-label').after('<div class="error">' + error.defaultMessage + '</div>');
	        				}
	        			} else
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
