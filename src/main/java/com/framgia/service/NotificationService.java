package com.framgia.service;

import java.util.List;

import com.framgia.bean.NotificationInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.UserInfo;

public interface NotificationService extends BaseService<Integer, NotificationInfo> {
	UserInfo getUser(Integer notificationId);

	OrderInfo getOrder(Integer notificationId);

	List<NotificationInfo> getNotifications(Integer userId, String page, int limit,
	    org.hibernate.criterion.Order order);

	boolean update(Integer id);

	boolean updateAll(Integer userId);
}
