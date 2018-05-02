package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.NotificationInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Notification;
import com.framgia.model.Order;
import com.framgia.model.User;
import com.framgia.service.NotificationService;

public class NotificationServiceImpl extends BaseServiceImpl implements NotificationService {

	@Override
	public NotificationInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean
			    .toNotificationInfo(getNotificationDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public NotificationInfo findById(Serializable key) {
		try {
			return findBy("id", key, true);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public NotificationInfo saveOrUpdate(NotificationInfo entity) {
		try {
			getNotificationDAO().saveOrUpdate(toNotification(entity));
			return entity;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(NotificationInfo entity) {
		try {
			getNotificationDAO().delete(toNotification(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<NotificationInfo> getObjects() {
		try {
			return getNotificationDAO().getObjects().stream().map(ModelToBean::toNotificationInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<NotificationInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getNotificationDAO().getObjectsByIds(keys).stream()
			    .map(ModelToBean::toNotificationInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo getUser(Integer notificationId) {
		try {
			return ModelToBean.toUserInfo(getNotificationDAO().getUser(notificationId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public OrderInfo getOrder(Integer notificationId) {
		try {
			return ModelToBean.toOrderInfo(getNotificationDAO().getOrder(notificationId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Notification toNotification(NotificationInfo notificationInfo) {
		Notification notification = getNotificationDAO().getFromSession(notificationInfo.getId());
		if (notification == null) {
			notification = new Notification();
			notification.setId(notificationInfo.getId());
			notification.setUser(new User(notificationInfo.getUser().getId()));
			notification.setOrder(new Order(notificationInfo.getOrder().getId()));
		}

		notification.setContent(notificationInfo.getContent());
		notification.setWatched(notificationInfo.isWatched());
		return notification;
	}
}
