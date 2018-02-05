package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

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

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Override
	public List<Order> getOrders(Integer productId) {
		try {
			List<OrderProduct> orderProducts = getProductDAO().getOrderProducts(productId);
			return (List<Order>) orderProducts.stream().map(OrderProduct::getOrder).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Cart> getCarts(Integer productId) {
		try {
			return (List<Cart>) getProductDAO().getCarts(productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> getOrderedUser(Integer productId) {
		try {
			return (List<User>) getOrders(productId).stream().map(Order::getUser).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OrderProduct> getOrderProducts(Integer productId) {
		try {
			return getProductDAO().getOrderProducts(productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Comment> getComments(Integer productId) {
		try {
			return getProductDAO().getComments(productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Image> getImages(Integer productId) {
		try {
			return getProductDAO().getImages(productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Recent getRecent(Integer productId) {
		try {
			return getProductDAO().getRecent(productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Promotion getPromotion(Integer productId) {
		try {
			return getProductDAO().getPromotion(productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Category getCategory(Integer productId) {
		try {
			return getProductDAO().getCategory(productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getProductDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product findById(Serializable key) {
		try {
			return getProductDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
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
		try {
			return getProductDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getObjectsByIds(List<Integer> keys) {
		try {
			return getProductDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getObjects(int limit) {
		try {
			return getProductDAO().getObjects(limit);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> filterProducts(Integer categoryId, String name, String priceLow, String priceHigh) {
		try {
			float low, high;
			if (StringUtils.isEmpty(name))
				name = "";

			if (StringUtils.isEmpty(priceLow))
				low = Price.MIN_PRICE;
			else
				low = Float.parseFloat(priceLow);

			if (StringUtils.isEmpty(priceHigh))
				high = Price.MAX_PRICE;
			else
				high = Float.parseFloat(priceHigh);

			return getProductDAO().filterProducts(categoryId, name, low, high);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getProducts(Integer categoryId) {
		try {
			return getProductDAO().getProducts(categoryId);
		} catch (Exception e) {
			return null;
		}
	}
}
