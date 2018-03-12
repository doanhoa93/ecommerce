package com.framgia.service;

import com.framgia.bean.NotificationInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.UserInfo;

public interface NotificationService extends BaseService<Integer, NotificationInfo> {
	UserInfo getUser(Integer notificationId);

	OrderInfo getOrder(Integer notificationId);
}
