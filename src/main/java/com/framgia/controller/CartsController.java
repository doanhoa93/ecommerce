package com.framgia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.CartInfo;
import com.framgia.constant.Paginate;

@Controller
public class CartsController extends BaseController {

	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView("carts");
		List<CartInfo> carts = cartService.getCarts(currentUser().getId(), page, Paginate.CART_LIMIT);
		model.addObject("carts", carts);
		model.addObject("cartsSize", cartService.getCarts(currentUser().getId(), null, 0).size());
		model.addObject("paginate", setPaginate(carts.size(), page, Paginate.PRODUCT_LIMIT));
		return model;
	}

	@RequestMapping(value = "/products/{productId}/carts", method = RequestMethod.POST)
	public String create(RedirectAttributes redirect, @PathVariable Integer productId,
	        @ModelAttribute("cartInfo") CartInfo cartInfo) {
		HashMap<String, Object> flash = new HashMap<>();
		try {
			CartInfo cartInf = cartService.getCart(currentUser().getId(), productId);
			cartInfo.setProductId(productId);
			cartInfo.setUserId(currentUser().getId());

			if (cartInfo.getQuantity() == 0)
				cartInfo.setQuantity(1);

			if (cartInf != null) {
				cartInfo.setId(cartInf.getId());
				cartInfo.setQuantity(cartInfo.getQuantity() + cartInf.getQuantity());
			}

			cartService.saveOrUpdate(cartInfo);
			flash.put("type", "success");
			flash.put("content", messageSource.getMessage("cart.success", null, Locale.US));
			redirect.addFlashAttribute("flash", flash);
			return "redirect:/carts";
		} catch (Exception e) {
			e.printStackTrace();
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
			CartInfo cartInfo = cartService.findById(id);
			cartInfo.setQuantity((Integer) hashMap.get("quantity"));
			cartService.saveOrUpdate(cartInfo);
			hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
		} catch (Exception e) {
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
		}
		return toJson(hashMap);
	}

	@RequestMapping(value = "/carts/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		CartInfo cartInfo = cartService.findById(id);
		if (cartService.delete(cartInfo))
			hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
		else
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
		return toJson(hashMap);
	}
}
