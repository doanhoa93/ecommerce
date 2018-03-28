package com.framgia.controller;

import java.util.Calendar;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framgia.constant.Paginate;

@Controller
@RequestMapping(value = "/")
public class PagesController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView("homePage");
		model.addObject("flash", redirect.getFlashAttributes().get("flash"));
		model.addObject("categories", categoryService.hotCategories(Paginate.HOT_CATEGORY_LIMIT));
		model.addObject("hotProducts", productService.hotProducts(Paginate.HOT_PRODUCT_LIMIT));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - Paginate.DATE_RECENT);
		model.addObject("recentProducts",
		    productService.recentProducts(calendar.getTime(), Paginate.RECENT_PRODUCT_LIMIT));
		model.addObject("recommendProducts",
		    productService.randomProducts(Paginate.RECOMMEND_PRODUCT_LIMIT));

		if (currentUser() != null) {
			model.addObject("chats", chatService.getChats(currentUser().getId(), 0,
			    Paginate.CHAT_LIMIT, Order.desc("createdAt")));
		}
		return model;
	}
}
