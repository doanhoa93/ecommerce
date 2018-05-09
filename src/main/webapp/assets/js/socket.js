$(document).ready(
    function() {
        var token;
        if ($('.token').val() != null && $('.token').val() != '') {
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
            if ($('.chat-panel').is(':visible')) {
                $('.input-chat').val('');
                scrollChat();
            } else {
                $('.new-message').addClass('user-chat-new-message');
            }
        }

        function onNotificationReceived(data) {
            var notification = JSON.parse(data.body);
            $('.notification-size').html(Number($('.notification-size').html()) + 1);
            $('.notification-title').after(notificationPanel(notification));
        }

        function notificationPanel(notification) {
            var notificationPanel = $('.notification').first().clone();
            notificationPanel.attr('data-id', notification.id);
            notificationPanel.removeClass('unwatched');
            if (!notification.isWatched)
                notificationPanel.addClass('unwatched');

            notificationPanel.find('.notification-href').attr('href',
                getContextPath() + '/orders/' + notification.orderId);
            notificationPanel.find('.notification-content').text(notification.content);
            notificationPanel.find('.notification-time').text(
                jQuery.timeago(notification.createdAt));

            return notificationPanel.prop('outerHTML');
        }

        function getContextPath() {
            return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        }

        function setChatPanel(chat) {
            var chatPanel = $('.chat-item').first().clone();
            chatPanel.removeClass();
            chatPanel.addClass('clearfix chat-item chat-item-' + chat.id);
            chatPanel.attr('data-id', chat.id);
            chatPanel.find('.text-muted').removeClass('pull-right');
            chatPanel.find('.primary-font').removeClass('pull-right');
            if (chat.sender.token == token) {
                chatPanel.addClass('left');
                chatPanel.find('.chat-img').removeClass().addClass('chat-img pull-left');
                chatPanel.find('.text-muted').addClass('pull-right');
            } else {
                chatPanel.addClass('right');
                chatPanel.find('.chat-img').removeClass().addClass('chat-img pull-right');
                chatPanel.find('.primary-font').addClass('pull-right');
            }

            chatPanel.find('.img-circle').attr('src', chat.sender.avatar);
            chatPanel.find('.primary-font').text(chat.sender.name);
            chatPanel.find('.chat-time').text(jQuery.timeago(chat.createdAt));
            chatPanel.find('.chat-content').text(chat.content);
            return chatPanel.prop('outerHTML');
        }

        $(document).on('click', '.notification', function() {
            updateNotification($(this).data('id'));
        });

        $(document).on('click', '.notification-title .mark', function(e) {
            $.ajax({
                url : getContextPath() + '/notifications/0',
                method : 'POST',
                success : function() {
                    $('.notification-size').text('0');
                    $('.sub-menu-item').removeClass('unwatched');
                }
            });
        });

        function updateNotification(notificationId) {
            $.ajax({
                url : getContextPath() + '/notifications/' + notificationId,
                method : 'POST',
            });
        }

        $(document).on('click', '.btn-chat', function() {
            var content = $('.input-chat').val();
            if (content != '')
                stompClient.send("/app/chats/" + token, {}, JSON.stringify({
                    'content' : content
                }));
        });

        $(document).on('keypress', '.input-chat', function(event) {
            var content = $('.input-chat').val();
            if (event.keyCode == 13 && content != '')
                stompClient.send("/app/chats/" + token, {}, JSON.stringify({
                    'content' : content
                }));
        });

        $('.panel-body').scroll(
            function() {
                var chatItem = $(document).find('.chat-item').first();
                var top = chatItem.position().top - 15;
                if (top == $(this).position().top) {
                    $.ajax({
                        method : 'GET',
                        url : getContextPath() + '/chats/' + token,
                        dataType : 'json',
                        contentType : 'application/json',
                        data : {
                            off : chatItem.data('id')
                        },
                        success : function(data) {
                            if (data.chats != undefined && data.chats.length > 0) {
                                for (var index = 0; index < data.chats.length; index++)
                                    $('.chat').prepend(setChatPanel(data.chats[index]));
                                $('.panel-body').scrollTop(
                                    $('.chat-item-' + data.chats[0].id).position().top);
                            }
                        }
                    });
                }
            });

        $(document).on('click', '.chat-title', function() {
            $.ajax({
                type : 'PATCH',
                url : getContextPath() + '/chats/' + token,
                dataType : 'json',
                contentType : 'application/json'
            });
        });

        function initTime() {
            $('.notification-time').each(function() {
                $(this).html(jQuery.timeago($(this).html()));
            });

            $('.chat-time').each(function() {
                $(this).html(jQuery.timeago($(this).html()));
            });
        }

        function scrollChat() {
            $('.panel-body').scrollTop($('.panel-body').prop('scrollHeight'));
        }

        function getContextPath() {
            return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
        }
    });
