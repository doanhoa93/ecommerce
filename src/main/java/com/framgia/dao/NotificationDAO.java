package com.framgia.dao;

import com.framgia.model.Notification;
import com.framgia.model.Order;
import com.framgia.model.User;

public interface NotificationDAO extends BaseDAO<Integer, Notification> {
	User getUser(Integer notificationId);

	Order getOrder(Integer notificationId);
}
