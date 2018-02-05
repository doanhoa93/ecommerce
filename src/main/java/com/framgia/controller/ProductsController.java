package com.framgia.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.model.Price;
import com.framgia.model.Product;

@Controller
public class ProductsController extends BaseController {

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "priceLow", required = false) String priceLow,
	        @RequestParam(value = "priceHigh", required = false) String priceHigh,
	        @RequestParam(value = "name", required = false) String name) {
		ModelAndView model = new ModelAndView();
		List<Product> products = null;
		if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(priceLow) || !StringUtils.isEmpty(priceHigh)) {
			products = productService.filterProducts(null, name, priceLow, priceHigh);
			model.setViewName("productsPartial");
		} else {
			model.addObject("categories", categoryService.getObjects());
			model.addObject("maxPrice", Price.MAX_PRICE);
			model.addObject("minPrice", Price.MIN_PRICE);
			products = productService.getObjects();
			model.setViewName("products");
		}
		model.addObject("title", "products");
		model.addObject("products", products);
		return model;
	}

	@RequestMapping(value = "/categories/{categoryId}/products", method = RequestMethod.GET)
	public ModelAndView indexWithCategory(@PathVariable Integer categoryId,
	        @RequestParam(value = "priceLow", required = false) String priceLow,
	        @RequestParam(value = "priceHigh", required = false) String priceHigh,
	        @RequestParam(value = "name", required = false) String name) {
		ModelAndView model = new ModelAndView();
		List<Product> products = null;
		if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(priceLow) || !StringUtils.isEmpty(priceHigh)) {
			products = productService.filterProducts(categoryId, name, priceLow, priceHigh);
			model.setViewName("productsPartial");
		} else {
			model.addObject("categories", categoryService.getObjects());
			model.addObject("maxPrice", Price.MAX_PRICE);
			model.addObject("minPrice", Price.MIN_PRICE);
			products = productService.getProducts(categoryId);
			model.setViewName("products");
		}
		model.addObject("title", "products");
		model.addObject("categoryId", categoryId);
		model.addObject("products", products);
		return model;
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		Product product = productService.findById(id);
		ModelAndView model = new ModelAndView();
		if (product != null) {
			int size = product.getImages().size() / 3;
			if (product.getImages().size() % 3 > 0)
				size++;
			model.addObject("product", product);
			model.addObject("slideSize", size);
			model.setViewName("product");
			return model;
		} else {
			model.setViewName("redirect:/");
			return model;
		}
	}
}
