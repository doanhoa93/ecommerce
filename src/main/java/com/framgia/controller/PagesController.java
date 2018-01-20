package com.framgia.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController extends BaseController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("categories", categoryService.getObjects(7));
		hashMap.put("hotProducts", productService.getObjects(3));
		hashMap.put("recentProducts", productService.getObjects(6));
		hashMap.put("recommendProducts", productService.getObjects(6));
		return new ModelAndView("homePage", "params", hashMap);
	}
}
