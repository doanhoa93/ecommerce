package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Image;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Price;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;
import com.framgia.model.User;
import com.framgia.service.ProductService;
import com.mysql.jdbc.StringUtils;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Override
	public List<Order> getOrders(Integer productId) {
		List<OrderProduct> orderProducts = getProductDAO().getOrderProducts(productId);
		return (List<Order>) orderProducts.stream().map(OrderProduct::getOrder).collect(Collectors.toList());
	}

	@Override
	public List<Cart> getCarts(Integer productId) {
		return (List<Cart>) getProductDAO().getCarts(productId);
	}

	@Override
	public List<User> getOrderedUser(Integer productId) {
		return (List<User>) getOrders(productId).stream().map(Order::getUser).collect(Collectors.toList());
	}

	@Override
	public List<OrderProduct> getOrderProducts(Integer productId) {
		return getProductDAO().getOrderProducts(productId);
	}

	@Override
	public List<Comment> getComments(Integer productId) {
		return getProductDAO().getComments(productId);
	}

	@Override
	public List<Image> getImages(Integer productId) {
		return getProductDAO().getImages(productId);
	}

	@Override
	public Recent getRecent(Integer productId) {
		return getProductDAO().getRecent(productId);
	}

	@Override
	public Promotion getPromotion(Integer productId) {
		return getProductDAO().getPromotion(productId);
	}

	@Override
	public Category getCategory(Integer productId) {
		return getProductDAO().getCategory(productId);
	}

	@Override
	public Product findBy(String attribute, Serializable key, boolean lock) {
		return getProductDAO().findBy(attribute, key, lock);
	}

	@Override
	public Product findById(Serializable key) {
		return getProductDAO().findById(key);
	}

	@Override
	public boolean delete(Product entity) {
		try {
			getProductDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Product entity) {
		try {
			getProductDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Product> getObjects() {
		return getProductDAO().getObjects();
	}

	@Override
	public List<Product> getObjectsByIds(List<Integer> keys) {
		return getProductDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Product> getObjects(int limit) {
		return getProductDAO().getObjects(limit);
	}

	@Override
	public List<Product> filterProducts(Integer categoryId, String name, String priceLow, String priceHigh) {
		float low, high;
		if (StringUtils.isNullOrEmpty(name))
			name = "";

		if (StringUtils.isNullOrEmpty(priceLow))
			low = Price.MIN_PRICE;
		else
			low = Float.parseFloat(priceLow);

		if (StringUtils.isNullOrEmpty(priceHigh))
			high = Price.MAX_PRICE;
		else
			high = Float.parseFloat(priceHigh);

		return getProductDAO().filterProducts(categoryId, name, low, high);
	}

	@Override
	public List<Product> getProducts(Integer categoryId) {
		return getProductDAO().getProducts(categoryId);
	}
}
