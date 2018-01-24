package com.framgia.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.CartInfo;
import com.framgia.helper.BeanToModel;
import com.framgia.model.Cart;

@Controller
public class CartsController extends BaseController {
	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("currentUser", currentUser());
		hashMap.put("carts", userService.getCarts(currentUser().getId()));
		modelAndView.setViewName("carts");
		modelAndView.addObject("params", hashMap);
		return modelAndView;
	}

	@RequestMapping(value = "products/{productId}/carts/create", method = RequestMethod.POST)
	public String create(@PathVariable Integer productId, @ModelAttribute("cartInfo") CartInfo cartInfo) {
		Cart cart = userService.getCart(currentUser().getId(), productId);
		if (cartInfo.getQuantity() == 0)
			cartInfo.setQuantity(1);

		if (cart != null) {
			cart.setQuantity(cartInfo.getQuantity());
		} else {
			cartInfo.setProductId(productId);
			cartInfo.setUserId(currentUser().getId());
			cart = BeanToModel.toCart(cartInfo);
		}
		cartService.saveOrUpdate(cart);
		return "redirect:/carts";
	}
}
