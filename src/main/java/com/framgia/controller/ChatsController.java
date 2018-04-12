package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.framgia.bean.ChatInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Paginate;
import com.framgia.constant.Role;
import com.framgia.helper.SendChat;
import com.framgia.service.ChatService;

@Controller
public class ChatsController extends BaseController {

	@Autowired
	private SendChat sendChat;

	@Autowired
	private ChatService chatService;

	@SuppressWarnings("unchecked")
	@MessageMapping(value = "chats/{token}")
	public void chat(@DestinationVariable("token") String token, String chat)
	    throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, String> hashMap = toHashMap(chat);
		ChatInfo chatInfo = new ChatInfo();
		chatInfo.setSender(userService.findByToken(token));
		chatInfo.setReceiver(userService.getUsers(Role.ADMIN).get(0));
		chatInfo.setContent(hashMap.get("content"));
		chatInfo = chatService.createChat(chatInfo);
		if (chatInfo != null)
			sendChat.send("/topic/chats/" + token, chatInfo);
	}

	@RequestMapping(value = "chats/{token}")
	public @ResponseBody String index(@PathVariable("token") String token,
	    @RequestParam(value = "off", required = false) int off) throws IOException {
		HashMap<String, Object> hashMap = new HashMap<>();
		UserInfo userInfo = userService.findByToken(token);
		if (off >= 0 && userInfo != null) {
			hashMap.put("chats", chatService.getChats(userInfo.getId(), off, Paginate.CHAT_LIMIT,
			    Order.desc("createdAt")));
		}
		return toJson(hashMap);
	}

	@RequestMapping(value = "chats/{token}", method = RequestMethod.PATCH)
	public @ResponseBody void update(@PathVariable("token") String token) throws IOException {
		UserInfo userInfo = userService.findByToken(token);
		if (userInfo != null) {
			chatService.updateReadAll(userInfo.getId());
			currentUser().setNewMessage(false);
		}
	}
}
