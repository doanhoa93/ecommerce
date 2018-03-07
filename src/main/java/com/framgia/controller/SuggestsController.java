package com.framgia.controller;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.SuggestInfo;
import com.framgia.constant.Paginate;
import com.framgia.constant.Status;
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
		model.addAttribute("suggest", new SuggestInfo());
		return "newSuggest";
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String create(@ModelAttribute("suggest") SuggestInfo suggestInfo, BindingResult result)
	        throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		suggestInfo.setUserId(currentUser().getId());
		suggestValidator.validate(suggestInfo, result);
		if (!result.hasErrors() && suggestService.createSuggest(suggestInfo))
			hashMap.put("url", request.getContextPath() + "/suggests");
		else
			hashMap.put("errors", convertErrorsToMap(result.getFieldErrors()));

		return toJson(hashMap);
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("editSuggest");
		SuggestInfo suggestInfo = suggestService.findById(id);
		if (suggestInfo != null && suggestInfo.getStatus().equals(Status.WAITING))
			model.addObject("suggest", suggestInfo);
		else
			model.setViewName("404");
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public @ResponseBody String update(@PathVariable("id") Integer id,
	        @ModelAttribute("suggest") SuggestInfo suggestInfo, BindingResult result) throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		SuggestInfo oldSuggest = suggestService.findById(id);
		if (oldSuggest != null && oldSuggest.getStatus().equals(Status.WAITING)) {
			suggestInfo.setUserId(currentUser().getId());
			suggestValidator.validUpdate(oldSuggest, suggestInfo, result);
			if (!result.hasErrors() && suggestService.updateSuggest(oldSuggest, suggestInfo))
				hashMap.put("url", request.getContextPath() + "/suggests");
			else
				hashMap.put("errors", convertErrorsToMap(result.getFieldErrors()));
		}
		return toJson(hashMap);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("redirect:/suggests");
		SuggestInfo suggestInfo = suggestService.findById(id);
		if (suggestInfo != null)
			suggestService.delete(suggestInfo);
		else
			model.setViewName("404");
		return model;
	}
}
