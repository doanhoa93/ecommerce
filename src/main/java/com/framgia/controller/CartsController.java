package com.framgia.controller;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.CartInfo;
import com.framgia.helper.BeanToModel;
import com.framgia.model.Cart;

@Controller
public class CartsController extends BaseController {
	
	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("carts", userService.getCarts(currentUser().getId()));
		modelAndView.setViewName("carts");
		return modelAndView;
	}

	@RequestMapping(value = "/products/{productId}/carts", method = RequestMethod.POST)
	public String create(RedirectAttributes redirect, @PathVariable Integer productId,
	        @ModelAttribute("cartInfo") CartInfo cartInfo) {
		HashMap<String, Object> flash = new HashMap<>();
		try {
			Cart cart = userService.getCart(currentUser().getId(), productId);
			if (cartInfo.getQuantity() == 0)
				cartInfo.setQuantity(1);

			if (cart != null) {
				cart.setQuantity(cartInfo.getQuantity() + cart.getQuantity());
			} else {
				cartInfo.setProductId(productId);
				cartInfo.setUserId(currentUser().getId());
				cart = BeanToModel.toCart(cartInfo);
			}
			cartService.saveOrUpdate(cart);
			flash.put("type", "success");
			flash.put("content", messageSource.getMessage("cart.success", null, Locale.US));
			redirect.addFlashAttribute("flash", flash);
			return "redirect:/carts";
		} catch (Exception e) {
			flash.put("type", "error");
			flash.put("content", messageSource.getMessage("cart.error", null, Locale.US));
			redirect.addFlashAttribute("flash", flash);
			return "redirect:/";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/carts/{id}", method = RequestMethod.PATCH)
	public @ResponseBody String update(@RequestBody String data, @PathVariable("id") Integer id)
	        throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			hashMap = toHashMap(data);
			Cart cart = cartService.findById(id);
			cart.setQuantity((Integer) hashMap.get("quantity"));
			cartService.saveOrUpdate(cart);
			hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
		} catch (Exception e) {
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
		}
		return toJson(hashMap);
	}

	@RequestMapping(value = "/carts/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		Cart cart = cartService.findById(id);
		if (cartService.delete(cart))
			hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
		else
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
		return toJson(hashMap);
	}
}
