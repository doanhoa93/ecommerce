package com.framgia.controller.admin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.CategoryInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.constant.Paginate;
import com.framgia.validator.CategoryValidator;

@Controller("admin/category")
@RequestMapping(value = "admin/categories")
public class CategoriesController extends AdminController {

	@Autowired
	private CategoryValidator categoryValidator;

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

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView getNew() {
		ModelAndView model = new ModelAndView("adminNewCategory");
		List<CategoryInfo> categories = categoryService.getObjects();
		model.addObject("categories", categories);
		model.addObject("categoryInfo", new CategoryInfo());
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult errors) {
		ModelAndView model = new ModelAndView("redirect:/admin/categories");
		categoryValidator.validate(categoryInfo, errors);
		if (errors.hasErrors()) {
			model.setViewName("adminNewCategory");
		} else
			categoryService.createCategory(categoryInfo);
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

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("adminEditCategory");
		CategoryInfo categoryInfo = categoryService.findById(id);
		if (categoryInfo != null) {
			model.addObject("categories", categoryService.getCategoriesWithForNew(categoryInfo.getId()));
			model.addObject("categoryInfo", categoryInfo);
		} else
			model.setViewName("admin404");
		return model;
	}

	@RequestMapping(value = "{id}/update", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable("id") Integer id,
	        @ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult errors) {
		ModelAndView model = new ModelAndView();
		CategoryInfo oldCategory = categoryService.findById(id);
		if (oldCategory != null) {
			categoryValidator.validateUpdate(oldCategory, categoryInfo, errors);
			if (errors.hasErrors()) {
				model.setViewName("adminEditCategory");
			} else if (categoryService.updateCategory(oldCategory, categoryInfo))
				model.setViewName("redirect:/admin/categories/" + oldCategory.getId());
		} else
			model.setViewName("admin404");
		return model;
	}

	@RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView();
		CategoryInfo categoryInfo = categoryService.findById(id);
		if (categoryInfo != null) {
			categoryService.delete(categoryInfo);
			model.setViewName("redirect:/admin/categories");
		} else
			model.setViewName("admin404");
		return model;
	}
}
