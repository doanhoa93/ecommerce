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
import com.framgia.constant.Status;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Suggest;
import com.framgia.validator.SuggestValidator;

@Controller("admin/suggest")
@RequestMapping(value = "admin/suggests")
public class SuggestsController extends AdminController {

	@Autowired
	private SuggestValidator suggestValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("adminSuggests");
		model.addObject("statuses", Status.statuses);
		model.addObject("suggests", suggestService.getObjects());
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		Suggest suggest = suggestService.findById(id);
		ModelAndView model = new ModelAndView("adminSuggest");
		if (suggest != null) {
			model.addObject("suggest", suggest);
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
			Suggest suggest = suggestService.findById(id);
			suggestValidator.validStatus((int) hashMap.get("status"), ModelToBean.toSuggestInfo(suggest), result);
			if (result.hasErrors()) {
				hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			} else {
				suggest.setStatus((int) hashMap.get("status"));
				suggestService.saveOrUpdate(suggest);
				hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
				hashMap.put("statuses", Status.statuses);
			}
			return toJson(hashMap);
		} catch (Exception e) {
			return "404";
		}
	}
}
