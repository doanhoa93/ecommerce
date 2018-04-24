package com.framgia.controller.admin;

import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.SuggestInfo;
import com.framgia.constant.Paginate;
import com.framgia.validator.SuggestValidator;

@Controller("admin/suggest")
@RequestMapping(value = "admin/suggests")
public class SuggestsController extends AdminController {

	@Autowired
	private SuggestValidator suggestValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("adminSuggests");
		if (StringUtils.isNotEmpty(entries)) {
			if (entries.equals("all"))
				model.addObject("suggests", suggestService.getObjects());
			else
				model.addObject("suggests", suggestService.getSuggests(null, 0,
				    Integer.parseInt(entries), Order.desc("id")));
		} else
			model.addObject("suggests",
			    suggestService.getSuggests(null, 0, Paginate.ADMIN_OBJECT_LIMIT, Order.desc("id")));
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		SuggestInfo suggest = suggestService.findById(id);
		ModelAndView model = new ModelAndView("adminSuggest");
		if (suggest != null)
			model.addObject("suggest", suggest);
		else
			model.setViewName("admin404");
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public @ResponseBody String update(@RequestBody String data, @PathVariable("id") Integer id,
	    BindingResult result) throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			hashMap = toHashMap(data);
			SuggestInfo suggest = suggestService.findById(id);
			suggestValidator.validStatus((String) hashMap.get("status"), result);
			if (result.hasErrors()) {
				hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			} else {
				suggest.setStatus((String) hashMap.get("status"));
				suggestService.saveOrUpdate(suggest);
				hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
			}
			return toJson(hashMap);
		} catch (Exception e) {
			logger.error(e);
			return "admin404";
		}
	}
}
