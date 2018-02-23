package com.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/")
public class PagesController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView("homePage");
		model.addObject("flash", redirect.getFlashAttributes().get("flash"));
		model.addObject("categories", categoryService.getObjects(0, 7));
		model.addObject("hotProducts", productService.getObjects(0, 3));
		model.addObject("recentProducts", productService.getObjects(0, 6));
		model.addObject("recommendProducts", productService.getObjects(0, 6));
		return model;
	}
}
