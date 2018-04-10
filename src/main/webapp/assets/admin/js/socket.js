$(document).ready(function() {
	var token, userId;
	var subscribers = {};
	if($('.token').val() != null && $('.token').val() != '') {
		token = $('.token').val();
		connect();
	}
	
	function connect() {
		var socket = new SockJS('/Ecommerce/admin/ws');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);
		initTime();
	}
	
	function onConnected() {
		$.ajax({
			type: 'GET',
		    url: getContextPath() + '/admin/tokens',
			dataType: 'json',
		    contentType: 'application/json',
		    success: function (data) {
		    	if(data.tokens != undefined) {
		    		$.each(data.tokens, function(index, token) {
		    			var subscribe = stompClient.subscribe('/topic/chats/' + token, onChatReceived);
		    			subscribers[token] = {subscribe: subscribe, token: token};
		    		});
		    	}        	
		    }
		});		
		
		stompClient.subscribe('/topic/registers', onRegisterReceived);	
		stompClient.subscribe('/topic/unregisters', onUnRegisterReceived);
        stompClient.subscribe('/topic/newMessages', onNewChatReceived);
        stompClient.subscribe('/topic/orders', onOrderReceived);		
	}
	
	function onError(error) {
	}
	
    function onNewChatReceived(data) {
        if(!$('.admin-chats').length)
            $('.new-message').addClass('user-chat-new-message');            
    }   	
	
	function onChatReceived(data) {
		var chat = JSON.parse(data.body);
		if($('.chat').data('id') == chat.sender.id || ($('.chat').data('id') == chat.receiver.id && chat.sender.token == token))
			$('.chat').append(setChatPanel(chat));
		else {
			$('.user-chat-' + chat.sender.id).remove();
			$('.users').prepend(setUserPanel(chat.sender));
			$('.new-message-' + chat.sender.id).addClass('user-chat-new-message');
		}
		scrollChat();
	}	
    
    function onOrderReceived(data) {
        var order = JSON.parse(data.body);
        $('.orders-body').prepend(setOrderPanel(order));
        $('.new-order-item-icon').show();
    }   	
	
	function onRegisterReceived(data) {
		var user = JSON.parse(data.body);
		if(subscribers[user.token] == undefined) {
		    var subscribe = stompClient.subscribe('/topic/chats/' + user.token, onChatReceived);	
		    subscribers[user.token] = {subscribe: subscribe, token: user.token};
		}
	}
	
	function onUnRegisterReceived(data) {
		var user = JSON.parse(data.body);
		for(var element in subscribers) {
			if(element == user.token) {
				subscribers[element].subscribe.unsubscribe();
				delete subscribers[element];
				break;
			}
		}
	}	
	
	function setChatPanel(chat) {
	    var chatPanel = $('.chat-item').first().clone();
	    chatPanel.removeClass();
	    chatPanel.addClass('clearfix chat-item chat-item-' + chat.id);
	    chatPanel.attr('data-id', chat.id);
	    var font = chatPanel.find('.primary-font');
	    font.removeClass();
	    
	    var textMuted = chatPanel.find('.text-muted');
	    textMuted.removeClass();
	    
	    if(chat.sender.id == userId) {
	        chatPanel.addClass('right');
	        chatPanel.find('.chat-img').removeClass('pull-left');
	        chatPanel.find('.chat-img').addClass('pull-right');
	        font.addClass('pull-right');
	    } else {
            chatPanel.addClass('left');
            chatPanel.find('.chat-img').addClass('pull-left');
            chatPanel.find('.chat-img').removeClass('pull-right');
            textMuted.addClass('.pull-right');
	    }
	    font.addClass('primary-font');
	    chatPanel.find('.primary-font').text(chat.sender.name);
	    chatPanel.find('.img-circle').attr('src', chat.sender.avatar);
	    chatPanel.find('.chat-time').text(jQuery.timeago(chat.createdAt));
        chatPanel.find('.chat-content').text(chat.content);
        return chatPanel.prop('outerHTML');
	}
	
	function setUserPanel(user) {
	    var userPanel = $('.user-chat').first().clone();
	    userPanel.removeClass();
	    userPanel.addClass('user user-chat user-chat-' + user.id);
	    userPanel.attr('data-id', user.id);
	    userPanel.attr('data-token', user.token);
	    userPanel.find('.user-chat-avatar').attr('src', user.avatar);
	    userPanel.find('.user-name').text(user.name);
	    userPanel.find('.user-new-message').addClass('new-message-' + user.id);
	    if(user.adminNewMessage)
	        userPanel.find('.user-new-message').addClass('user-chat-new-message');
	    
	    return userPanel.prop('outerHTML'); 
	}
	
	function setOrderPanel(order) {
	    var orderPanel = $('.order-item').first().clone();
	    orderPanel.removeClass();
	    orderPanel.addClass('order-item new-order-item tr-href-js order-' + order.id);
	    orderPanel.attr('data-href', getContextPath() + '/admin/orders/' + order.id);
	    orderPanel.find('.index').html('New');
	    orderPanel.find('.user-name').html(order.user == null ? 'Guets' : order.user.name);
	    orderPanel.find('.order-product').html(order.orderProducts.length);
        var totalPrice = order.totalPrice.toLocaleString('en-US', {currency: 'USD', style: 'currency'});
	    orderPanel.find('.total-price').html(totalPrice);
	    orderPanel.find('.created-at').html(new Date(order.createdAt).toUTCString());
	    var status = orderPanel.find('.order-status');
	    status.removeClass();
	    status.addClass('center order-status status-' + order.status);
	    status.html(order.status);
	    return orderPanel.prop('outerHTML');
	}
	
	$(document).on('click', '.user-chat', function() {
		userId = $(this).data('id');
		$('.chat').data('id', userId);		
		$('.user-chat').removeClass('user-chat-active');
		$(this).addClass('user-chat-active');
		$('.new-message-' + userId).removeClass('user-chat-new-message');
		if($('.user-chat-new-message').length == 1)
			$('.new-message').removeClass('user-chat-new-message');
			
		$.ajax({
			type: 'GET',
		    url: getContextPath() + '/admin/chats/' + userId,
			dataType: 'json',
		    contentType: 'application/json',
		    data: {off: 0},
		    success: function (data) {
		    	if(data.chats != undefined && data.chats.length > 0) {
		    		for(var index = 0; index < data.chats.length; index++)
		    			$('.chat').prepend(setChatPanel(data.chats[index]));
		    		$('.chat-item-0').remove();
		    		$('.panel-body').scrollTop($('.chat-item-' + data.chats[0].id).position().top);
		    	}        	
		    }
		});
		
		$.ajax({
			type: 'PATCH',
		    url: getContextPath() + '/admin/chats/' + userId,
			dataType: 'json',
		    contentType: 'application/json'
		});			
	});
	
	$(document).on('click', '.btn-chat', function() {
		var content = $('.input-chat').val();
		if(content != '' && userId != undefined)
			stompClient.send("/app/admin/chats/" + userId, {}, JSON.stringify({'content': content, 'token': token}));
		$('.input-chat').val('');		
	});
	
	$(document).on('keypress', '.input-chat', function(event) {
		var content = $('.input-chat').val();
        if (event.keyCode == 13 && content != '' && userId != undefined) {
			stompClient.send("/app/admin/chats/" + userId, {}, JSON.stringify({'content': content, 'token': token}));
			$('.input-chat').val('');
        } else if (event.keyCode == 13)
            $('.input-chat').val('');		
	});
	
	$('.panel-body').scroll(function() {
		var chatItem = $(document).find('.chat-item').first();
		var top = chatItem.position().top - 15; 
		if(top == $(this).position().top) {
			$.ajax({
			    method: 'GET',
			    url: getContextPath() + '/admin/chats/' + userId,
				dataType: 'json',
			    contentType: 'application/json',
			    data: {off: chatItem.data('id')},
			    success: function(data) {
			    	if(data.chats != undefined && data.chats.length > 0) {
			    		for(var index = 0; index < data.chats.length; index++)
			    			$('.chat').prepend(setChatPanel(data.chats[index]));
			    		$('.panel-body').scrollTop($('.chat-item-' + data.chats[0].id).position().top);
			    	}
			    }
			});			
		}
	});	
	
	$(document).on('input', '.name-user', function() {
		var name = $(this).val().toUpperCase();
		$('.user').each(function() {
			if($(this).find('.user-name').text().toUpperCase().includes(name))
				$(this).show();
			else
				$(this).hide();
		});
	});	

	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
	
	function initTime() {
        $('.chat-time').each(function() {
            $(this).html(jQuery.timeago($(this).html()));
        });
	}	
	
	function scrollChat() {
		$('.panel-body').scrollTop($('.panel-body').prop('scrollHeight'));
	}		
});
