package com.framgia.service;

import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface CartService extends BaseService<Integer, Cart> {
	User getUser(Integer cartId);

	Product getProduct(Integer cartId);
}
