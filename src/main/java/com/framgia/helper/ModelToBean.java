package com.framgia.helper;

import com.framgia.bean.OrderInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.SuggestInfo;
import com.framgia.model.Order;
import com.framgia.model.Product;
import com.framgia.model.Suggest;

public class ModelToBean {
	public static OrderInfo toOrderInfo(Order order) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(order.getId());
		orderInfo.setTotalPrice(order.getTotalPrice());
		orderInfo.setStatus(order.getStatus());
		orderInfo.setCreatedAt(order.getCreatedAt());
		return orderInfo;
	}

	public static ProductInfo toProductInfo(Product product) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setId(product.getId());
		productInfo.setAvatar(product.getAvatar());
		productInfo.setCategoryId(product.getCategory().getId());
		productInfo.setInformation(product.getInformation());
		productInfo.setIsPromotion(product.getIsPromotion());
		productInfo.setName(product.getName());
		productInfo.setNumber(product.getNumber());
		productInfo.setPrice(product.getPrice());
		productInfo.setPromotionId(product.getPromotionId());
		productInfo.setRating(product.getRating());
		productInfo.setSaleOf(product.getSaleOf());
		productInfo.setImages(product.getImages());
		return productInfo;
	}
	
	public static SuggestInfo toSuggestInfo(Suggest suggest) {
		SuggestInfo suggestInfo = new SuggestInfo();
		suggestInfo.setId(suggest.getId());
		suggestInfo.setAvatar(suggest.getAvatar());
		suggestInfo.setCategory(suggest.getCategory());
		suggestInfo.setName(suggest.getName());
		suggestInfo.setCreatedAt(suggest.getCreatedAt());
		suggestInfo.setPrice(suggest.getPrice());
		suggestInfo.setUserId(suggest.getUser().getId());
		suggestInfo.setInformation(suggest.getInformation());
		suggestInfo.setStatus(suggest.getStatus());
		return suggestInfo;
	}	
}
