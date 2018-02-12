package com.framgia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.constant.Status;
import com.framgia.model.Order;

@Component
public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Order order = (Order) target;
		int status = order.getStatus();
		if (status < Status.WAITING || status > Status.CANCEL)
			errors.rejectValue("status", "order.status.inlavid");
	}

	public void validStatus(int newStatus, Object target, Errors errors) {
		Order orderTemp = new Order();
		orderTemp.setStatus(newStatus);
		validate(orderTemp, errors);
		if (errors.hasErrors())
			return;

		Order order = (Order) target;
		int status = order.getStatus();

		// Da accept, chi duoc phep len CANCEL.
		// Da cancel thi chi duoc phep ve WAITING
		// Da reject, thi chi duoc phep ve WAITING
		if ((status == Status.ACCEPT && newStatus != Status.CANCEL)
		        || (status == Status.CANCEL && newStatus != Status.WAITING) 
		        || (status == Status.REJECT && newStatus != Status.WAITING))
			errors.rejectValue("status", "order.status.inlavid");
	}
}
