package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framgia.model.User;
import com.framgia.service.CartService;
import com.framgia.service.CategoryService;
import com.framgia.service.OrderService;
import com.framgia.service.ProductService;
import com.framgia.service.UserService;

public class BaseController {
	
	@Autowired
	CartService cartService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	public User currentUser() {
		return (User) request.getSession().getAttribute("currentUser");
	}
	

	public String toJson(HashMap<String, Object> hashMap) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(hashMap);
	}

	@SuppressWarnings("rawtypes")
	public HashMap toHashMap(String data) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(data, HashMap.class);
	}	
}
