package com.framgia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.CategoryInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.constant.Paginate;
import com.framgia.constant.Price;
import com.framgia.helper.ProductFilter;

@Controller
@RequestMapping(value = "categories")
public class CategoriesController extends BaseController {

	@RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer categoryId,
	    @RequestParam(required = false) Map<String, Object> params) {
		String priceLow = null, priceHigh = null, name = null, page = null;
		HashMap<String, String> orders = new HashMap<>();
		if (!params.isEmpty()) {
			priceLow = (String) params.get("priceLow");
			priceHigh = (String) params.get("priceHigh");
			name = (String) params.get("name");
			page = (String) params.get("page");
			orders.put("orderAttr", (String) params.get("orderAttr"));
			orders.put("orderType", (String) params.get("orderType"));
		}

		ModelAndView model = new ModelAndView();
		CategoryInfo categoryInfo = categoryService.findById(categoryId, false);
		if (categoryInfo != null) {
			List<ProductInfo> products = null;
			ProductFilter productFilter = new ProductFilter(name, priceLow, priceHigh, orders);
			if (productFilter.isFilterProduct()) {
				products = productService.filterProducts(categoryId, productFilter, page,
				    Paginate.PRODUCT_LIMIT, productFilter.getOrder());
				model.setViewName("productsPartial");
				model.addObject("paginate",
				    setPaginate(page,
				        products.size(), productService
				            .filterProducts(categoryId, productFilter, null, 0, null).size(),
				        Paginate.PRODUCT_LIMIT));
			} else {
				model.addObject("categories", categoryService.getObjects());
				model.addObject("maxPrice", Price.MAX_PRICE);
				model.addObject("minPrice", Price.MIN_PRICE);
				products = productService.getProducts(categoryId, page, Paginate.PRODUCT_LIMIT,
				    productFilter.getOrder());
				model.setViewName("products");
				model.addObject("paginate",
				    setPaginate(page, products.size(),
				        productService.getProducts(categoryId, null, 0, null).size(),
				        Paginate.PRODUCT_LIMIT));
			}

			model.addObject("title", "products");
			model.addObject("categoryId", categoryId);
			model.addObject("products", products);
		} else
			model.setViewName("404");

		return model;
	}
}
