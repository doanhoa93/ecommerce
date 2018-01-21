package com.framgia.dao;

import com.framgia.model.Image;
import com.framgia.model.Product;

public interface ImageDAO extends BaseDAO<Integer, Image> {
	Product getProduct(Integer imageId);
}
