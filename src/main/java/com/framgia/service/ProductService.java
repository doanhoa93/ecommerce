package com.framgia.service;

import java.util.List;

import com.framgia.bean.CartInfo;
import com.framgia.bean.CategoryInfo;
import com.framgia.bean.CommentInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.PromotionInfo;
import com.framgia.bean.RecentInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ProductFilter;

public interface ProductService extends BaseService<Integer, ProductInfo> {
	RecentInfo getRecent(Integer productId);

	PromotionInfo getPromotion(Integer productId);

	CategoryInfo getCategory(Integer productId);

	List<OrderProductInfo> getOrderProducts(Integer productId);

	List<CommentInfo> getComments(Integer productId);

	List<ImageInfo> getImages(Integer productId);

	List<OrderInfo> getOrders(Integer productId);

	List<CartInfo> getCarts(Integer productId);

	List<UserInfo> getOrderedUser(Integer productId);

	List<ProductInfo> getProducts(Integer categoryId, String page, int limit);

	List<ProductInfo> filterProducts(Integer categoryId, ProductFilter productFilter, String page, Integer limit);
}
