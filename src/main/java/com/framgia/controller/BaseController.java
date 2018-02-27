package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Role;
import com.framgia.service.CartService;
import com.framgia.service.CategoryService;
import com.framgia.service.OrderService;
import com.framgia.service.ProductService;
import com.framgia.service.SuggestService;
import com.framgia.service.UserService;

public class BaseController {

	@Autowired
	public CartService cartService;

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public ProductService productService;

	@Autowired
	public UserService userService;

	@Autowired
	public OrderService orderService;

	@Autowired
	public SuggestService suggestService;

	@Autowired
	public MessageSource messageSource;

	@Autowired
	public HttpServletRequest request;

	@Autowired
	public HttpServletResponse response;

	public Logger logger = Logger.getLogger(this.getClass());

	public UserInfo currentUser() {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		if (userInfo == null)
			userInfo = userService.getFromCookie(request);
		
		if (userInfo != null)
			userInfo = userService.findById(userInfo.getId());
		return userInfo;
	}

	public String toJson(HashMap<String, Object> hashMap) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(hashMap);
	}

	@SuppressWarnings("rawtypes")
	public HashMap toHashMap(String data) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(data, HashMap.class);
	}

	public HashMap<String, Object> setPaginate(int length, String page, int limit) {
		int start, end;
		boolean more;
		if (StringUtils.isEmpty(page))
			start = 1;
		else
			start = Integer.parseInt(page);

		if (start == 1 && length < limit)
			end = 1;
		else if (length == limit)
			end = start + 1;
		else
			end = 2;

		if (length == limit)
			more = true;
		else
			more = false;

		HashMap<String, Object> paginate = new HashMap<>();
		paginate.put("start", start);
		paginate.put("end", end);
		paginate.put("more", more);
		return paginate;
	}

	public boolean isAdmin(UserInfo userInfo) {
		return userInfo.getRole().equals(Role.Admin);
	}
}
