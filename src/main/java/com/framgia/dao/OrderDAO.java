package com.framgia.dao;

import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.User;

public interface OrderDAO extends BaseDAO<Integer, Order> {
	User getUser(Integer orderId);

	List<OrderProduct> getOrderProducts(Integer orderId);
}
