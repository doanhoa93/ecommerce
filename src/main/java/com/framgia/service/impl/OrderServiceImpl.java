package com.framgia.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.bean.CartInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Status;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.User;
import com.framgia.service.CartService;
import com.framgia.service.OrderProductService;
import com.framgia.service.OrderService;
import com.framgia.service.ProductService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderProductService orderProductService;

	@Autowired
	private ProductService productService;

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
	public List<OrderInfo> getOrders(Integer userId, String page, int limit) {
		try {
			int off;
			if (StringUtils.isEmpty(page)) {
				off = 0;
			} else
				off = (Integer.parseInt(page) - 1) * limit;
			return getOrderDAO().getOrders(userId, off, limit).stream().map(order -> {
				OrderInfo orderInfo = ModelToBean.toOrderInfo(order);
				orderInfo.setProductQuantity(getProductQuantity(order.getId()));
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
		List<CartInfo> cartInfos = cartService.getObjectsByIds(cartIds);
		boolean error = false;

		// Kiem tra so luong san pham con du de dap ung k?
		for (CartInfo cartInfo : cartInfos) {
			if (!checkQuantityProduct(cartInfo)) {
				error = true;
				hashMap.put(cartInfo.getProduct().getId().toString(), cartInfo.getProduct().getName()
				        + "'s quantiy is not enough! (" + cartInfo.getProduct().getNumber() + ")");
			}
		}

		// Neu khong du thi return false, va tao message error de thong bao cho
		// client biet
		if (error) {
			request.setAttribute("error", hashMap);
			return null;
		}

		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setStatus(Status.WAITING);
			orderInfo.setUserId(userId);
			orderInfo.setCreatedAt(new Date());

			// Tinh tong tien cua don hang
			float totalPrice = 0;
			for (CartInfo cartInfo : cartInfos) {
				totalPrice += cartInfo.getQuantity() * cartInfo.getProduct().getPrice();
			}

			orderInfo.setTotalPrice(totalPrice);
			orderInfo = saveOrUpdate(orderInfo);

			// Voi moi cart, tao mot order_product. Dong thoi xoa bo cart di
			for (CartInfo cartInfo : cartInfos) {
				// Tao orderProduct
				OrderProductInfo orderProduct = new OrderProductInfo();
				orderProduct.setOrderId(orderInfo.getId());
				orderProduct.setProductId(cartInfo.getProduct().getId());
				orderProduct.setPrice(cartInfo.getProduct().getPrice());
				orderProduct.setQuantity(cartInfo.getQuantity());
				orderProduct.setStatus(Status.WAITING);
				orderProductService.saveOrUpdate(orderProduct);

				// Xoa cart
				cartService.delete(cartInfo);
			}

			return orderInfo;
		} catch (Exception e) {
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
			List<OrderProductInfo> orderProductInfos = orderInfo.getOrderProducts();
			for (OrderProductInfo orderProductInfo : orderProductInfos) {
				if (orderProductInfo.getProduct().getNumber() < orderProductInfo.getQuantity()) {
					orderProductInfo.setStatus(Status.REJECT);
					orderProductService.saveOrUpdate(orderProductInfo);
					valid = false;
				} else {
					orderProductInfo.setStatus(Status.ACCEPT);
					orderProductService.saveOrUpdate(orderProductInfo);
				}
			}

			if (!valid) {
				orderInfo.setStatus(Status.REJECT);
			} else {
				for (OrderProductInfo orderProductInfo : orderProductInfos) {
					ProductInfo productInfo = orderProductInfo.getProduct();
					productInfo.setNumber(productInfo.getNumber() - orderProductInfo.getQuantity());
					productService.saveOrUpdate(productInfo);
				}
			}

			saveOrUpdate(orderInfo);
			return valid;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean updateStatusOrder(OrderInfo orderInfo) {
		try {
			if (!orderInfo.getStatus().equals(Status.ACCEPT)) {
				List<OrderProductInfo> orderProductInfos = orderInfo.getOrderProducts();
				for (OrderProductInfo orderProductInfo : orderProductInfos) {
					orderProductInfo.setStatus(orderInfo.getStatus());
					orderProductService.saveOrUpdate(orderProductInfo);
				}
				saveOrUpdate(orderInfo);
			}

			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean updateOrderProduct(OrderInfo orderInfo, List<HashMap<String, Object>> orderProducts) {
		try {
			if (orderInfo.getStatus().equals(Status.WAITING) || orderInfo.getStatus().equals(Status.REJECT)) {
				List<OrderProductInfo> orderProductInfos = orderInfo.getOrderProducts();
				for (HashMap<String, Object> orderProduct : orderProducts) {
					Integer id = (Integer) orderProduct.get("id");
					int quantity = Integer.parseInt((String) orderProduct.get("quantity"));

					OrderProductInfo orderProductInfo = orderProductService.findById(id);
					orderProductInfo.setQuantity(quantity);
					orderProductService.saveOrUpdate(orderProductInfo);

					orderProductInfos.remove(findOrderProduct(id, orderProductInfos));
				}
				
				for (OrderProductInfo orderProductInfo : orderProductInfos)
					orderProductService.delete(orderProductInfo);
				
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private boolean checkQuantityProduct(CartInfo cart) {
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

	private int findOrderProduct(Integer id, List<OrderProductInfo> orderProductInfos) {
		for (int i = 0; i < orderProductInfos.size(); i++)
			if (orderProductInfos.get(i).getId() == id)
				return i;

		return -1;
	}
}
