$(document).ready(function() {
	var userId = 0;
	if($('.currentUser').val() > 0) {
		userId = $('.currentUser').val();
		connect();
	}
	
	function connect() {
		var socket = new SockJS('/Ecommerce/ws');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);
		initTimeOfNotification();
	}
	
	function onConnected() {
		stompClient.subscribe('/topic/notifications/' + userId, onMessageReceived);
	}
	
	function onError(error) {
	}
	
	function onMessageReceived(data) {
		var notification = JSON.parse(data.body);
		$('.notification-size').html(Number($('.notification-size').html()) + 1);
		$('.notifications-body').prepend(notificationPanel(notification));
	}
	
	function updateNotification(notificationId) {
		stompClient.send("/app/update/" + notificationId, {}, JSON.stringify({
		    userId : userId,
		    read: true
		}))
	}
	
	function initTimeOfNotification() {
		$('.notification-time').each(function() {
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
	
	$(document).on('click', '.notification', function() {
		updateNotification($(this).data('id'));
		$('.notification-size').html(Number($('.notification-size').html()) - 1);
		$(this).removeClass('unwatched');
	});
});
