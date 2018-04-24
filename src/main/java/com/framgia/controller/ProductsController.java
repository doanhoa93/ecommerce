package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.RateInfo;
import com.framgia.constant.Paginate;
import com.framgia.constant.Price;
import com.framgia.constant.Rating;
import com.framgia.helper.ProductFilter;

@Controller
@RequestMapping(value = "products")
public class ProductsController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(required = false) Map<String, Object> params)
	    throws JsonParseException, JsonMappingException, IOException {
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
		List<ProductInfo> products = null;
		ProductFilter productFilter = new ProductFilter(name, priceLow, priceHigh, orders);
		if (productFilter.isFilterProduct()) {
			products = productService.filterProducts(null, productFilter, page,
			    Paginate.PRODUCT_LIMIT, productFilter.getOrder());
			model.setViewName("productsPartial");
			model.addObject("paginate",
			    setPaginate(page, products.size(),
			        productService.filterProducts(null, productFilter, null, 0, null).size(),
			        Paginate.PRODUCT_LIMIT));
		} else {
			model.addObject("categories", categoryService.getObjects());
			model.addObject("maxPrice", Price.MAX_PRICE);
			model.addObject("minPrice", Price.MIN_PRICE);
			products = productService.getProducts(null, page, Paginate.PRODUCT_LIMIT,
			    productFilter.getOrder());
			model.setViewName("products");
			model.addObject("paginate", setPaginate(page, products.size(),
			    productService.getProducts(null, null, 0, null).size(), Paginate.PRODUCT_LIMIT));
		}

		model.addObject("title", "products");
		model.addObject("products", products);

		return model;
	}

	@SuppressWarnings("serial")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		ProductInfo productInfo = productService.findById(id, false);
		ModelAndView model = new ModelAndView();
		if (productInfo != null) {
			int size = productInfo.getImages().size() / 3;
			if (productInfo.getImages().size() % 3 > 0)
				size++;
			model.addObject("product", productInfo);
			model.addObject("slideSize", size);
			if (currentUser() != null) {
				RateInfo rateInfo = rateService.getRate(currentUser().getId(), id);
				if (rateInfo == null)
					rateInfo = new RateInfo();
				model.addObject("rate", rateInfo);
			}
			model.setViewName("product");
			model.addObject("categories", categoryService.getObjects());
			model.addObject("rating", new HashMap<String, Integer>() {
				{
					put("min", Rating.MIN);
					put("max", Rating.MAX);
				}
			});
			productService.updateRecent(productInfo);
		} else {
			model.setViewName("404");
		}
		return model;
	}
}
