package com.framgia.controller.admin;

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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.framgia.bean.ChatInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Paginate;
import com.framgia.helper.SendChat;
import com.framgia.service.ChatService;

@Controller("admin/chat")
public class ChatsController extends AdminController {

	@Autowired
	private SendChat sendChat;

	@Autowired
	private ChatService chatService;

	@SuppressWarnings("unchecked")
	@MessageMapping(value = "admin/chats/{userId}")
	public void chat(@DestinationVariable("userId") Integer userId, String chat)
			throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, String> hashMap = toHashMap(chat);
		ChatInfo chatInfo = new ChatInfo();
		UserInfo userInfo = userService.findById(userId);
		chatInfo.setSender(userService.findByToken(hashMap.get("token")));
		chatInfo.setReceiver(userInfo);
		chatInfo.setContent(hashMap.get("content"));
		chatInfo = chatService.createChat(chatInfo);
		if (chatInfo != null)
			sendChat.send("/topic/chats/" + userInfo.getToken(), chatInfo);
	}

	@RequestMapping(value = "admin/chats")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("adminChats");
		model.addObject("users", chatService.getRecentUsers(currentUser().getId()));
		return model;
	}

	@RequestMapping(value = "admin/chats/{userId}")
	public @ResponseBody String show(@PathVariable("userId") Integer userId,
			@RequestParam(value = "off", required = false) int off) throws IOException {
		HashMap<String, Object> hashMap = new HashMap<>();
		if (off >= 0)
			hashMap.put("chats", chatService.getChats(userId, off, Paginate.CHAT_LIMIT,
					Order.desc("createdAt")));
		return toJson(hashMap);
	}

	@RequestMapping(value = "admin/chats/{userId}", method = RequestMethod.PATCH)
	public @ResponseBody void update(@PathVariable("userId") Integer userId) throws IOException {
		UserInfo userInfo = userService.findById(userId);
		if (userInfo != null)
			chatService.updateReadAll(userId, currentUser().getId());
	}
}
