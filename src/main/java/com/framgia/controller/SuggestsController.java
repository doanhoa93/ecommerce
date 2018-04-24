package com.framgia.controller;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.SuggestInfo;
import com.framgia.constant.Status;
import com.framgia.validator.SuggestValidator;

@Controller
@RequestMapping(value = "suggests")
public class SuggestsController extends BaseController {

	@Autowired
	private SuggestValidator suggestValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("suggests");
		if (StringUtils.isNotEmpty(entries)) {
			if (entries.equals("all"))
				model.addObject("suggests",
				    suggestService.getSuggests(currentUser().getId(), 0, 0, Order.desc("id")));
			else
				model.addObject("suggests", suggestService.getSuggests(currentUser().getId(), 0,
				    Integer.parseInt(entries), Order.desc("id")));
		} else
			model.addObject("suggests",
			    suggestService.getSuggests(currentUser().getId(), 0, 0, Order.desc("id")));
		model.addObject("suggestsSize",
		    suggestService.getSuggests(currentUser().getId(), 0, 0, null).size());
		return model;
	}

	@RequestMapping(value = "new")
	public String getNew(ModelMap model) {
		model.addAttribute("suggest", new SuggestInfo());
		return "newSuggest";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("suggest") SuggestInfo suggestInfo,
	    BindingResult result) throws JsonProcessingException {
		ModelAndView model = new ModelAndView();
		suggestInfo.setUserId(currentUser().getId());
		suggestValidator.validate(suggestInfo, result);
		if (!result.hasErrors() && suggestService.createSuggest(suggestInfo)) {
			model.setViewName("redirect");
			model.addObject("url", request.getContextPath() + "/suggests");
		} else {
			model.setViewName("inputError");
			model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
		}

		return model;
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("editSuggest");
		SuggestInfo suggestInfo = suggestService.findById(id, true);
		if (suggestInfo != null && suggestInfo.getStatus().equals(Status.WAITING))
			model.addObject("suggest", suggestInfo);
		else
			model.setViewName("404");
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable("id") Integer id,
	    @ModelAttribute("suggest") SuggestInfo suggestInfo, BindingResult result)
	    throws JsonProcessingException {
		ModelAndView model = new ModelAndView();
		SuggestInfo oldSuggest = suggestService.findById(id, true);
		if (oldSuggest != null && oldSuggest.getStatus().equals(Status.WAITING)) {
			suggestInfo.setUserId(currentUser().getId());
			suggestValidator.validUpdate(oldSuggest, suggestInfo, result);
			if (!result.hasErrors() && suggestService.updateSuggest(oldSuggest, suggestInfo)) {
				model.setViewName("redirect");
				model.addObject("url", request.getContextPath() + "/suggests");
			} else {
				model.setViewName("inputError");
				model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
			}
		}

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("redirect:/suggests");
		SuggestInfo suggestInfo = suggestService.findById(id, true);
		if (suggestInfo != null)
			suggestService.delete(suggestInfo);
		else
			model.setViewName("404");
		return model;
	}
}
