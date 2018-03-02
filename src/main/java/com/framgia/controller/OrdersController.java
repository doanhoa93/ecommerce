package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.framgia.constant.Paginate;
import com.framgia.constant.Status;
import com.framgia.mailer.ApplicationMailer;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController extends BaseController {

	@Autowired
	private ApplicationMailer mailer;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView("orders");
		List<OrderInfo> orders = orderService.getOrders(currentUser().getId(), page, Paginate.ORDER_LIMIT,
		        Order.desc("id"));
		model.addObject("paginate", setPaginate(orders.size(), page, Paginate.ORDER_LIMIT));
		model.addObject("orders", orders);
		model.addObject("ordersSize", orderService.getOrders(currentUser().getId(), null, 0, null).size());
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody String data)
	        throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> hashMap = toHashMap(data);
		try {
			List<String> strCartIds = (List<String>) hashMap.get("cartIds");
			hashMap.clear();
			if (strCartIds != null && !strCartIds.isEmpty()) {
				List<Integer> cartIds = strCartIds.stream().map(Integer::parseInt).collect(Collectors.toList());
				OrderInfo order = orderService.createOrder(currentUser().getId(), cartIds);
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
			} else {
				hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
				HashMap<String, Object> error = new HashMap<>();
				error.put("error", messageSource.getMessage("order.no_select", null, Locale.US));
				hashMap.put("error", error);
			}
			return toJson(hashMap);
		} catch (Exception e) {
			logger.error(e);
			hashMap.clear();
			hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			HashMap<String, Object> error = new HashMap<>();
			error.put("error", messageSource.getMessage("system.error", null, Locale.US));
			hashMap.put("error", error);
			return toJson(hashMap);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("order");
		OrderInfo order = orderService.findById(id);
		if (order != null) {
			model.addObject("order", order);
			model.addObject("orderProducts", orderService.getOrderProducts(id));
		} else {
			model.setViewName("404");
		}
		return model;
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("editOrder");
		OrderInfo order = orderService.findById(id);
		if (order != null && (order.getStatus().equals(Status.WAITING) || order.getStatus().equals(Status.REJECT))) {
			model.addObject("order", order);
			model.addObject("orderProducts", orderService.getOrderProducts(id));
		} else {
			model.setViewName("404");
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public @ResponseBody String update(@RequestBody String data, @PathVariable("id") Integer id)
	        throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			hashMap = toHashMap(data);
			OrderInfo orderInfo = orderService.findById(id);
			List<HashMap<String, Object>> orderProducts = (List<HashMap<String, Object>>) hashMap.get("orderProducts");
			hashMap.clear();
			if (orderProducts.size() != 0) {
				if (currentUser().getId() == orderInfo.getUser().getId()) {
					orderService.updateOrderProduct(orderInfo, orderProducts);
					hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
				} else {
					hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
				}
			}
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
			if (orderInfo != null && orderInfo.getStatus().equals(Status.WAITING)
			        && currentUser().getId() == orderInfo.getUser().getId()) {
				orderService.delete(orderInfo);
			} else {
				return "404";
			}

			return "redirect:/orders";
		} catch (Exception e) {
			logger.error(e);
			return "404";
		}
	}
}
