$(document).ready(function() {
	setTimeout(function() {
        $('.flash').slideUp(1000);
    }, 1500);

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
        beforeSend : function(xhr) {
	        xhr.setRequestHeader(header, token);
        }
    });	

    $('.zoom').zoom();

    $('.product-image').on('click', function(e) {
        $('.product-main-avatar').attr('src', $(this).attr('src'));
        $('.zoom').zoom();
    });

	$(document).on('click', '.notification-dropdown', function() {
		$('.notifications-body').toggle();
	});

	$(document).on('submit', 'form', function(e) {
		if($(this).data('confirm') != undefined) {
			var c = confirm($(this).data('confirm'));
			if(!c)
		        e.preventDefault(e);
		}
	});
	
	$(document).on('click', 'input', function(e) {
		if($(this).data('confirm') != undefined) {
			var c = confirm($(this).data('confirm'));
			if(!c)
		        e.preventDefault(e);
		}
	});	
	
	$(document).on('click', '.chat-title', function() {
		hideShowChat();
	});		
	
	$(document).on('click', '.dropdown-toggle', function() {
		hideShowChat();
	});
	
	$(document).click(function(event) { 
	    if(!$(event.target).closest('.item-notification').length) {
	        if($('.notifications-body').is(":visible")) {
	            $('.notifications-body').hide();
	        }
	    }        
	});
	
	$(document).on('submit', 'form#add-cart', function(e) {
        e.preventDefault();
        var formData = new FormData(this);
        $.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            data: formData,
            dataType: 'json',
            success: function (data) {
                $('#add-cart-modal').modal('show');
                if(data.result == 'success') {
                    $('.add-cart-result-content.success').show();
                    $('.add-cart-result-content.fail').hide();
                    if(data.isNew)
                        $('.item-cart .cart-size').text(Number($('.item-cart .cart-size').text()) + 1);
                } else {
                    $('.add-cart-result-content.success').hide();                    
                    $('.add-cart-result-content.fail').show();
                    $('.add-cart-result-content').css('color', 'red');
                }                   
                
                $('.add-cart-result-content').css('text-align', 'center');
                $('.add-cart-result-content').css('font-size', '25px');
                setTimeout(function() {
                    $('#add-cart-modal').modal('hide');
                }, 4000);                
            },
            cache: false,
            contentType: false,
            processData: false
        });         
    });	
	
	function hideShowChat() {
		$('.chat-panel').toggle();
		$('.chat-hidden').toggle();
		$('.new-message').removeClass('user-chat-new-message');
		scrollChat();	
	}
	
	function scrollChat() {
		$('.panel-body').scrollTop($('.panel-body').prop('scrollHeight'));
	}		
});
