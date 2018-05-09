package com.framgia.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.constant.Paginate;
import com.framgia.model.Category;
import com.framgia.model.Product;
import com.framgia.search.CategoryRepository;
import com.framgia.search.ProductRepository;

@Controller
@RequestMapping(value = "searches")
public class SearchesController extends BaseController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "search", required = false) String data,
	    @RequestParam(value = "page", required = false) String page,
	    @RequestParam(value = "objectType", required = false) String objectType) {
		ModelAndView model = new ModelAndView("searches");
		if (StringUtils.isEmpty(data))
			data = "";

		int pageInt = 0;
		if (StringUtils.isNotEmpty(page))
			pageInt = Integer.parseInt(page) - 1;

		if (Product.class.getSimpleName().toLowerCase().equals(objectType)) {
			Page<Product> pageProducts = productRepository.findByNameOrInformation(data, data,
			    new PageRequest(pageInt, Paginate.PRODUCT_SEARCH_LIMIT));
			List<Product> products = pageProducts.getContent();

			model.addObject("products", products);
			model.addObject("paginate", setPaginate(page, products.size(),
			    (int) pageProducts.getTotalElements(), Paginate.PRODUCT_SEARCH_LIMIT));
		} else {
			Page<Category> pageCategories = categoryRepository.findByName(data,
			    new PageRequest(pageInt, Paginate.PRODUCT_SEARCH_LIMIT));
			List<Category> categories = pageCategories.getContent();
			model.addObject("categories", categories);
			model.addObject("paginate", setPaginate(page, categories.size(),
			    (int) pageCategories.getTotalElements(), Paginate.PRODUCT_SEARCH_LIMIT));
		}

		return model;
	}
}
