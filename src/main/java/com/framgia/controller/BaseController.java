package com.framgia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.model.User;
import com.framgia.service.CartService;
import com.framgia.service.CategoryService;
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
	public UserService userService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	public User currentUser() {
		return (User) request.getSession().getAttribute("currentUser");
	}
}
