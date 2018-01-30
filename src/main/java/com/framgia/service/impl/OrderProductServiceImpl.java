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
		try {
			Order order = getOrderProductDAO().getOrder(orderProductId);
			if (order != null)
				return getOrderDAO().getUser(order.getId());
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Order getOrder(Integer orderProductId) {
		try {
			return getOrderProductDAO().getOrder(orderProductId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product getProduct(Integer orderProductId) {
		try {
			return getOrderProductDAO().getProduct(orderProductId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public OrderProduct findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getOrderProductDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public OrderProduct findById(Serializable key) {
		try {
			return getOrderProductDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(OrderProduct entity) {
		try {
			getOrderProductDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(OrderProduct entity) {
		try {
			getOrderProductDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<OrderProduct> getObjects() {
		try {
			return getOrderProductDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OrderProduct> getObjectsByIds(List<Integer> keys) {
		try {
			return getOrderProductDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OrderProduct> getObjects(int limit) {
		try {
			return getOrderProductDAO().getObjects(limit);
		} catch (Exception e) {
			return null;
		}
	}
}
