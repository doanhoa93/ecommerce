package com.framgia.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.CartInfo;
import com.framgia.bean.CategoryInfo;
import com.framgia.bean.CommentInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.PromotionInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ProductFilter;

public interface ProductService extends BaseService<Integer, ProductInfo> {
	PromotionInfo getPromotion(Integer productId);

	CategoryInfo getCategory(Integer productId);

	List<OrderProductInfo> getOrderProducts(Integer productId);

	List<CommentInfo> getComments(Integer productId);

	List<ImageInfo> getImages(Integer productId);

	List<OrderInfo> getOrders(Integer productId);

	List<CartInfo> getCarts(Integer productId);

	List<UserInfo> getOrderedUser(Integer productId);

	List<ProductInfo> getProducts(Integer categoryId, String page, int limit);

	List<ProductInfo> getProducts(Integer categoryId, int off, int limit, Order order);

	List<ProductInfo> filterProducts(Integer categoryId, ProductFilter productFilter, String page,
	    Integer limit);

	List<ProductInfo> hotProducts(int limit);

	List<ProductInfo> recentProducts(Date date, int limit);

	List<ProductInfo> randomProducts(int limit);

	List<ProductInfo> getNewProducts(Date date, int limit);

	boolean createProduct(ProductInfo productInfo);

	boolean updateProduct(ProductInfo oldProduct, ProductInfo newProduct);

	boolean updateRecent(ProductInfo productInfo);
}
