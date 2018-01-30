package com.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("homePage");
		model.addObject("categories", categoryService.getObjects(7));
		model.addObject("hotProducts", productService.getObjects(3));
		model.addObject("recentProducts", productService.getObjects(6));
		model.addObject("recommendProducts", productService.getObjects(6));
		return model;
	}
}
