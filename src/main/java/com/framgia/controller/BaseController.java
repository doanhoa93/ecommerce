package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Role;
import com.framgia.service.CartService;
import com.framgia.service.CategoryService;
import com.framgia.service.ChatService;
import com.framgia.service.OrderService;
import com.framgia.service.ProductService;
import com.framgia.service.PromotionService;
import com.framgia.service.SuggestService;
import com.framgia.service.UserService;

public class BaseController {

	@Autowired
	public CartService cartService;

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public ChatService chatService;

	@Autowired
	public ProductService productService;

	@Autowired
	public UserService userService;

	@Autowired
	public OrderService orderService;

	@Autowired
	public PromotionService promotionService;

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
		try {
			return userService
			    .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		} catch (Exception e) {
			return null;
		}
	}

	public String toJson(HashMap<String, Object> hashMap) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(hashMap);
	}

	@SuppressWarnings("rawtypes")
	public HashMap toHashMap(String data)
	    throws JsonParseException, JsonMappingException, IOException {
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

	public List<FieldError> convertErrorsToMap(List<FieldError> fieldErrors) {
		return fieldErrors.stream().map(fieldError -> {
			return new FieldError(fieldError.getObjectName(), fieldError.getField(), messageSource
			    .getMessage(fieldError.getCode(), fieldError.getArguments(), Locale.US));
		}).collect(Collectors.toList());
	}

	public HashMap<String, Object> convertErrorsToHashMap(List<FieldError> fieldErrors) {
		Iterator<FieldError> iterator = fieldErrors.iterator();
		HashMap<String, Object> map = new HashMap<>();
		while (iterator.hasNext()) {
			FieldError fieldError = iterator.next();
			map.put(fieldError.getField(), messageSource.getMessage(fieldError.getCode(),
			    fieldError.getArguments(), Locale.US));
		}
		return map;
	}
}
