package com.framgia.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.UserInfo;

@Component
public class SendOrder {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public void send(String chanel, OrderInfo orderInfo) {
		setUserNull(orderInfo.getUser());
		for (OrderProductInfo orderProductInfo : orderInfo.getOrderProducts()) {
			orderProductInfo.setOrder(null);
			orderProductInfo.setProduct(null);
		}
		simpMessagingTemplate.convertAndSend(chanel, orderInfo);
	}

	private void setUserNull(UserInfo user) {
		if (user != null) {
			user.setCarts(null);
			user.setNotifications(null);
			user.setPassword(null);
			user.setOrders(null);
			user.setProfile(null);
			user.setRole(null);
			user.setUnWatchedNotifications(0);
		}
	}
}
