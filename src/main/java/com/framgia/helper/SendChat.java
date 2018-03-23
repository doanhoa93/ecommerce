package com.framgia.helper;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.framgia.bean.ChatInfo;
import com.framgia.bean.UserInfo;

@Component
public class SendChat {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public void send(String chanel, ChatInfo chatInfo) {
		setUserNull(chatInfo.getSender());
		setUserNull(chatInfo.getReceiver());

		simpMessagingTemplate.convertAndSend(chanel, chatInfo);
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("newMessage", "true");
		simpMessagingTemplate.convertAndSend("/topic/newMessages", hashMap);
	}

	private void setUserNull(UserInfo user) {
		if (user != null) {
			user.setCarts(null);
			user.setNotifications(null);
			user.setPassword(null);
			user.setOrders(null);
			user.setProfile(null);
			user.setRole(null);
			user.setUnWatchedNotifications(0);
		}
	}
}
