package com.framgia.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.model.Cart;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Status;
import com.framgia.model.User;
import com.framgia.service.CartService;
import com.framgia.service.OrderProductService;
import com.framgia.service.OrderService;
import com.framgia.service.ProductService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {
	@Autowired
	CartService cartService;

	@Autowired
	OrderProductService orderProductService;

	@Autowired
	ProductService productService;

	@Override
	public User getUser(Integer orderId) {
		return getOrderDAO().getUser(orderId);
	}

	@Override
	public List<OrderProduct> getOrderProducts(Integer orderId) {
		return getOrderDAO().getOrderProducts(orderId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts(Integer orderId) {
		List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderId);
		return (List<Product>) orderProducts.stream().map(OrderProduct::getProduct);
	}

	@Override
	public Order findBy(String attribute, Serializable key, boolean lock) {
		return getOrderDAO().findBy(attribute, key, lock);
	}

	@Override
	public Order findById(Serializable key) {
		return getOrderDAO().findById(key);
	}

	@Override
	public boolean delete(Order entity) {
		try {
			getOrderDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Order entity) {
		try {
			getOrderDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Order> getObjects() {
		return getOrderDAO().getObjects();
	}

	@Override
	public List<Order> getObjectsByIds(List<Integer> keys) {
		return getOrderDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Order> getObjects(int limit) {
		return getOrderDAO().getObjects(limit);
	}

	@Override
	public boolean createOrder(Integer userId, List<Integer> cartIds) {
		HashMap<String, Object> hashMap = new HashMap<>();
		List<Cart> carts = cartService.getObjectsByIds(cartIds);
		boolean error = false;

		// Kiem tra so luong san pham con du de dap ung k?
		for (Cart cart : carts) {
			if (!checkQuantityProduct(cart)) {
				error = true;
				hashMap.put(cart.getProduct().getId().toString(), cart.getProduct().getName()
				        + "'s quantiy is not enough! (" + cart.getProduct().getNumber() + ")");
			}
		}

		// Neu khong du thi tao mot exception, de thong bao cho client biet
		if (error) {
			request.setAttribute("error", hashMap);
			return false;
		}

		try {
			Order order = new Order();
			order.setStatus(Status.WAITING);
			order.setUser(new User(userId));

			// Tinh tong tien cua don hang
			float totalPrice = 0;
			for (Cart cart : carts) {
				totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
			}

			order.setTotalPrice(totalPrice);
			saveOrUpdate(order);

			// Voi moi cart, tao mot order_product. Dong thoi xoa bo cart di
			for (Cart cart : carts) {
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setOrder(order);
				orderProduct.setProduct(cart.getProduct());
				orderProduct.setPrice(cart.getProduct().getPrice());
				orderProductService.saveOrUpdate(orderProduct);

				Product product = cart.getProduct();
				product.setNumber(product.getNumber() - cart.getQuantity());
				productService.saveOrUpdate(product);
				cartService.delete(cart);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			hashMap.put("order", "Error when try save order!");
			request.setAttribute("error", hashMap);
			throw e;
		}
	}

	private boolean checkQuantityProduct(Cart cart) {
		if (cart.getQuantity() <= cart.getProduct().getNumber())
			return true;
		return false;
	}
}
