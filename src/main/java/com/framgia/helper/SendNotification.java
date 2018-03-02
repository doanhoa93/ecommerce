package com.framgia.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.framgia.bean.NotificationInfo;

@Component
public class SendNotification {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public void send(NotificationInfo notificationInfo) {
		notificationInfo.setUserId(notificationInfo.getUser().getId());
		notificationInfo.setOrderId(notificationInfo.getOrder().getId());
		notificationInfo.setUser(null);
		notificationInfo.setOrder(null);
		simpMessagingTemplate.convertAndSend("/topic/notifications/" + notificationInfo.getUserId(), notificationInfo);
	}
}
