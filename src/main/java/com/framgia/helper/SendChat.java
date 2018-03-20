package com.framgia.helper;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.framgia.bean.ChatInfo;

@Component
public class SendChat {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public void send(String chanel, ChatInfo chatInfo) {
		if (chatInfo.getSender() != null) {
			chatInfo.getSender().setCarts(null);
			chatInfo.getSender().setNotifications(null);
			chatInfo.getSender().setPassword(null);
			chatInfo.getSender().setOrders(null);
			chatInfo.getSender().setProfile(null);
			chatInfo.getSender().setRole(null);
			chatInfo.getSender().setUnWatchedNotifications(0);
		}

		if (chatInfo.getReceiver() != null) {
			chatInfo.getReceiver().setCarts(null);
			chatInfo.getReceiver().setNotifications(null);
			chatInfo.getReceiver().setPassword(null);
			chatInfo.getReceiver().setOrders(null);
			chatInfo.getReceiver().setProfile(null);
			chatInfo.getReceiver().setRole(null);
			chatInfo.getReceiver().setUnWatchedNotifications(0);
		}

		simpMessagingTemplate.convertAndSend(chanel, chatInfo);
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("newMessage", "true");
		simpMessagingTemplate.convertAndSend("/topic/newMessages", hashMap);
	}
}
