package com.framgia.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Status;
import com.framgia.helper.CustomSession;
import com.framgia.service.ProductService;

@Component
public class OrderValidator implements Validator {

	@Autowired
	private ProductService productService;

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

	public void validateCreate(OrderInfo orderInfo, Errors errors) {
		if (orderInfo.getCartIds() == null || orderInfo.getCartIds().isEmpty())
			errors.rejectValue("cartIds", "order.carts.invalid");

		if (StringUtils.isEmpty(orderInfo.getPhoneNumber()))
			errors.rejectValue("phoneNumber", "order.phone_number.empty");

		if (StringUtils.isEmpty(orderInfo.getName()))
			errors.rejectValue("name", "order.name.empty");

		if (StringUtils.isEmpty(orderInfo.getEmail()))
			errors.rejectValue("email", "order.email.empty");
	}

	public boolean validateEdit(Object target, UserInfo userInfo) {
		OrderInfo orderInfo = (OrderInfo) target;

		if (!isOwner(orderInfo, userInfo))
			return false;

		if (!(orderInfo.getStatus().equals(Status.WAITING)
		    || orderInfo.getStatus().equals(Status.REJECT)))
			return false;

		return true;
	}

	public void validateUpdate(OrderInfo oldOrder, OrderInfo newOrder, UserInfo userInfo,
	    Errors errors) {
		if (oldOrder == null) {
			errors.rejectValue("order", "order.null");
			return;
		}

		List<OrderProductInfo> orderProductInfos = newOrder.getOrderProducts();
		if (orderProductInfos == null) {
			errors.rejectValue("orderProducts", "order.order_product.invalid");
			return;
		}

		for (OrderProductInfo orderProductInfo : orderProductInfos) {
			ProductInfo productInfo = productService.findById(orderProductInfo.getProductId());
			if (productInfo != null && orderProductInfo.getQuantity() > productInfo.getNumber()) {
				errors.rejectValue("orderProducts", "order.order_product.quantity.invalid");
				return;
			}
		}

		if (StringUtils.isEmpty(newOrder.getPhoneNumber()))
			errors.rejectValue("phoneNumber", "order.phone_number.empty");

		if (StringUtils.isEmpty(newOrder.getName()))
			errors.rejectValue("name", "order.name.empty");

		if (StringUtils.isEmpty(newOrder.getEmail()))
			errors.rejectValue("email", "order.email.empty");

		if (!isOwner(oldOrder, userInfo))
			errors.rejectValue("user", "system.permission.denied");

		if (!(oldOrder.getStatus().equals(Status.WAITING)
		    || oldOrder.getStatus().equals(Status.REJECT)))
			errors.rejectValue("status", "order.status.invalid");
	}

	public boolean validateDelete(Object target, UserInfo userInfo) {
		OrderInfo orderInfo = (OrderInfo) target;

		if (!isOwner(orderInfo, userInfo))
			return false;

		if (!orderInfo.getStatus().equals(Status.WAITING))
			return false;
		return true;
	}

	private boolean isOwner(OrderInfo orderInfo, UserInfo userInfo) {
		if (orderInfo == null)
			return false;

		if (userInfo != null && orderInfo.getUser() != null
		    && userInfo.getId() == orderInfo.getUser().getId())
			return true;

		if (userInfo == null && orderInfo.getUser() == null
		    && orderInfo.getSessionId().equals(CustomSession.current()))
			return true;

		return false;
	}
}
