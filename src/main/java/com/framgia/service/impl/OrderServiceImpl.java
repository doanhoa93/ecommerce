package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Override
	public User getUser(Integer orderId) {
		return getOrderDAO().getUser(orderId);
	}

	@Override
	public List<OrderProduct> getOrderProducts(Integer orderId) {
		return getOrderDAO().getOrderProducts(orderId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts(Integer orderId) {
		List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderId);
		return (List<Product>) orderProducts.stream().map(OrderProduct::getProduct);
	}

	@Override
	public Order findBy(String attribute, Serializable key) {
		return getOrderDAO().findBy(attribute, key);
	}

	@Override
	public Order findById(Serializable key) {
		return getOrderDAO().findById(key);
	}

	@Override
	public void delete(Order entity) {
		try {
			getOrderDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Order entity) {
		try {
			getOrderDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Order> getObjects() {
		return getOrderDAO().getObjects();
	}

	@Override
	public List<Order> getObjectsByIds(List<Integer> keys) {
		return getOrderDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Order> getObjects(int limit) {
		return getOrderDAO().getObjects(limit);
	}
}
