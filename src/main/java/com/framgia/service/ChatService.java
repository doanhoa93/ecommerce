package com.framgia.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.ChatInfo;
import com.framgia.bean.UserInfo;

public interface ChatService extends BaseService<Integer, ChatInfo> {
	List<ChatInfo> getChats(Integer userId, int off, int limit, Order order);

	List<UserInfo> getRecentUsers(Integer receiverId);

	ChatInfo createChat(ChatInfo chatInfo);

	void updateReadAll(Integer receiverId);

	void updateReadAll(Integer senderId, Integer receiverId);
}
