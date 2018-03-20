package com.framgia.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.Order;

import com.framgia.bean.ChatInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Chat;
import com.framgia.service.ChatService;

public class ChatServiceImpl extends BaseServiceImpl implements ChatService {

	@Override
	public ChatInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toChatInfo(getChatDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ChatInfo findById(Serializable key) {
		try {
			return findBy("id", key, true);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ChatInfo saveOrUpdate(ChatInfo entity) {
		try {
			return ModelToBean.toChatInfo(getChatDAO().saveOrUpdate(toChat(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(ChatInfo entity) {
		try {
			getChatDAO().delete(toChat(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<ChatInfo> getObjects() {
		try {
			return getChatDAO().getObjects().stream().map(ModelToBean::toChatInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ChatInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getChatDAO().getObjectsByIds(keys).stream().map(ModelToBean::toChatInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ChatInfo> getObjects(int off, int limit) {
		try {
			return getChatDAO().getObjects(off, limit).stream().map(ModelToBean::toChatInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ChatInfo> getChats(Integer userId, int off, int limit, Order order) {
		try {
			return getChatDAO().getChats(userId, off, limit, order).stream().map(ModelToBean::toChatInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public void updateReadAll(Integer receiverId) {
		try {
			getChatDAO().updateReadAll(receiverId);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public void updateReadAll(Integer senderId, Integer receiverId) {
		try {
			getChatDAO().updateReadAll(senderId, receiverId);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<UserInfo> getRecentUsers(Integer receiverId) {
		try {
			return getChatDAO().getRecentUsers(receiverId).stream().map(ModelToBean::toUserInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ChatInfo createChat(ChatInfo chatInfo) {
		try {
			Chat chat = new Chat();
			chat.setSender(getUserDAO().findById(chatInfo.getSender().getId()));
			chat.setReceiver(getUserDAO().findById(chatInfo.getReceiver().getId()));
			chat.setContent(chatInfo.getContent());
			chat.setCreatedAt(new Date());
			chat.setWatched(false);
			return ModelToBean.toChatInfo(getChatDAO().saveOrUpdate(chat));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Chat toChat(ChatInfo chatInfo) {
		Chat chat = getChatDAO().getFromSession(chatInfo.getId());
		if (chat == null) {
			chat = new Chat();
			chat.setId(chatInfo.getId());
			chat.setSender(getUserDAO().findById(chatInfo.getSenderId()));
			chat.setReceiver(getUserDAO().findById(chatInfo.getReceiverId()));
		}

		chat.setContent(chatInfo.getContent());
		chat.setCreatedAt(new Date());
		return chat;
	}
}
