package com.framgia.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.service.CategoryService;
import com.framgia.service.ProductService;

@Controller
public class PagesController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("categories", categoryService.getList(7));
		hashMap.put("hotProducts", productService.getList(3));
		hashMap.put("recentProducts", productService.getList(6));
		hashMap.put("recommendProducts", productService.getList(6));
		return new ModelAndView("homePage", "params", hashMap);
	}
}
