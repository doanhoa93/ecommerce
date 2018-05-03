package com.framgia.helper;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;

import com.framgia.constant.Price;

public class ProductFilter {
	private String name;
	private float priceLow;
	private float priceHigh;
	private boolean isFilterProduct;
	private HashMap<String, String> orders;
	final String[] orderTypes = { "asc", "desc" };

	public ProductFilter(String name, String priceLow, String priceHigh,
	    HashMap<String, String> orders) {
		if (StringUtils.isEmpty(name))
			this.name = "";
		else
			this.name = name;

		if (StringUtils.isEmpty(priceLow))
			this.priceLow = Price.MIN_PRICE;
		else
			this.priceLow = Float.parseFloat(priceLow);

		if (StringUtils.isEmpty(priceHigh))
			this.priceHigh = Price.MAX_PRICE;
		else
			this.priceHigh = Float.parseFloat(priceHigh);

		this.orders = orders;

		isFilterProduct = StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(priceLow)
		    || StringUtils.isNotEmpty(priceHigh);
	}

	public String getName() {
		return name;
	}

	public float getPriceLow() {
		return priceLow;
	}

	public float getPriceHigh() {
		return priceHigh;
	}

	public boolean isFilterProduct() {
		return isFilterProduct;
	}

	public HashMap<String, String> getOrders() {
		return orders;
	}

	public Order getOrder() {
		try {
			String orderAttr = orders.get("orderAttr");
			String orderType = orders.get("orderType");

			if (validOrderType(orderType))
				return (Order) Order.class.getMethod(orderType, String.class).invoke(null,
				    orderAttr);

			return null;
		} catch (Exception e) {
			return null;
		}
	}

	private boolean validOrderType(String orderType) {
		for (int i = 0; i < orderTypes.length; i++)
			if (orderTypes[i].equals(orderType))
				return true;

		return false;
	}
}
