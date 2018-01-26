package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class OrdersController extends BaseController {
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String index() {
		return "orders";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody String data)
	        throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> hashMap = toHashMap(data);
		List<String> strCartIds = (List<String>) hashMap.get("cartIds");
		if (!strCartIds.isEmpty()) {
			List<Integer> cartIds = strCartIds.stream().map(Integer::parseInt).collect(Collectors.toList());
			hashMap.clear();
			if (orderService.createOrder(currentUser().getId(), cartIds))
				hashMap.put("url", "/orders");
			else
				hashMap.put("error", "Cannot create this order");
			return toJson(hashMap);
		}
		return "";
	}
}
