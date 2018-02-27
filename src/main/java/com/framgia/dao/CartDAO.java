package com.framgia.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface CartDAO extends BaseDAO<Integer, Cart> {
	User getUser(Integer cartId);

	Product getProduct(Integer cartId);
	
	List<Cart> getCarts(Integer userId, int off, int limit, Order order);
	
	Cart getCart(Integer userId, Integer productId);	
}
