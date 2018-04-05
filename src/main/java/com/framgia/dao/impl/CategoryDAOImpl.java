package com.framgia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Category> hotCategories(int limit) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("products", "products", Criteria.LEFT_JOIN);
		criteria.createAlias("products.orderProducts", "orderProducrs", Criteria.LEFT_JOIN);
		ProjectionList projectionList = Projections.projectionList()
		    .add(Projections.groupProperty("id"))
		    .add(Projections.count("products.id"), "countProduct");
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.desc("countProduct")).setMaxResults(limit);

		List<Object[]> results = criteria.list();
		List<Integer> categoryIds = new ArrayList<>();
		for (Object[] object : results)
			categoryIds.add((Integer) object[0]);

		return getCategoriesByIdsWithOrder(categoryIds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(int off, int limit, Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(off);

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);

		return criteria.list();
	}
}
