package com.framgia.constant;

import org.apache.commons.lang3.StringUtils;

public class ProductFilter {
	private String name;
	private float priceLow;
	private float priceHigh;
	private boolean isFilterProduct;

	public ProductFilter(String name, String priceLow, String priceHigh) {
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
}
