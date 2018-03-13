package com.framgia.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.CartInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;

public interface CartService extends BaseService<Integer, CartInfo> {
	UserInfo getUser(Integer cartId);

	ProductInfo getProduct(Integer cartId);

	List<CartInfo> getCarts(Integer userId, String page, int limit, Order order);

	CartInfo getCart(Integer userId, Integer productId);
	
	boolean createCart(CartInfo cartInfo);
}
