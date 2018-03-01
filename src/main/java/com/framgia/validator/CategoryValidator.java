package com.framgia.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.bean.CategoryInfo;
import com.framgia.service.CategoryService;

@Component
public class CategoryValidator implements Validator {

	@Autowired
	private CategoryService categoryService;

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoryInfo categoryInfo = (CategoryInfo) target;
		if (categoryService.findBy("name", categoryInfo.getName(), true) != null)
			errors.rejectValue("name", "Duplicate name");

		if (categoryInfo.getId() == categoryInfo.getParentId())
			errors.rejectValue("id", "Category and Parent is same!");

		if (categoryInfo.getParentId() != null && categoryInfo.getParentId() == 0)
			if (categoryService.findBy("name", categoryInfo.getParentName(), true) != null)
				errors.rejectValue("ParentName", "Duplicate name");
	}

	public void validateUpdate(Object oldObject, Object newObject, Errors errors) {
		CategoryInfo oldCategoryInfo = (CategoryInfo) oldObject;
		CategoryInfo newCategoryInfo = (CategoryInfo) newObject;

		if (!oldCategoryInfo.getName().equals(newCategoryInfo.getName())
		        && (categoryService.findBy("name", newCategoryInfo.getName(), true) != null))
			errors.rejectValue("name", "Duplicate name");

		if (oldCategoryInfo.getId() == newCategoryInfo.getParentId())
			errors.rejectValue("id", "Category and Parent is same!");

		if (newCategoryInfo.getParentId() != null && newCategoryInfo.getParentId() == 0)
			if (categoryService.findBy("name", newCategoryInfo.getParentName(), true) != null)
				errors.rejectValue("ParentName", "Duplicate name");
	}
}
