package com.framgia.dao;

import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;

public interface OrderProductDAO {
  Order getOrder(Integer orderProductId);

  Product getProduct(Integer orderProductId);

  List<OrderProduct> getOrderProducts(List<Integer> orderIds);
}
