$(document).ready(function() {
	var token;
	if($('.token').val() != null && $('.token').val() != '') {
		token = $('.token').val();
		connect();
	}
	
	function connect() {
		var socket = new SockJS('/Ecommerce/ws');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);
		initTime();
	}
	
	function onConnected() {
		stompClient.subscribe('/topic/notifications/' + token, onNotificationReceived);
		stompClient.subscribe('/topic/chats/' + token, onChatReceived);
	}
	
	function onError(error) {
	}
	
	function onChatReceived(data) {
		var chat = JSON.parse(data.body);
		$('.chat').append(setChatPanel(chat));
		if($('.chat-panel').is(':visible')) {
			$('.input-chat').val('');		
			scrollChat();
		} else {
			$('.new-message').addClass('user-chat-new-message');
		}
	}
	
	function onNotificationReceived(data) {
		var notification = JSON.parse(data.body);
		$('.notification-size').html(Number($('.notification-size').html()) + 1);
		$('.notifications-body').prepend(notificationPanel(notification));
	}	
	
	function updateNotification(notificationId) {
		stompClient.send("/app/update/" + notificationId, {}, JSON.stringify({
		    token : token,
		    read: true
		}))
	}
	
	function initTime() {
		$('.notification-time').each(function() {
			$(this).html(jQuery.timeago($(this).html()));
		});
		
		$('.chat-time').each(function() {
			$(this).html(jQuery.timeago($(this).html()));
		});
	}
	
	function notificationPanel(notification) {
		return '<li class="sub-menu-item notification ' + (notification.isWatched ? '' : 'unwatched') + 
		       '" data-id="'+ notification.id + '">' +
	           '<a href="' + getContextPath() + '/orders/' + notification.orderId + '">' +
	           '<span class="notification-content">' + notification.content + '</span></a>' +
	           '<div class="small notification-time">' + jQuery.timeago(notification.createdAt) + '</div></li>';
	}
	
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
	
	function setChatPanel(chat) {
		var direction = chat.sender.token == token ? 'left' : 'right';
		var pullDirection = chat.sender.token == token ? 'pull-right' : '';
		var pullDirectionReverse = chat.sender.token == token ? '' : 'pull-right';
		return '<li class="' + direction + ' clearfix chat-item chat-item-' + chat.id + '" data-id="' + chat.id + '">' +
		   '<span class="chat-img pull-' + direction + '">' + 
		   '<img src="' + chat.sender.avatar + '" alt="User Avatar" class="img-responsive img-circle" />' +
		   '</span><div class="chat-body clearfix">' +
		   '<div class="header"><strong class="' + pullDirectionReverse + ' primary-font">' + chat.sender.name + '</strong>' + 
		   '<small class="' + pullDirection + ' text-muted">' +
		   '<i class="fa fa-clock-o fa-fw"></i><span class="chat-time">' + jQuery.timeago(chat.createdAt) + '</span></small></div>' +
		   '<p class="chat-content">' + chat.content + '</p></div></li>';
	}
	
	$(document).on('click', '.notification', function() {
		updateNotification($(this).data('id'));
	});
	
	$(document).on('click', '.btn-chat', function() {
		var content = $('.input-chat').val();
		if(content != '')
			stompClient.send("/app/chats/" + token, {}, JSON.stringify({'content': content}));
	});
	
	$(document).on('keypress', '.input-chat', function(event) {
		var content = $('.input-chat').val();
        if (event.keyCode == 13 && content != '')
			stompClient.send("/app/chats/" + token, {}, JSON.stringify({'content': content}));		
	});	
	
	$('.panel-body').scroll(function() {
		var chatItem = $(document).find('.chat-item').first();
		var top = chatItem.position().top - 15; 
		if(top == $(this).position().top) {
			$.ajax({
			    method: 'GET',
			    url: getContextPath() + '/chats/' + token,
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
	
	$(document).on('click', '.chat-title', function() {
		$.ajax({
			type: 'PATCH',
		    url: getContextPath() + '/chats/' + token,
			dataType: 'json',
		    contentType: 'application/json'
		});	
	});			
	
	function scrollChat() {
		$('.panel-body').scrollTop($('.panel-body').prop('scrollHeight'));
	}	
});
