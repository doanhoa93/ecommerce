package com.framgia.controller;

import java.util.HashMap;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping(value = "redises")
public class RedisesController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String index() throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		Jedis jedis = new Jedis();
		Set<String> productNames = jedis.smembers("products");
		int i = 0;
		for (String productName : productNames)
			hashMap.put(String.valueOf(i++), productName);

		jedis.close();
		return toJson(hashMap);
	}
}
