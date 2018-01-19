package com.framgia.dao;

import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface CartDAO extends BaseDAO<Integer, Cart> {
	User getUser(Integer cartId);

	Product getProduct(Integer cartId);
}
