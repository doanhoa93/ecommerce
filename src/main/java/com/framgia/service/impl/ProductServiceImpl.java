package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.framgia.bean.ProductInfo;
import com.framgia.constant.ProductFilter;
import com.framgia.helper.BeanToModel;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Image;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
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
	public ProductInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toProductInfo(getProductDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ProductInfo findById(Serializable key) {
		try {
			return ModelToBean.toProductInfo(getProductDAO().findById(key));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(ProductInfo entity) {
		try {
			getProductDAO().delete(BeanToModel.toProduct(entity));
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(ProductInfo entity) {
		try {
			getProductDAO().saveOrUpdate(BeanToModel.toProduct(entity));
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ProductInfo> getObjects() {
		try {
			return getProductDAO().getObjects().stream().map(product -> ModelToBean.toProductInfo(product))
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getProductDAO().getObjectsByIds(keys).stream().map(product -> ModelToBean.toProductInfo(product))
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ProductInfo> getObjects(int off, int limit) {
		try {
			return getProductDAO().getObjects(off, limit).stream().map(product -> ModelToBean.toProductInfo(product))
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getProducts(Integer categoryId, String page, int limit) {
		try {
			int off;
			if (StringUtils.isEmpty(page))
				off = 0;
			else
				off = (Integer.parseInt(page) - 1) * limit;

			return getProductDAO().filterProducts(categoryId, new ProductFilter(null, null, null), off, limit);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> filterProducts(Integer categoryId, ProductFilter productFilter, String page, Integer limit) {
		try {
			int off;
			if (StringUtils.isEmpty(page)) {
				off = 0;
			} else
				off = (Integer.parseInt(page) - 1) * limit;

			return getProductDAO().filterProducts(categoryId, productFilter, off, limit);
		} catch (Exception e) {
			return null;
		}
	}
}
