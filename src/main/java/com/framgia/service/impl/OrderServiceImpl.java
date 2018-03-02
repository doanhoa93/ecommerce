package com.framgia.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Status;
import com.framgia.helper.ModelToBean;
import com.framgia.helper.SendNotification;
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
			return getOrderDAO().getOrderProducts(orderId).stream().map(ModelToBean::toOrderProductInfo)
			        .collect(Collectors.toList());
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
	public List<OrderInfo> getObjects() {
		try {
			return getOrderDAO().getObjects().stream().map(ModelToBean::toOrderInfo).collect(Collectors.toList());
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
	public List<OrderInfo> getObjects(int off, int limit) {
		try {
			return getOrderDAO().getObjects(off, limit).stream().map(ModelToBean::toOrderInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderInfo> getOrders(Integer userId, String page, int limit, org.hibernate.criterion.Order order) {
		try {
			int off;
			if (StringUtils.isEmpty(page)) {
				off = 0;
			} else
				off = (Integer.parseInt(page) - 1) * limit;
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
	public int getProductQuantity(Integer orderId) {
		try {
			List<OrderProductInfo> orderProducts = getOrderProducts(orderId);
			return orderProducts.stream().map(OrderProductInfo::getQuantity).mapToInt(Integer::intValue).sum();
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public OrderInfo createOrder(Integer userId, List<Integer> cartIds) {
		HashMap<String, Object> hashMap = new HashMap<>();
		List<Cart> carts = getCartDAO().getObjectsByIds(cartIds);
		boolean error = false;

		// Kiem tra so luong san pham con du de dap ung k?
		for (Cart cart : carts) {
			if (!checkQuantityProduct(cart)) {
				error = true;
				hashMap.put(cart.getProduct().getId().toString(), cart.getProduct().getName()
				        + "'s quantiy is not enough! (" + cart.getProduct().getNumber() + ")");
			}
		}

		// Neu khong du thi return false, va tao message error de thong bao cho
		// client biet
		if (error) {
			request.setAttribute("error", hashMap);
			return null;
		}

		try {
			Order order = new Order();
			order.setStatus(Status.getIntStatus(Status.WAITING));
			order.setUser(getUserDAO().findById(userId));
			order.setCreatedAt(new Date());

			// Tinh tong tien cua don hang
			float totalPrice = 0;
			for (Cart cart : carts) {
				totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
			}

			order.setTotalPrice(totalPrice);
			order = getOrderDAO().saveOrUpdate(order);

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

			return ModelToBean.toOrderInfo(order);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			hashMap.put("order", "Error when try save order!");
			request.setAttribute("error", hashMap);
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

			Notification notification = new Notification();
			notification.setUser(order.getUser());
			notification.setOrder(order);
			notification.setContent(messageSource.getMessage("order.update.status",
			        new Object[] { order.getCreatedAt().toString(), Status.getStrStatus(order.getStatus()) },
			        Locale.US));
			notification.setCreatedAt(new Date());
			getNotificationDAO().saveOrUpdate(notification);

			sendNotification.send(ModelToBean.toNotificationInfo(notification));
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
				List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderInfo.getId());
				for (OrderProduct orderProduct : orderProducts) {
					orderProduct.setStatus(order.getStatus());
					getOrderProductDAO().saveOrUpdate(orderProduct);
				}

				order.setStatus(Status.getIntStatus(orderInfo.getStatus()));
				getOrderDAO().saveOrUpdate(order);

				Notification notification = new Notification();
				notification.setUser(order.getUser());
				notification.setOrder(order);
				notification.setContent(messageSource.getMessage("order.update.status",
				        new Object[] { order.getCreatedAt().toString(), Status.getStrStatus(order.getStatus()) },
				        Locale.US));
				notification.setCreatedAt(new Date());
				getNotificationDAO().saveOrUpdate(notification);

				sendNotification.send(ModelToBean.toNotificationInfo(notification));
			}

			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean updateOrderProduct(OrderInfo orderInfo, List<HashMap<String, Object>> orderProductMaps) {
		try {
			if (orderInfo.getStatus().equals(Status.WAITING) || orderInfo.getStatus().equals(Status.REJECT)) {
				List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderInfo.getId());
				for (HashMap<String, Object> orderProductMap : orderProductMaps) {
					Integer id = (Integer) orderProductMap.get("id");
					int quantity = Integer.parseInt((String) orderProductMap.get("quantity"));

					OrderProduct orderProduct = getOrderProductDAO().findById(id);
					orderProduct.setQuantity(quantity);
					getOrderProductDAO().saveOrUpdate(orderProduct);

					orderProducts.remove(findOrderProduct(id, orderProducts));
				}

				for (OrderProduct orderProduct : orderProducts)
					getOrderProductDAO().delete(orderProduct);

				return true;
			}
			return false;
		} catch (Exception e) {
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
		return order;
	}

	private int findOrderProduct(Integer id, List<OrderProduct> orderProducts) {
		for (int i = 0; i < orderProducts.size(); i++)
			if (orderProducts.get(i).getId() == id)
				return i;

		return -1;
	}
}
