package com.framgia.service;

import com.framgia.model.Product;

public interface ImageService extends BaseService {
  Product getProduct(Integer imageId);
}
