$(document).ready(function() {
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
	        	data = JSON.parse(data);
	        	if(data.errors != undefined) {
	        		$('.error').remove();
	        		$.each(data.errors, function(index, error) {
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
	
	connect();
	var stompClient;
	function connect() {
		var socket = new SockJS('/Ecommerce/admin/ws');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);
	}
	
	function onConnected() {
		stompClient.subscribe('/topic/newMessages', onChatReceived);
	}
	
	function onError(error) {
	}
	
	function onChatReceived(data) {
		if(!$('.admin-chats').length)
			$('.new-message').addClass('user-chat-new-message');			
	}		
});
