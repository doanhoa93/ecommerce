package com.framgia.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.bean.ProductInfo;
import com.framgia.service.ProductService;

import redis.clients.jedis.Jedis;

public class RedisUtil {

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
