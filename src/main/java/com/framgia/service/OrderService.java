package com.framgia.service;

import java.util.HashMap;
import java.util.List;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;

public interface OrderService extends BaseService<Integer, OrderInfo> {
	UserInfo getUser(Integer orderId);

	List<OrderProductInfo> getOrderProducts(Integer orderId);

	List<ProductInfo> getProducts(Integer orderId);

	List<OrderInfo> getOrders(Integer userId, String page, int limit);

	int getProductQuantity(Integer orderId);

	OrderInfo createOrder(Integer userId, List<Integer> cartIds);

	boolean acceptOrder(OrderInfo orderInfo);

	boolean updateStatusOrder(OrderInfo orderInfo);
	
	boolean updateOrderProduct(OrderInfo orderInfo, List<HashMap<String, Object>> orderProducts);
}
