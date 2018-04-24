package com.framgia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.CartInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.constant.Paginate;
import com.framgia.helper.CustomSession;
import com.framgia.validator.CartValidator;

@Controller
public class CartsController extends BaseController {

	@Autowired
	private CartValidator cartValidator;

	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView("carts");
		List<CartInfo> carts = null;
		if (currentUser() != null) {
			carts = cartService.getCarts(currentUser().getId(), page, Paginate.CART_LIMIT,
			    Order.desc("id"));
			model.addObject("cartsSize",
			    cartService.getCarts(currentUser().getId(), null, 0, null).size());
		} else {
			carts = cartService.getCartsWithGuest(CustomSession.current(), page,
			    Paginate.CART_LIMIT, Order.desc("id"));
			model.addObject("cartsSize",
			    cartService.getCartsWithGuest(CustomSession.current(), null, 0, null).size());
		}

		model.addObject("carts", carts);
		model.addObject("orderInfo", new OrderInfo());
		if (StringUtils.isNotEmpty(page))
			model.setViewName("cartsPartial");
		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/carts", method = RequestMethod.POST)
	public @ResponseBody String create(@ModelAttribute("cartInfo") CartInfo cartInfo,
	    BindingResult result) throws JsonProcessingException {
		HashMap<String, Object> flash = new HashMap<>();
		try {
			cartInfo.setUser(currentUser());
			cartValidator.validateCreate(cartInfo, result);
			if (!result.hasErrors() && cartService.createCart(cartInfo)) {
				flash.put("result", "success");
				flash.put("isNew", (cartInfo.getQuantity() == 1));
			} else
				flash.put("result", "fail");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			flash.put("result", "fail");
		} finally {
			return toJson(flash);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/carts/{id}", method = RequestMethod.PATCH)
	public @ResponseBody String update(@RequestBody String data, @PathVariable("id") Integer id,
	    BindingResult result) throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			CartInfo cartInfo = cartService.findById(id, true);
			hashMap = toHashMap(data);
			cartInfo.setQuantity((Integer) hashMap.get("quantity"));
			cartValidator.validateUpdate(cartInfo, currentUser(), result);
			if (result.hasErrors())
				hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			else {
				cartService.saveOrUpdate(cartInfo);
				hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
			}
		} catch (Exception e) {
			logger.info(e);
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
		}
		return toJson(hashMap);
	}

	@RequestMapping(value = "/carts/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable("id") Integer id)
	    throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		CartInfo cartInfo = cartService.findById(id, true);
		if (cartValidator.validateDelete(cartInfo, currentUser()) && cartService.delete(cartInfo))
			hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
		else
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
		return toJson(hashMap);
	}
}
