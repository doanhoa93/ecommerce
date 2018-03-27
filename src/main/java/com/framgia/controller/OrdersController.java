package com.framgia.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.framgia.bean.OrderInfo;
import com.framgia.validator.OrderValidator;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController extends BaseController {

	@Autowired
	private OrderValidator orderValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("orders");
		if (currentUser() != null) {
			if (StringUtils.isNotEmpty(entries) && !entries.equals("all")) {
				model.addObject("orders", orderService.getOrders(currentUser().getId(), 0,
						Integer.parseInt(entries), Order.desc("id")));
			} else {
				model.addObject("orders",
						orderService.getOrders(currentUser().getId(), 0, 0, Order.desc("id")));
			}
			model.addObject("ordersSize",
					orderService.getOrders(currentUser().getId(), 0, 0, null).size());
		} else
			model.setViewName("redirect:/");
		return model;

	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String create(@ModelAttribute("orderInfo") OrderInfo orderInfo,
			BindingResult result) throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> hashMap = new HashMap<>();
		orderValidator.validateCreate(orderInfo, result);
		orderInfo.setUser(currentUser());
		if (!result.hasErrors() && orderService.createOrder(orderInfo, result)) {
			hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
			if (currentUser() != null)
				hashMap.put("url", request.getContextPath() + "/orders");
			else
				hashMap.put("url", request.getContextPath() + "/orders/" + orderInfo.getId());
		} else {
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			hashMap.put("errors", convertErrorsToMap(result.getFieldErrors()));
		}
		return toJson(hashMap);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		OrderInfo orderInfo = orderService.findById(id);
		ModelAndView model = new ModelAndView();
		if (isOwner(orderInfo)) {
			model.setViewName("order");
			model.addObject("orderProducts", orderService.getOrderProducts(id));
		} else if (isGuest(orderInfo))
			model.setViewName("orderGuest");
		else
			model.setViewName("404");

		model.addObject("order", orderInfo);
		return model;
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("editOrder");
		OrderInfo orderInfo = orderService.findById(id);
		if (orderValidator.validateEdit(orderInfo, currentUser())) {
			model.addObject("order", orderInfo);
			model.addObject("orderProducts", orderService.getOrderProducts(id));
		} else {
			model.setViewName("404");
		}

		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public @ResponseBody String update(@RequestBody String data, @PathVariable("id") Integer id,
			BindingResult result) throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			hashMap = toHashMap(data);
			List<HashMap<String, Object>> orderProducts = (List<HashMap<String, Object>>) hashMap
					.get("orderProducts");
			hashMap.clear();
			OrderInfo orderInfo = orderService.findById(id);
			if (isOwner(orderInfo)) {
				orderValidator.validateUpdate(orderInfo, currentUser(), orderProducts, result);
				if (!result.hasErrors()
						&& orderService.updateOrderProduct(orderInfo, orderProducts))
					hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
				else
					hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			} else
				return "404";
			return toJson(hashMap);
		} catch (Exception e) {
			logger.error(e);
			return "404";
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		try {
			OrderInfo orderInfo = orderService.findById(id);
			if (isOwner(orderInfo) && orderValidator.validateDelete(orderInfo, currentUser())
					&& orderService.delete(orderInfo))
				return "redirect:/orders";
			else
				return "404";
		} catch (Exception e) {
			logger.error(e);
			return "404";
		}
	}

	private boolean isOwner(OrderInfo orderInfo) {
		return currentUser() != null && orderInfo != null && orderInfo.getUser() != null
				&& orderInfo.getUser().getId() == currentUser().getId();
	}

	private boolean isGuest(OrderInfo orderInfo) {
		return currentUser() == null && orderInfo != null && orderInfo.getSessionId() != null
				&& currentSession().equals(orderInfo.getSessionId());
	}
}
