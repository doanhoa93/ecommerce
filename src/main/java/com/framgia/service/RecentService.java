package com.framgia.service;

import com.framgia.bean.ProductInfo;
import com.framgia.bean.RecentInfo;

public interface RecentService extends BaseService<Integer, RecentInfo> {
	ProductInfo getProduct(Integer recentId);
}
