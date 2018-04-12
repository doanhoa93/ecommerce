package com.framgia.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.RateInfo;
import com.framgia.validator.RateValidator;

@Controller
@RequestMapping(value = "rates")
public class RatesController extends BaseController {

	@Autowired
	private RateValidator rateValidator;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String create(@ModelAttribute("rate") RateInfo rateInfo,
	    BindingResult result) throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		rateInfo.setUserId(currentUser().getId());
		rateValidator.validate(rateInfo, result);
		if (!result.hasErrors() && rateService.saveOrUpdate(rateInfo) != null) {
			hashMap.put("result", "success");
		} else {
			hashMap.put("result", "fail");
			hashMap.put("error", convertErrorsToHashMap(result.getFieldErrors()));
		}

		return toJson(hashMap);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public @ResponseBody String update(@PathVariable("id") Integer id,
	    @ModelAttribute("rate") RateInfo rateInfo, BindingResult result)
	    throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		rateInfo.setUserId(currentUser().getId());
		RateInfo oldRate = rateService.findById(id, true);
		rateValidator.validateUpdate(oldRate, rateInfo, result);
		if (!result.hasErrors() && rateService.saveOrUpdate(rateInfo) != null) {
			hashMap.put("result", "success");
		} else {
			hashMap.put("result", "fail");
			hashMap.put("errors", convertErrorsToHashMap(result.getFieldErrors()));
		}

		return toJson(hashMap);
	}
}
