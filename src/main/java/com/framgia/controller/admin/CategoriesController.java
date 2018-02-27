package com.framgia.controller.admin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.CategoryInfo;
import com.framgia.bean.ProductInfo;
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

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id,
	        @RequestParam(value = "entries", required = false) String entries) {
		CategoryInfo category = categoryService.findById(id);
		ModelAndView model = new ModelAndView("adminCategory");
		if (category != null) {
			List<ProductInfo> products = null;
			if (StringUtils.isNotEmpty(entries)) {
				if (entries.equals("all"))
					products = productService.getProducts(category.getId(), null, 0);
				else
					products = productService.getProducts(category.getId(), null, Integer.parseInt(entries));
			} else {
				products = productService.getProducts(category.getId(), null, Paginate.ADMIN_OBJECT_LIMIT);
			}
			model.addObject("category", category);
			model.addObject("products", products);
		} else {
			model.setViewName("admin404");
		}
		return model;
	}
}
