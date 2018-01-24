package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.CartService;

public class CartServiceImpl extends BaseServiceImpl implements CartService {

	@Override
	public User getUser(Integer cartId) {
		return getCartDAO().getUser(cartId);
	}

	@Override
	public Product getProduct(Integer cartId) {
		return getCartDAO().getProduct(cartId);
	}

	@Override
	public Cart findBy(String attribute, Serializable key, boolean lock) {
		return getCartDAO().findBy(attribute, key, lock);
	}

	@Override
	public Cart findById(Serializable key) {
		return getCartDAO().findById(key);
	}

	@Override
	public void delete(Cart entity) {
		try {
			getCartDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Cart entity) {
		try {
			getCartDAO().saveOrUpdate(entity);
		} catch (Exception e) {
		}
	}

	@Override
	public List<Cart> getObjects() {
		return getCartDAO().getObjects();
	}

	@Override
	public List<Cart> getObjectsByIds(List<Integer> keys) {
		return getCartDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Cart> getObjects(int limit) {
		return getObjects(limit);
	}
}
