package com.framgia.helper;

import com.framgia.bean.OrderInfo;
import com.framgia.model.Order;

public class ModelToBean {
	public static OrderInfo toOrderInfo(Order order) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(order.getId());
		orderInfo.setTotalPrice(order.getTotalPrice());
		orderInfo.setStatus(order.getStatus());
		orderInfo.setCreatedAt(order.getCreatedAt());
		return orderInfo;
	}
}
