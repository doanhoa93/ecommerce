package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.CategoryDAO;
import com.framgia.model.Category;

public class CategoryDAOImpl extends BaseDAOAbstract<Integer, Category> implements CategoryDAO {

	public CategoryDAOImpl() {
		super(Category.class);
	}

	@Override
	public Category getParentCategory(Integer categoryId) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("id", categoryId));
		Category category = (Category) criteria.uniqueResult();
		if (category != null) {
			criteria.add(Restrictions.eq("id", category.getParentId()));
			return (Category) criteria.uniqueResult();
		}
		return null;
	}
}
