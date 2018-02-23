package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Status;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.service.OrderProductService;

public class OrderProductServiceImpl extends BaseServiceImpl implements OrderProductService {

	@Override
	public UserInfo getUser(Integer orderProductId) {
		try {
			Order order = getOrderProductDAO().getOrder(orderProductId);
			return ModelToBean.toUserInfo(order.getUser());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public OrderInfo getOrder(Integer orderProductId) {
		try {
			return ModelToBean.toOrderInfo(getOrderProductDAO().getOrder(orderProductId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProductInfo getProduct(Integer orderProductId) {
		try {
			return ModelToBean.toProductInfo(getOrderProductDAO().getProduct(orderProductId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public OrderProductInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toOrderProductInfo(getOrderProductDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public OrderProductInfo findById(Serializable key) {
		try {
			return ModelToBean.toOrderProductInfo(getOrderProductDAO().findById(key));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(OrderProductInfo entity) {
		try {
			getOrderProductDAO().delete(toOrderProduct(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public OrderProductInfo saveOrUpdate(OrderProductInfo entity) {
		try {
			OrderProduct orderProduct = getOrderProductDAO().saveOrUpdate(toOrderProduct(entity));
			return ModelToBean.toOrderProductInfo(orderProduct);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<OrderProductInfo> getObjects() {
		try {
			return getOrderProductDAO().getObjects().stream().map(ModelToBean::toOrderProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderProductInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getOrderProductDAO().getObjectsByIds(keys).stream().map(ModelToBean::toOrderProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderProductInfo> getObjects(int off, int limit) {
		try {
			return getOrderProductDAO().getObjects(off, limit).stream().map(ModelToBean::toOrderProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private OrderProduct toOrderProduct(OrderProductInfo orderProductInfo) {
		OrderProduct orderProduct = getOrderProductDAO().getFromSession(orderProductInfo.getId());
		if (orderProduct == null) {
			orderProduct = new OrderProduct();
			orderProduct.setId(orderProductInfo.getId());
			orderProduct.setOrder(new Order(orderProductInfo.getOrderId()));
			orderProduct.setProduct(new Product(orderProductInfo.getProductId()));
		}

		orderProduct.setPrice(orderProductInfo.getPrice());
		orderProduct.setQuantity(orderProductInfo.getQuantity());
		orderProduct.setStatus(Status.getIntStatus(orderProductInfo.getStatus()));
		return orderProduct;
	}
}
