$(document).ready(function() {
	setTimeout(function(){
	    $('.flash').slideUp(1000);
	  }, 1500);

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
        beforeSend : function(xhr) {
	        xhr.setRequestHeader(header, token);
        }
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
