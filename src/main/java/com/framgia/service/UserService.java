package com.framgia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.framgia.bean.CommentInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.ProfileInfo;
import com.framgia.bean.UserInfo;

public interface UserService extends BaseService<Integer, UserInfo> {
	UserInfo findByEmail(String email);

	ProfileInfo getProfile(Integer userId);

	List<CommentInfo> getComments(Integer userId);

	List<OrderProductInfo> getOrderProducts(Integer userId);

	List<ProductInfo> getOrderedProducts(Integer userId);

	List<ProductInfo> getCartedProducts(Integer userId);

	boolean validate(UserInfo user);

	UserInfo getFromCookie(HttpServletRequest request);

	void unremember();
}
