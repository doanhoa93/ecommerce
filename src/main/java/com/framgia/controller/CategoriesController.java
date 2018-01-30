package com.framgia.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.constant.Paginate;
import com.framgia.constant.Price;
import com.framgia.constant.ProductFilter;
import com.framgia.model.Product;

@Controller
@RequestMapping(value = "categories")
public class CategoriesController extends BaseController {

	@RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer categoryId,
	        @RequestParam(value = "priceLow", required = false) String priceLow,
	        @RequestParam(value = "priceHigh", required = false) String priceHigh,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView();
		List<Product> products = null;
		ProductFilter productFilter = new ProductFilter(name, priceLow, priceHigh);
		if (productFilter.isFilterProduct()) {
			products = productService.filterProducts(categoryId, productFilter, page, Paginate.PRODUCT_LIMIT);
			model.setViewName("productsPartial");
		} else {
			model.addObject("categories", categoryService.getObjects());
			model.addObject("maxPrice", Price.MAX_PRICE);
			model.addObject("minPrice", Price.MIN_PRICE);
			products = productService.getProducts(categoryId, page, Paginate.PRODUCT_LIMIT);
			model.setViewName("products");
		}

		model.addObject("paginate", setPaginate(products.size(), page, Paginate.PRODUCT_LIMIT));
		model.addObject("title", "products");
		model.addObject("categoryId", categoryId);
		model.addObject("products", products);
		return model;
	}
}
