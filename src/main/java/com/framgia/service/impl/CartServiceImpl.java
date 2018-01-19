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
	public Cart findBy(String attribute, Serializable key) {
		return getCartDAO().findBy(attribute, key);
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
	public List<Cart> getList() {
		return getCartDAO().getList();
	}

	@Override
	public List<Cart> getList(List<Integer> keys) {
		return getCartDAO().getList(keys);
	}

	@Override
	public List<Cart> getList(int limit) {
		return getList(limit);
	}
}
