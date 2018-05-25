package com.framgia.dao;

import java.util.List;

import com.framgia.model.Notification;
import com.framgia.model.Order;
import com.framgia.model.User;

public interface NotificationDAO extends BaseDAO<Integer, Notification> {
	User getUser(Integer notificationId);

	Order getOrder(Integer notificationId);

	List<Notification> getNotifications(Integer userId, int off, int limit,
	    org.hibernate.criterion.Order order);

	void updateAll(Integer userId);
}
