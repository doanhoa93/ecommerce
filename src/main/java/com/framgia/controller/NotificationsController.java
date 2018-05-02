package com.framgia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.framgia.bean.NotificationInfo;
import com.framgia.service.NotificationService;

@Controller
public class NotificationsController {

	@Autowired
	private NotificationService notificationService;

	@MessageMapping("update/{id}")
	public void update(@DestinationVariable("id") Integer id) {
		NotificationInfo notificationInfo = notificationService.findById(id, true);
		notificationInfo.setWatched(true);
		notificationService.saveOrUpdate(notificationInfo);
	}
}
