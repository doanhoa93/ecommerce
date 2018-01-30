package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Image;
import com.framgia.model.Product;
import com.framgia.service.ImageService;

public class ImageServiceImpl extends BaseServiceImpl implements ImageService {

	@Override
	public Product getProduct(Integer imageId) {
		try {
			return getImageDAO().getProduct(imageId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Image findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getImageDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Image findById(Serializable key) {
		try {
			return getImageDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
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
		try {
			return getImageDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Image> getObjectsByIds(List<Integer> keys) {
		try {
			return getImageDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Image> getObjects(int off, int limit) {
		try {
			return getImageDAO().getObjects(off, limit);
		} catch (Exception e) {
			return null;
		}
	}
}
