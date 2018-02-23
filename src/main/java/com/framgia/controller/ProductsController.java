package com.framgia.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.ProductInfo;
import com.framgia.constant.Paginate;
import com.framgia.constant.Price;
import com.framgia.helper.ProductFilter;

@Controller
@RequestMapping(value = "/products")
public class ProductsController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "priceLow", required = false) String priceLow,
	        @RequestParam(value = "priceHigh", required = false) String priceHigh,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView();
		List<ProductInfo> products = null;
		ProductFilter productFilter = new ProductFilter(name, priceLow, priceHigh);
		if (productFilter.isFilterProduct()) {
			products = productService.filterProducts(null, productFilter, page, Paginate.PRODUCT_LIMIT);
			model.setViewName("productsPartial");
		} else {
			model.addObject("categories", categoryService.getObjects());
			model.addObject("maxPrice", Price.MAX_PRICE);
			model.addObject("minPrice", Price.MIN_PRICE);
			products = productService.getProducts(null, page, Paginate.PRODUCT_LIMIT);
			model.setViewName("products");
		}
		model.addObject("paginate", setPaginate(products.size(), page, Paginate.PRODUCT_LIMIT));
		model.addObject("title", "products");
		model.addObject("products", products);
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		ProductInfo productInfo = productService.findById(id);
		ModelAndView model = new ModelAndView();
		if (productInfo != null) {
			int size = productInfo.getImages().size() / 3;
			if (productInfo.getImages().size() % 3 > 0)
				size++;
			model.addObject("product", productInfo);
			model.addObject("slideSize", size);
			model.setViewName("product");
		} else {
			model.setViewName("404");
		}
		return model;
	}
}
