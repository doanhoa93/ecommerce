package com.framgia.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.ProductInfo;
import com.framgia.constant.Paginate;
import com.framgia.validator.ProductValidator;

@Controller("admin/product")
@RequestMapping(value = "admin/products")
public class ProductsController extends AdminController {

	@Autowired
	private ProductValidator productValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("adminProducts");
		if (StringUtils.isNotEmpty(entries)) {
			if (entries.equals("all"))
				model.addObject("products",
				    productService.getProducts(null, 0, 0, Order.desc("createdAt")));
			else
				model.addObject("products", productService.getProducts(null, 0,
				    Integer.parseInt(entries), Order.desc("createdAt")));
		} else
			model.addObject("products", productService.getProducts(null, 0,
			    Paginate.ADMIN_OBJECT_LIMIT, Order.desc("createdAt")));

		return model;
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView getNew() {
		ModelAndView model = new ModelAndView("adminNewProduct");
		model.addObject("categories", categoryService.getObjects());
		model.addObject("promotions", promotionService.getObjects());
		model.addObject("product", new ProductInfo());
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("product") ProductInfo productInfo,
	    BindingResult result) throws JsonProcessingException {
		ModelAndView model = new ModelAndView("product");
		productValidator.validate(productInfo, result);
		if (!result.hasErrors() && productService.createProduct(productInfo)) {
			model.setViewName("redirect");
			model.addObject("url",
			    request.getContextPath() + "/admin/products/" + productInfo.getId());
		} else {
			model.setViewName("inputError");
			model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
		}

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("id") Integer id) {
		ProductInfo product = productService.findById(id, false);
		ModelAndView model = new ModelAndView("adminProduct");
		if (product != null) {
			int size = product.getImages().size() / 3;
			if (product.getImages().size() % 3 > 0)
				size++;
			model.addObject("slideSize", size);
			model.addObject("product", product);
		} else
			model.setViewName("admin404");
		return model;
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("adminEditProduct");
		ProductInfo productInfo = productService.findById(id, true);
		if (productInfo != null) {
			model.addObject("promotions", promotionService.getObjects());
			model.addObject("categories", categoryService.getObjects());
			model.addObject("product", productInfo);
		} else
			model.setViewName("admin404");
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable("id") Integer id,
	    @ModelAttribute("product") ProductInfo productInfo, BindingResult result)
	    throws JsonProcessingException {
		ModelAndView model = new ModelAndView();
		ProductInfo oldProduct = productService.findById(id, true);
		productValidator.validateUpdate(oldProduct, productInfo, result);
		if (!result.hasErrors() && productService.updateProduct(oldProduct, productInfo)) {
			model.setViewName("redirect");
			model.addObject("url",
			    request.getContextPath() + "/admin/products/" + productInfo.getId());
		} else {
			model.setViewName("inputError");
			model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
		}

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView();
		ProductInfo productInfo = productService.findById(id, true);
		if (productInfo != null) {
			productService.delete(productInfo);
			model.setViewName("redirect:/admin/products");
		} else
			model.setViewName("admin404");
		return model;
	}
}
