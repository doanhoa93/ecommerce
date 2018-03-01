package com.framgia.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.ProductInfo;
import com.framgia.constant.Paginate;

@Controller("admin/product")
@RequestMapping(value = "admin/products")
public class ProductsController extends AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("adminProducts");
		if (StringUtils.isNotEmpty(entries)) {
			if (entries.equals("all"))
				model.addObject("products", productService.getObjects());
			else
				model.addObject("products", productService.getObjects(0, Integer.parseInt(entries)));
		} else
			model.addObject("products", productService.getObjects(0, Paginate.ADMIN_OBJECT_LIMIT));
		return model;			
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("id") Integer id) {
		ProductInfo product = productService.findById(id);
		ModelAndView model = new ModelAndView("adminProduct");
		if(product != null) {
			int size = product.getImages().size() / 3;
			if (product.getImages().size() % 3 > 0)
				size++;
			model.addObject("slideSize", size);		
			model.addObject("product", product);
		} else
			model.setViewName("admin404");
		return model;			
	}	
}
