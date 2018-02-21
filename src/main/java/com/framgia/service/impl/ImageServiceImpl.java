package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.ImageInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Image;
import com.framgia.model.Product;
import com.framgia.service.ImageService;

public class ImageServiceImpl extends BaseServiceImpl implements ImageService {

	@Override
	public ProductInfo getProduct(Integer imageId) {
		try {
			return ModelToBean.toProductInfo(getImageDAO().getProduct(imageId));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ImageInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toImageInfo(getImageDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ImageInfo findById(Serializable key) {
		try {
			return ModelToBean.toImageInfo(getImageDAO().findById(key));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(ImageInfo entity) {
		try {
			getImageDAO().delete(toImage(entity));
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public ImageInfo saveOrUpdate(ImageInfo entity) {
		try {
			return ModelToBean.toImageInfo(getImageDAO().saveOrUpdate(toImage(entity)));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ImageInfo> getObjects() {
		try {
			return getImageDAO().getObjects().stream().map(ModelToBean::toImageInfo).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ImageInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getImageDAO().getObjectsByIds(keys).stream().map(ModelToBean::toImageInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ImageInfo> getObjects(int off, int limit) {
		try {
			return getImageDAO().getObjects(off, limit).stream().map(ModelToBean::toImageInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Image toImage(ImageInfo imageInfo) {
		Image image = getImageDAO().getFromSession(imageInfo.getId());
		if (image == null) {
			image = new Image();
			image.setId(imageInfo.getId());
			image.setProduct(new Product(imageInfo.getProductId()));
		}

		image.setImage(imageInfo.getImage());
		return image;
	}
}
