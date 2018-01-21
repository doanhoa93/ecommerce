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
	public Image findBy(String attribute, Serializable key) {
		return getImageDAO().findBy(attribute, key);
	}

	@Override
	public Image findById(Serializable key) {
		return getImageDAO().findById(key);
	}

	@Override
	public void delete(Image entity) {
		try {
			getImageDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Image entity) {
		try {
			getImageDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Image> getList() {
		return getImageDAO().getList();
	}

	@Override
	public List<Image> getList(List<Integer> keys) {
		return getImageDAO().getList(keys);
	}

	@Override
	public List<Image> getList(int limit) {
		return getImageDAO().getList(limit);
	}
}
