package com.framgia.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Status;
import com.framgia.helper.CustomSession;
import com.framgia.helper.ModelToBean;
import com.framgia.helper.SendNotification;
import com.framgia.mailer.ApplicationMailer;
import com.framgia.model.Cart;
import com.framgia.model.Notification;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SendNotification sendNotification;

	@Autowired
	private ApplicationMailer mailer;

	@Override
	public OrderInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toOrderInfo(getOrderDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public OrderInfo findById(Serializable key) {
		try {
			return ModelToBean.toOrderInfo(getOrderDAO().findById(key));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public OrderInfo saveOrUpdate(OrderInfo entity) {
		try {
			Order order = getOrderDAO().saveOrUpdate(toOrder(entity));
			return ModelToBean.toOrderInfo(order);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(OrderInfo entity) {
		try {
			getOrderDAO().delete(toOrder(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<OrderInfo> getObjects() {
		try {
			return getOrderDAO().getObjects().stream().map(ModelToBean::toOrderInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getOrderDAO().getObjectsByIds(keys).stream().map(ModelToBean::toOrderInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo getUser(Integer orderId) {
		try {
			return ModelToBean.toUserInfo(getOrderDAO().getUser(orderId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderProductInfo> getOrderProducts(Integer orderId) {
		try {
			return getOrderDAO().getOrderProducts(orderId).stream()
			    .map(ModelToBean::toOrderProductInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getProducts(Integer orderId) {
		try {
			List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderId);
			return (List<ProductInfo>) orderProducts.stream().map(orderProduct -> {
				return ModelToBean.toProductInfo(orderProduct.getProduct());
			}).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderInfo> getOrders(Integer userId, int off, int limit,
	    org.hibernate.criterion.Order order) {
		try {
			return getOrderDAO().getOrders(userId, off, limit, order).stream().map(object -> {
				OrderInfo orderInfo = ModelToBean.toOrderInfo(object);
				orderInfo.setProductQuantity(getProductQuantity(object.getId()));
				return orderInfo;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderInfo> getNewOrders(Date date, int limit) {
		try {
			return getOrderDAO().getNewObjects(date, limit).stream().map(ModelToBean::toOrderInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getOrdersSizeWithStatus(String status) {
		try {
			return getOrderDAO().getOrdersSizeWithStatus(Status.getIntStatus(status));
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public double[] getSalesByDate(Date startDate, Date endDate) {
		try {
			return getOrderDAO().getSalesByDate(startDate, endDate);
		} catch (Exception e) {
			logger.error(e);
			return new double[2];
		}
	}

	@Override
	public List<OrderInfo> getOrdersWithGuest(String sessionId, int off, int limit,
	    org.hibernate.criterion.Order order) {
		try {
			return getOrderDAO().getOrdersWithGuest(sessionId, off, limit, order).stream()
			    .map(object -> {
				    OrderInfo orderInfo = ModelToBean.toOrderInfo(object);
				    orderInfo.setProductQuantity(getProductQuantity(object.getId()));
				    return orderInfo;
			    }).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getProductQuantity(Integer orderId) {
		try {
			List<OrderProductInfo> orderProducts = getOrderProducts(orderId);
			return orderProducts.stream().map(OrderProductInfo::getQuantity)
			    .mapToInt(Integer::intValue).sum();
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public boolean createOrder(OrderInfo orderInfo, BindingResult result) {
		List<Cart> carts = getCartDAO().getObjectsByIds(orderInfo.getCartIds());
		UserInfo userInfo = orderInfo.getUser();

		// Kiem tra so luong san pham con du de dap ung k?
		for (Cart cart : carts) {
			if (!checkQuantityProduct(cart)) {
				result.rejectValue("cartIds", "order.cartIds.invalid");
				return false;
			}
		}

		try {
			Order order = new Order();
			order.setStatus(Status.getIntStatus(Status.WAITING));
			if (userInfo != null)
				order.setUser(getUserDAO().findById(userInfo.getId()));
			else
				order.setSessionId(CustomSession.current());

			order.setPhoneNumber(orderInfo.getPhoneNumber());
			order.setName(orderInfo.getName());
			order.setEmail(orderInfo.getEmail());
			order.setAddress(orderInfo.getAddress());
			order.setCreatedAt(new Date());

			// Tinh tong tien cua don hang
			float totalPrice = 0;
			for (Cart cart : carts) {
				totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
			}

			order.setTotalPrice(totalPrice);
			order = getOrderDAO().saveOrUpdate(order);
			orderInfo.setId(order.getId());

			// Voi moi cart, tao mot order_product. Dong thoi xoa bo cart di
			for (Cart cart : carts) {
				// Tao orderProduct
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setOrder(order);
				orderProduct.setProduct(cart.getProduct());
				orderProduct.setPrice(cart.getProduct().getPrice());
				orderProduct.setQuantity(cart.getQuantity());
				orderProduct.setStatus(Status.getIntStatus(Status.WAITING));
				getOrderProductDAO().saveOrUpdate(orderProduct);

				// Xoa cart
				getCartDAO().delete(cart);
			}

			String email = order.getEmail();
			if (email == null)
				email = order.getUser().getEmail();
			mailer.sendMail(email, messageSource.getMessage("mail.title", null, Locale.US),
			    messageSource.getMessage("mail.content", null, Locale.US));

			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean acceptOrder(OrderInfo orderInfo) {
		try {
			boolean valid = true;
			Order order = getOrderDAO().findById(orderInfo.getId());
			List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderInfo.getId());
			for (OrderProduct orderProduct : orderProducts) {
				if (orderProduct.getProduct().getNumber() < orderProduct.getQuantity()) {
					orderProduct.setStatus(Status.getIntStatus(Status.REJECT));
					valid = false;
				} else {
					orderProduct.setStatus(Status.getIntStatus(Status.ACCEPT));
				}
				getOrderProductDAO().saveOrUpdate(orderProduct);
			}

			if (!valid) {
				order.setStatus(Status.getIntStatus(Status.REJECT));
			} else {
				for (OrderProduct orderProduct : orderProducts) {
					Product product = orderProduct.getProduct();
					product.setNumber(product.getNumber() - orderProduct.getQuantity());
					getProductDAO().saveOrUpdate(product);
				}
			}

			order.setStatus(Status.getIntStatus(orderInfo.getStatus()));
			getOrderDAO().saveOrUpdate(order);

			Object[] status = new Object[] { order.getCreatedAt().toString(),
			    Status.getStrStatus(order.getStatus()) };
			if (order.getUser() != null) {
				Notification notification = new Notification();
				notification.setUser(order.getUser());
				notification.setOrder(order);
				notification
				    .setContent(messageSource.getMessage("order.update.status", status, Locale.US));
				notification.setCreatedAt(new Date());
				getNotificationDAO().saveOrUpdate(notification);

				sendNotification.send(ModelToBean.toNotificationInfo(notification));
			}

			String email = order.getEmail();
			if (email == null)
				email = order.getUser().getEmail();
			mailer.sendMail(email, messageSource.getMessage("mail.title", null, Locale.US),
			    messageSource.getMessage("order.update.status", status, Locale.US));

			return valid;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean updateStatusOrder(OrderInfo orderInfo) {
		try {
			Order order = getOrderDAO().findById(orderInfo.getId());
			if (!orderInfo.getStatus().equals(Status.ACCEPT)) {
				List<
				    OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderInfo.getId());
				for (OrderProduct orderProduct : orderProducts) {
					orderProduct.setStatus(order.getStatus());
					getOrderProductDAO().saveOrUpdate(orderProduct);
				}

				order.setStatus(Status.getIntStatus(orderInfo.getStatus()));
				getOrderDAO().saveOrUpdate(order);

				Object[] status = new Object[] { order.getCreatedAt().toString(),
				    Status.getStrStatus(order.getStatus()) };
				if (order.getUser() != null) {
					Notification notification = new Notification();
					notification.setUser(order.getUser());
					notification.setOrder(order);
					notification.setContent(
					    messageSource.getMessage("order.update.status", status, Locale.US));
					notification.setCreatedAt(new Date());
					getNotificationDAO().saveOrUpdate(notification);

					sendNotification.send(ModelToBean.toNotificationInfo(notification));
				}

				String email = order.getEmail();
				if (email == null)
					email = order.getUser().getEmail();
				mailer.sendMail(email, messageSource.getMessage("mail.title", null, Locale.US),
				    messageSource.getMessage("order.update.status", status, Locale.US));
			}

			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean updateOrderProduct(OrderInfo orderInfo) {
		try {
			if (orderInfo.getStatus().equals(Status.WAITING)
			    || orderInfo.getStatus().equals(Status.REJECT)) {
				List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderInfo.getId())
				    .stream().filter(object -> (object != null)).collect(Collectors.toList());
				List<OrderProductInfo> orderProductInfos = orderInfo.getOrderProducts();
				for (OrderProductInfo orderProductInfo : orderProductInfos) {
					OrderProduct orderProduct = getOrderProductDAO()
					    .findById(orderProductInfo.getId());
					orderProduct.setQuantity(orderProductInfo.getQuantity());
					getOrderProductDAO().saveOrUpdate(orderProduct);
					orderProducts.remove(findOrderProduct(orderProductInfo.getId(), orderProducts));
				}

				for (OrderProduct orderProduct : orderProducts)
					getOrderProductDAO().delete(orderProduct);

				orderProducts = getOrderDAO().getOrderProducts(orderInfo.getId()).stream()
				    .filter(object -> (object != null)).collect(Collectors.toList());
				float totalPrice = 0;
				for (OrderProduct orderProduct : orderProducts) {
					totalPrice += orderProduct.getQuantity() * orderProduct.getProduct().getPrice();
				}
				Order order = getOrderDAO().findById(orderInfo.getId());
				order.setTotalPrice(totalPrice);
				getOrderDAO().saveOrUpdate(order);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw e;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private boolean checkQuantityProduct(Cart cart) {
		if (cart.getQuantity() <= cart.getProduct().getNumber())
			return true;
		return false;
	}

	private Order toOrder(OrderInfo orderInfo) {
		Order order = getOrderDAO().getFromSession(orderInfo.getId());
		if (order == null) {
			order = new Order();
			order.setId(orderInfo.getId());
			order.setUser(new User(orderInfo.getUserId()));
		}

		order.setStatus(Status.getIntStatus(orderInfo.getStatus()));
		order.setCreatedAt(orderInfo.getCreatedAt());
		order.setTotalPrice(orderInfo.getTotalPrice());
		order.setPhoneNumber(orderInfo.getPhoneNumber());
		order.setName(orderInfo.getName());
		order.setEmail(orderInfo.getEmail());
		order.setAddress(orderInfo.getAddress());
		return order;
	}

	private int findOrderProduct(Integer id, List<OrderProduct> orderProducts) {
		for (int i = 0; i < orderProducts.size(); i++)
			if (orderProducts.get(i).getId() == id)
				return i;

		return -1;
	}
}
