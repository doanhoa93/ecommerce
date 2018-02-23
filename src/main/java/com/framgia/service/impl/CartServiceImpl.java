package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.framgia.bean.CartInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.CartService;

public class CartServiceImpl extends BaseServiceImpl implements CartService {

	@Override
	public UserInfo getUser(Integer cartId) {
		try {
			return ModelToBean.toUserInfo(getCartDAO().getUser(cartId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProductInfo getProduct(Integer cartId) {
		try {
			return ModelToBean.toProductInfo(getCartDAO().getProduct(cartId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CartInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toCartInfo(getCartDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CartInfo findById(Serializable key) {
		try {
			return ModelToBean.toCartInfo(getCartDAO().findById(key));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(CartInfo entity) {
		try {
			getCartDAO().delete(toCart(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public CartInfo saveOrUpdate(CartInfo entity) {
		try {
			Cart cart = getCartDAO().saveOrUpdate(toCart(entity));
			return ModelToBean.toCartInfo(cart);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<CartInfo> getObjects() {
		try {
			return getCartDAO().getObjects().stream().map(ModelToBean::toCartInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);			
			return null;
		}
	}

	@Override
	public List<CartInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getCartDAO().getObjectsByIds(keys).stream().map(ModelToBean::toCartInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CartInfo> getObjects(int off, int limit) {
		try {
			return getCartDAO().getObjects(off, limit).stream().map(ModelToBean::toCartInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CartInfo getCart(Integer userId, Integer productId) {
		try {
			return ModelToBean.toCartInfo(getCartDAO().getCart(userId, productId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CartInfo> getCarts(Integer userId, String page, int limit) {
		try {
			int off;
			if (StringUtils.isEmpty(page)) {
				off = 0;
			} else
				off = (Integer.parseInt(page) - 1) * limit;
			return getCartDAO().getCarts(userId, off, limit).stream().map(ModelToBean::toCartInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Cart toCart(CartInfo cartInfo) {
		Cart cart = getCartDAO().getFromSession(cartInfo.getId());
		if (cart == null) {
			cart = new Cart();
			cart.setId(cartInfo.getId());
			cart.setProduct(new Product(cartInfo.getProductId()));
			cart.setUser(new User(cartInfo.getUserId()));
		} 
		
		cart.setQuantity(cartInfo.getQuantity());
		return cart;
	}
}
