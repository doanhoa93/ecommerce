package com.framgia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.bean.OrderInfo;
import com.framgia.constant.Status;

@Component
public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderInfo order = (OrderInfo) target;
		int status = Status.getIntStatus(order.getStatus());
		int begin = Status.getIntStatus(Status.WAITING);
		int end = Status.getIntStatus(Status.CANCEL);
		if (status < begin || status > end)
			errors.rejectValue("status", "order.status.inlavid");
	}

	public void validStatus(String newStatus, Object target, Errors errors) {
		OrderInfo orderTemp = new OrderInfo();
		orderTemp.setStatus(newStatus);
		validate(orderTemp, errors);
		if (errors.hasErrors())
			return;

		OrderInfo order = (OrderInfo) target;
		String status = order.getStatus();

		// Da accept, chi duoc phep len CANCEL.
		// Da cancel thi chi duoc phep ve WAITING
		// Da reject, thi chi duoc phep ve WAITING
		if ((status.equals(Status.ACCEPT) && !newStatus.equals(Status.CANCEL))
		        || (status.equals(Status.CANCEL) && !newStatus.equals(Status.WAITING))
		        || (status.equals(Status.REJECT) && !newStatus.equals(Status.WAITING)))
			errors.rejectValue("status", "order.status.inlavid");
	}
}
