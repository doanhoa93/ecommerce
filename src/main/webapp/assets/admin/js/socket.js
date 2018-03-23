$(document).ready(function() {
	initTime();
	
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
	}
	
	function onError(error) {
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
	
	function onRegisterReceived(data) {
		var user = JSON.parse(data.body);
		var subscribe = stompClient.subscribe('/topic/chats/' + user.token, onChatReceived);	
		subscribers[user.token] = {subscribe: subscribe, token: user.token};
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
		var direction = chat.sender.id != userId ? 'left' : 'right';
		var pullDirection = chat.sender.id != userId ? 'pull-right' : '';
		var pullDirectionReverse = chat.sender.id != userId ? '' : 'pull-right';
		return '<li class="' + direction + ' clearfix chat-item chat-item-' + chat.id + '" data-id="' + chat.id + '">' +
		   '<span class="chat-img pull-' + direction + '">' + 
		   '<img src="' + chat.sender.avatar + '" alt="User Avatar" class="img-responsive img-circle" />' +
		   '</span><div class="chat-body clearfix">' +
		   '<div class="header"><strong class="' + pullDirectionReverse + ' primary-font">' + chat.sender.name + '</strong>' + 
		   '<small class="' + pullDirection + ' text-muted">' +
		   '<i class="fa fa-clock-o fa-fw"></i><span class="chat-time">' + jQuery.timeago(chat.createdAt) + '</span></small></div>' +
		   '<p class="chat-content">' + chat.content + '</p></div></li>';
	}
	
	function setUserPanel(user) {
        return '<li class="user user-chat user-chat-' + user.id + '" data-id="' + user.id + '" data-token="' + user.token + '">' +
        	'<img alt="" src="' + user.avatar + '" class="img-responsive user-chat-avatar">' +
        	'<span>' + user.name + '</span>' +
        	'<span class="new-message-' + user.id + (user.adminNewMessage ? ' user-chat-new-message' : '') + '"></span></li>';		
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
		    	$('.chat-item').remove();
		    	if(data.chats != undefined && data.chats.length > 0) {
		    		for(var index = 0; index < data.chats.length; index++)
		    			$('.chat').prepend(setChatPanel(data.chats[index]));
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
        }else if(event.keyCode == 13)
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
			$(this).text((jQuery.timeago($(this).html())));
		});
	}	
	
	function scrollChat() {
		$('.panel-body').scrollTop($('.panel-body').prop('scrollHeight'));
	}		
});
