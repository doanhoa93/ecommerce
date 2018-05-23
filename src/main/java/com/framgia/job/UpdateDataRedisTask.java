package com.framgia.job;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.framgia.model.Product;
import com.framgia.util.HibernateUtil;

import redis.clients.jedis.Jedis;

@Service("updateDataRedisTask")
public class UpdateDataRedisTask {

	@SuppressWarnings("unchecked")
	public void reloadData() {
		Jedis jedis = new Jedis();
		jedis.flushAll();
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Product> products = (List<Product>) session.createCriteria(Product.class).list();
		for (Product product : products)
			jedis.sadd("products", product.getName());
		jedis.close();
	}
}
