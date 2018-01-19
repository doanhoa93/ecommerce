package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.CategoryDAO;
import com.framgia.model.Category;
import com.framgia.model.Product;

public class CategoryDAOImpl extends BaseDAOImpl<Integer, Category> implements CategoryDAO {

	public CategoryDAOImpl() {
		super(Category.class);
	}

	@Override
	public Category getParentCategory(Integer categoryId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("id", categoryId));
		Category category = (Category) criteria.uniqueResult();
		if (category != null) {
			criteria.add(Restrictions.eq("id", category.getParentId()));
			return (Category) criteria.uniqueResult();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts(Integer categoryId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("category_id", categoryId));
		return (List<Product>) criteria.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Category> getCatgories(List<Integer> categoryIds) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.in("id", categoryIds));
		return (List<Category>) criteria.list();
	}
}
