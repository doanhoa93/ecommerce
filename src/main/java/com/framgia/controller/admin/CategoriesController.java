package com.framgia.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.constant.Paginate;

@Controller("admin/category")
@RequestMapping(value = "admin/categories")
public class CategoriesController extends AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("adminCategories");
		if (StringUtils.isNotEmpty(entries)) {
			if (entries.equals("all"))
				model.addObject("categories", categoryService.getObjects());
			else
				model.addObject("categories", categoryService.getObjects(0, Integer.parseInt(entries)));
		} else
			model.addObject("categories", categoryService.getObjects(0, Paginate.ADMIN_OBJECT_LIMIT));
		return model;
	}
}
