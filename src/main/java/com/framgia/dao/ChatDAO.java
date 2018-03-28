package com.framgia.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.model.Chat;
import com.framgia.model.User;

public interface ChatDAO extends BaseDAO<Integer, Chat> {
	List<Chat> getChats(Integer userId, int off, int limit, Order order);

	List<User> getRecentUsers(Integer receiverId);

	void updateReadAll(Integer receiverId);

	void updateReadAll(Integer senderId, Integer receiverId);
}
