$(document).ready(function() {
	$(document).on('change', '.product-form .avatar', function() {
		readURL(this, $('.product-avatar-panel'));
		$('.field').find('.avatar-info').text('Type: ' + this.files[0].type +  
		        ', Size: ' + (this.files[0].size / 1048576).toFixed(2) + ' MB')
	});
	
	function readURL(input, image) {
	    if (input.files && input.files[0]) {
	    	setURL(input.files[0], image);
	    }
	}	
	
	$(document).on('change', '.product-form .image', function() {
		for(var i = 0; i < this.files.length; i++) {
			var productPanel = $(this).next().children().first(); 
			setURL(this.files[i], productPanel);
	        $(this).prev().text('Type: ' + this.files[0].type +  
	                ', Size: ' + (this.files[0].size / 1048576).toFixed(2) + ' MB')		
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
	
    $('.zoom').zoom();	
	
    $('.product-image').on('click', function(e) {
        $('.product-main-avatar').attr('src', $(this).attr('src'));
        $('.zoom').zoom();
    });    
    
	$(document).on('click', '.add-image', function() {
	    var imageForm = $('.product-image-form').first().clone();
	    imageForm.show();
	    imageForm.find('.image-info').html('');
	    imageForm.find('.product-image-panel').attr('src', '');
	    imageForm.find('.avatar').val();
		$('.product-image-forms').append(imageForm.prop('outerHTML'));
	});
	
	$(document).on('click', '.remove-image', function() {
		$(this).next().children().first().val(-1);
		$(this).parent().hide();
	});
	
	$(document).on('submit', 'form#create-product', function(e) {
		e.preventDefault();    
	    var formData = new FormData(this);
	    $.ajax({
	        url: $(this).attr('action'),
	        type: 'POST',
	        data: formData,
	        success: function (data) {
	            if(isError(data)) {
                    var errorMsgs = [];	                
	                $(data).filter('.error').each(function(index, error)  {
	                    if($(error).filter('.imageFiles').length) {
                            if(!errorMsgs.includes(error)) {
                                errorMsgs.push(error);
                                $('.product-image-label').after(error);
                            }
                        } 	                    
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
	
	$(document).on('submit', 'form#update-product', function(e) {
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
                        if($(error).filter('.imageFiles').length) {
                            if(!errorMsgs.includes(error)) {
                                errorMsgs.push(error);
                                $('.product-image-label').after(error);
                            }
                        }
                        
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
