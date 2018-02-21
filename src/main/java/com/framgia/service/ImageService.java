package com.framgia.service;

import com.framgia.bean.ImageInfo;
import com.framgia.bean.ProductInfo;

public interface ImageService extends BaseService<Integer, ImageInfo> {
	ProductInfo getProduct(Integer imageId);
}
