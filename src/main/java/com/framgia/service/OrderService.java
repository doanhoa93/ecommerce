package com.framgia.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.validation.BindingResult;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;

public interface OrderService extends BaseService<Integer, OrderInfo> {
	UserInfo getUser(Integer orderId);

	List<OrderProductInfo> getOrderProducts(Integer orderId);

	List<ProductInfo> getProducts(Integer orderId);

	List<OrderInfo> getOrders(Integer userId, int off, int limit, Order order);

	List<OrderInfo> getNewOrders(Date date, int limit);

	int getOrdersSizeWithStatus(String status);

	double[] getSalesByDate(Date startDate, Date endDate);

	int getProductQuantity(Integer orderId);

	boolean createOrder(OrderInfo orderInfo, BindingResult result);

	boolean acceptOrder(OrderInfo orderInfo);

	boolean updateStatusOrder(OrderInfo orderInfo);

	boolean updateOrderProduct(OrderInfo orderInfo, List<HashMap<String, Object>> orderProducts);
}
