package com.framgia.service;

import com.framgia.model.Image;
import com.framgia.model.Product;

public interface ImageService extends BaseService<Integer, Image> {
  Product getProduct(Integer imageId);
}
