package com.framgia.service;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;

public interface OrderProductService extends BaseService<Integer, OrderProductInfo> {
	UserInfo getUser(Integer orderProductId);

	OrderInfo getOrder(Integer orderProductId);

	ProductInfo getProduct(Integer orderProductId);
}
