package com.framgia.controller.admin;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.OrderInfo;
import com.framgia.constant.Status;
import com.framgia.validator.OrderValidator;

@Controller("admin/order")
@RequestMapping(value = "/admin/orders")
public class OrdersController extends AdminController {

	@Autowired
	private OrderValidator orderValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("adminOrders");
		model.addObject("orders", orderService.getObjects());
		model.addObject("statuses", Status.statuses);
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		OrderInfo order = orderService.findById(id);
		ModelAndView model = new ModelAndView("adminOrder");
		if (order != null) {
			model.addObject("order", order);
			model.addObject("orderProducts", order.getOrderProducts());
			model.addObject("statuses", Status.statuses);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public @ResponseBody String update(@RequestBody String data, @PathVariable("id") Integer id, BindingResult result)
	        throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			hashMap = toHashMap(data);
			OrderInfo order = orderService.findById(id);
			orderValidator.validStatus((int) hashMap.get("status"), order, result);
			if (result.hasErrors()) {
				hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			} else {
				order.setStatus((int) hashMap.get("status"));
				orderService.saveOrUpdate(order);
				hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
				hashMap.put("statuses", Status.statuses);
			}
			return toJson(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
}
