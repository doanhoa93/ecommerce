package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.ImageDAO;
import com.framgia.model.Image;
import com.framgia.model.Product;

public class ImageDAOImpl extends BaseDAOAbstract<Integer, Image> implements ImageDAO {

	public ImageDAOImpl() {
		super(Image.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(Integer imageId) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.createAlias("images", "images", Criteria.LEFT_JOIN, Restrictions.eq("images.id", imageId));
		return (Product) criteria.uniqueResult();
	}
}
