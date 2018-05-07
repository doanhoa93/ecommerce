package com.framgia.controller;

import java.io.IOException;
import java.util.HashMap;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.framgia.bean.OrderInfo;
import com.framgia.constant.Status;
import com.framgia.helper.CustomSession;
import com.framgia.helper.SendOrder;
import com.framgia.validator.OrderValidator;

@Controller
@RequestMapping(value = "orders")
public class OrdersController extends BaseController {

	@Autowired
	private OrderValidator orderValidator;

	@Autowired
	private SendOrder sendOrder;

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
		} else {
			if (StringUtils.isNotEmpty(entries) && !entries.equals("all")) {
				model.addObject("orders", orderService.getOrdersWithGuest(CustomSession.current(),
				    0, Integer.parseInt(entries), Order.desc("id")));
			} else {
				model.addObject("orders", orderService.getOrdersWithGuest(CustomSession.current(),
				    0, 0, Order.desc("id")));
			}
			model.addObject("ordersSize",
			    orderService.getOrdersWithGuest(CustomSession.current(), 0, 0, null).size());
		}

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("order") OrderInfo orderInfo, BindingResult result)
	    throws JsonParseException, JsonMappingException, IOException {
		ModelAndView model = new ModelAndView();
		orderInfo.setUser(currentUser());
		orderValidator.validateCreate(orderInfo, result);
		if (!result.hasErrors() && orderService.createOrder(orderInfo, result)) {
			model.setViewName("redirect");
			model.addObject("url", request.getContextPath() + "/orders/" + orderInfo.getId());
			orderInfo = orderService.findById(orderInfo.getId(), false);
			sendOrder.send("/topic/orders", orderInfo);
		} else {
			model.setViewName("inputError");
			model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
		}

		return model;
	}

	@SuppressWarnings("serial")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		OrderInfo orderInfo = orderService.findById(id, false);
		ModelAndView model = new ModelAndView("order");
		if (isOwner(orderInfo)) {
			model.addObject("order", orderInfo);
			model.addObject("orderProducts", orderService.getOrderProducts(id));
			model.addObject("statuses", new HashMap<String, String>() {
				{
					put("waiting", Status.WAITING);
					put("accept", Status.ACCEPT);
					put("reject", Status.REJECT);
					put("cancel", Status.CANCEL);
				}
			});
		} else
			model.setViewName("404");

		return model;
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("editOrder");
		OrderInfo orderInfo = orderService.findById(id, true);
		if (orderValidator.validateEdit(orderInfo, currentUser())) {
			model.addObject("order", orderInfo);
			model.addObject("orderProducts", orderService.getOrderProducts(id));
		} else {
			model.setViewName("404");
		}

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable("id") Integer id,
	    @ModelAttribute("order") OrderInfo orderInfo, BindingResult result)
	    throws JsonProcessingException {
		ModelAndView model = new ModelAndView();
		try {
			OrderInfo oldOrder = orderService.findById(id, true);
			orderValidator.validateUpdate(oldOrder, orderInfo, currentUser(), result);
			if (!result.hasErrors() && orderService.updateOrderProduct(orderInfo)) {
				model.setViewName("redirect");
				model.addObject("url", request.getContextPath() + "/orders/" + orderInfo.getId());
			} else {
				model.setViewName("inputError");
				model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
			}

			return model;
		} catch (Exception e) {
			logger.error(e);
			model.setViewName("404");
			return model;
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		try {
			OrderInfo orderInfo = orderService.findById(id, true);
			if (orderValidator.validateDelete(orderInfo, currentUser())
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
		if (orderInfo == null)
			return false;

		if (currentUser() != null && orderInfo.getUser() != null
		    && currentUser().getId() == orderInfo.getUser().getId())
			return true;

		if (currentUser() == null && orderInfo.getUser() == null
		    && orderInfo.getSessionId().equals(CustomSession.current()))
			return true;

		return false;
	}
}
