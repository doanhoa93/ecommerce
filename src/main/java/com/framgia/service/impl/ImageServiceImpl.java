package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Image;
import com.framgia.model.Product;
import com.framgia.service.ImageService;

public class ImageServiceImpl extends BaseServiceImpl implements ImageService {

	@Override
	public Product getProduct(Integer imageId) {
		return getImageDAO().getProduct(imageId);
	}

	@Override
	public Image findBy(String attribute, Serializable key, boolean lock) {
		return getImageDAO().findBy(attribute, key, lock);
	}

	@Override
	public Image findById(Serializable key) {
		return getImageDAO().findById(key);
	}

	@Override
	public boolean delete(Image entity) {
		try {
			getImageDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Image entity) {
		try {
			getImageDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Image> getObjects() {
		return getImageDAO().getObjects();
	}

	@Override
	public List<Image> getObjectsByIds(List<Integer> keys) {
		return getImageDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Image> getObjects(int limit) {
		return getImageDAO().getObjects(limit);
	}
}
