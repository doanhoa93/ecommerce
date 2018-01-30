package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.framgia.bean.OrderInfo;
import com.framgia.constant.Status;
import com.framgia.helper.ModelToBean;
import com.framgia.mailer.ApplicationMailer;
import com.framgia.model.Order;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController extends BaseController {
	@Autowired
	private ApplicationMailer mailer;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("orders");
		List<Order> orders = userService.getOrders(currentUser().getId());
		List<OrderInfo> orderInfos = orders.stream().map(order -> {
			OrderInfo orderInfo = ModelToBean.toOrderInfo(order);
			orderInfo.setProductQuantity(orderService.getProductQuantity(order.getId()));
			return orderInfo;
		}).collect(Collectors.toList());
		model.addObject("orders", orderInfos);
		model.addObject("statuses", Status.statuses);
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody String data)
	        throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> hashMap = toHashMap(data);
		try {
			List<String> strCartIds = (List<String>) hashMap.get("cartIds");
			List<Integer> cartIds = strCartIds.stream().map(Integer::parseInt).collect(Collectors.toList());
			hashMap.clear();
			Order order = orderService.createOrder(currentUser().getId(), cartIds);
			if (order != null) {
				hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
				hashMap.put("url", "/orders");

				// Send email
				mailer.sendMail(currentUser().getEmail(), messageSource.getMessage("mail.title", null, Locale.US),
				        messageSource.getMessage("mail.content", null, Locale.US));
			} else {
				hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
				hashMap.put("error", request.getAttribute("error"));
			}
			return toJson(hashMap);
		} catch (Exception e) {
			hashMap.clear();
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			HashMap<String, Object> error = new HashMap<>();
			error.put("error", messageSource.getMessage("order.no_select", null, Locale.US));
			hashMap.put("error", error);
			return toJson(hashMap);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("order");
		if (orderService.findById(id) != null) {
			model.addObject("orderProducts", orderService.getOrderProducts(id));
			return model;
		} else {
			model.setViewName("redirect:/");
			return model;
		}
	}
}
