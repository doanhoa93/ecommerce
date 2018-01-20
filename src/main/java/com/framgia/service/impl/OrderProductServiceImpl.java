package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.OrderProductService;

public class OrderProductServiceImpl extends BaseServiceImpl implements OrderProductService {

	@Override
	public User getUser(Integer orderProductId) {
		Order order = getOrderProductDAO().getOrder(orderProductId);
		if (order != null)
			return getOrderDAO().getUser(order.getId());
		return null;
	}

	@Override
	public Order getOrder(Integer orderProductId) {
		return getOrderProductDAO().getOrder(orderProductId);
	}

	@Override
	public Product getProduct(Integer orderProductId) {
		return getOrderProductDAO().getProduct(orderProductId);
	}

	@Override
	public OrderProduct findBy(String attribute, Serializable key) {
		return getOrderProductDAO().findBy(attribute, key);
	}

	@Override
	public OrderProduct findById(Serializable key) {
		return getOrderProductDAO().findById(key);
	}

	@Override
	public void delete(OrderProduct entity) {
		try {
			getOrderProductDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(OrderProduct entity) {
		try {
			getOrderProductDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<OrderProduct> getObjects() {
		return getOrderProductDAO().getObjects();
	}

	@Override
	public List<OrderProduct> getObjectsByIds(List<Integer> keys) {
		return getOrderProductDAO().getObjectsByIds(keys);
	}

	@Override
	public List<OrderProduct> getObjects(int limit) {
		return getOrderProductDAO().getObjects(limit);
	}
}
