package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.CartService;

public class CartServiceImpl extends BaseServiceImpl implements CartService {

	@Override
	public User getUser(Integer cartId) {
		try {
			return getCartDAO().getUser(cartId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product getProduct(Integer cartId) {
		try {
			return getCartDAO().getProduct(cartId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cart findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getCartDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cart findById(Serializable key) {
		try {
			return getCartDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Cart entity) {
		try {
			getCartDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Cart entity) {
		try {
			getCartDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Cart> getObjects() {
		try {
			return getCartDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Cart> getObjectsByIds(List<Integer> keys) {
		try {
			return getCartDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Cart> getObjects(int off, int limit) {
		try {
			return getCartDAO().getObjects(off, limit);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Cart> getCarts(Integer userId, String page, int limit) {
		try {
			int off;
			if (StringUtils.isEmpty(page)) {
				off = 0;
			} else
				off = (Integer.parseInt(page) - 1) * limit;
			return getCartDAO().getCarts(userId, off, limit);
		} catch (Exception e) {
			return null;
		}
	}
}
