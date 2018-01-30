package com.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.model.Product;

@Controller
@RequestMapping(value = "/products")
public class ProductsController extends BaseController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		Product product = productService.findById(id);
		int size = product.getImages().size() / 3;
		if (product.getImages().size() % 3 > 0)
			size++;
		ModelAndView model = new ModelAndView();
		model.addObject("product", product);
		model.addObject("slideSize", size);
		model.setViewName("product");
		return model;
	}
}
