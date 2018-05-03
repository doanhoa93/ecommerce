package com.framgia.controller.admin;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.PromotionInfo;
import com.framgia.constant.Paginate;
import com.framgia.constant.SaleOf;
import com.framgia.validator.PromotionValidator;

@Controller("admin/promotion")
@RequestMapping(value = "admin/promotions")
public class PromotionsController extends AdminController {

	@Autowired
	private PromotionValidator promotionValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("adminPromotions");
		if (StringUtils.isNotEmpty(entries)) {
			if (entries.equals("all"))
				model.addObject("promotions",
				    promotionService.getPromotions(null, 0, Order.desc("id")));
			else
				model.addObject("promotions", promotionService.getPromotions(null,
				    Integer.parseInt(entries), Order.desc("id")));
		} else
			model.addObject("promotions", promotionService.getPromotions(null,
			    Paginate.ADMIN_OBJECT_LIMIT, Order.desc("id")));

		return model;
	}

	@SuppressWarnings("serial")
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView getNew() {
		ModelAndView model = new ModelAndView("adminNewPromotion");
		model.addObject("promotion", new PromotionInfo());
		model.addObject("saleOf", new HashMap<String, Integer>() {
			{
				put("min", SaleOf.min);
				put("max", SaleOf.max);
			}
		});
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("promotion") PromotionInfo promotionInfo,
	    BindingResult result) throws JsonProcessingException {
		ModelAndView model = new ModelAndView();
		promotionValidator.validate(promotionInfo, result);

		if (!result.hasErrors() && promotionService.saveOrUpdate(promotionInfo) != null) {
			model.setViewName("redirect");
			model.addObject("url", request.getContextPath() + "/admin/promotions");
		} else {
			model.setViewName("inputError");
			model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
		}

		return model;
	}

	@SuppressWarnings("serial")
	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("adminEditPromotion");
		PromotionInfo promotionInfo = promotionService.findById(id, true);
		if (promotionInfo != null) {
			model.addObject("promotion", promotionInfo);
			model.addObject("saleOf", new HashMap<String, Integer>() {
				{
					put("min", SaleOf.min);
					put("max", SaleOf.max);
				}
			});
		} else
			model.setViewName("admin404");

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable("id") Integer id,
	    @ModelAttribute("promotion") PromotionInfo promotionInfo, BindingResult result)
	    throws JsonProcessingException {
		PromotionInfo oldPromotion = promotionService.findById(id, true);
		ModelAndView model = new ModelAndView();
		if (oldPromotion != null) {
			promotionValidator.validateUpdate(oldPromotion, promotionInfo, result);

			if (!result.hasErrors() && promotionService.saveOrUpdate(promotionInfo) != null) {
				model.setViewName("redirect");
				model.addObject("url", request.getContextPath() + "/admin/promotions");
			} else {
				model.setViewName("inputError");
				model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
			}
		}

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView();
		PromotionInfo promotion = promotionService.findById(id, true);
		if (promotion != null) {
			promotionService.delete(promotion);
			model.setViewName("redirect:/admin/promotions");
		} else
			model.setViewName("admin404");

		return model;
	}
}
