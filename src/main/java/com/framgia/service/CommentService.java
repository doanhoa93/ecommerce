package com.framgia.service;

import com.framgia.bean.CommentInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;

public interface CommentService extends BaseService<Integer, CommentInfo> {
	UserInfo getUser(Integer commentId);

	ProductInfo getProduct(Integer commentId);
}
