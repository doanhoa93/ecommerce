package com.framgia.service.impl;

import com.framgia.model.Product;
import com.framgia.service.ImageService;

public class ImageServiceImpl extends BaseServiceImpl implements ImageService {

  @Override
  public Product getProduct(Integer imageId) {
    return getImageDAO().getProduct(imageId);
  }
}
