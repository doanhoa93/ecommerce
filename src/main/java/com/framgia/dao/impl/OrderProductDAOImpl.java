package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.OrderProductDAO;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;

public class OrderProductDAOImpl extends BaseDAOAbstract<Integer, OrderProduct>
    implements OrderProductDAO {

	public OrderProductDAOImpl() {
		super(OrderProduct.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Order getOrder(Integer orderProductId) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.createAlias("order_products", "order_products", Criteria.LEFT_JOIN,
		    Restrictions.eq("order_products.id", orderProductId));
		return (Order) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(Integer orderProductId) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.createAlias("order_products", "order_products", Criteria.LEFT_JOIN,
		    Restrictions.eq("order_products.id", orderProductId));
		return (Product) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderProduct> getOrderProducts(Integer productId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("product.id", productId));
		return criteria.list();
	}
}
