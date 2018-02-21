package com.framgia.controller;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.SuggestInfo;
import com.framgia.constant.Paginate;
import com.framgia.validator.SuggestValidator;

@Controller
@RequestMapping(value = "suggests")
public class SuggestsController extends BaseController {

	@Autowired
	private SuggestValidator suggestValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView("suggests");
		List<SuggestInfo> suggests = suggestService.getSuggests(currentUser().getId(), page, Paginate.SUGGEST_LIMIT,
		        Order.desc("id"));
		model.addObject("paginate", setPaginate(suggests.size(), page, Paginate.SUGGEST_LIMIT));
		model.addObject("suggests", suggests);
		model.addObject("suggestsSize",
		        suggestService.getSuggests(currentUser().getId(), null, 0, Order.desc("id")).size());
		return model;
	}

	@RequestMapping(value = "new")
	public String getNew(ModelMap model) {
		model.addAttribute("suggestInfo", new SuggestInfo());
		return "suggestNew";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute("suggestInfo") SuggestInfo suggestInfo, BindingResult result) {
		try {
			suggestValidator.validate(suggestInfo, result);
			if (result.hasErrors())
				return "suggestNew";

			suggestInfo.setUserId(currentUser().getId());
			if (suggestService.saveOrUpdate(suggestInfo) != null) {
				return "redirect:/suggests";
			} else {
				return "suggestNew";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "suggestNew";
		}
	}
}
