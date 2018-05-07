package com.framgia.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framgia.bean.ProductInfo;
import com.framgia.service.ProductService;

import redis.clients.jedis.Jedis;

@Component
public class UpdateDataTask {

	@Autowired
	private ProductService productService;

	private Jedis jedis;

	public void updateData() {
		jedis = new Jedis();
		jedis.flushAll();
		List<ProductInfo> products = productService.getObjects();
		for (ProductInfo productInfo : products)
			jedis.sadd("products", productInfo.getName());
		jedis.close();
	}
}
