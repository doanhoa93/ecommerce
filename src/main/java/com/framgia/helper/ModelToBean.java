package com.framgia.helper;

import java.util.stream.Collectors;

import com.framgia.bean.CartInfo;
import com.framgia.bean.CategoryInfo;
import com.framgia.bean.CommentInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.ProfileInfo;
import com.framgia.bean.PromotionInfo;
import com.framgia.bean.RateInfo;
import com.framgia.bean.RecentInfo;
import com.framgia.bean.SuggestInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Status;
import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Image;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Profile;
import com.framgia.model.Promotion;
import com.framgia.model.Rate;
import com.framgia.model.Recent;
import com.framgia.model.Suggest;
import com.framgia.model.User;

public class ModelToBean {
	// Cart
	public static CartInfo toCartInfo(Cart cart) {
		CartInfo cartInfo = new CartInfo();
		cartInfo.setId(cart.getId());
		cartInfo.setQuantity(cart.getQuantity());
		cartInfo.setProduct(toProductInfoWithPro(cart.getProduct()));
		cartInfo.setUser(toUserInfoWithPro(cart.getUser()));
		return cartInfo;
	}

	// Category
	public static CategoryInfo toCategoryInfo(Category category) {
		CategoryInfo categoryInfo = toCategoryInfoWithPro(category);
		if (category.getProducts() != null)
			categoryInfo.setProducts(category.getProducts().stream().map(ModelToBean::toProductInfoWithPro)
			        .collect(Collectors.toList()));
		return categoryInfo;
	}

	// Comment
	public static CommentInfo toCommentInfo(Comment comment) {
		CommentInfo commentInfo = new CommentInfo();
		commentInfo.setId(comment.getId());
		commentInfo.setContent(comment.getContent());
		commentInfo.setUser(toUserInfo(comment.getUser()));
		commentInfo.setProduct(toProductInfoWithPro(comment.getProduct()));
		return commentInfo;
	}

	// Image
	public static ImageInfo toImageInfo(Image image) {
		return toImageInfoWithPro(image);
	}

	// Order
	public static OrderInfo toOrderInfo(Order order) {
		OrderInfo orderInfo = toOrderInfoWithPro(order);
		if (order.getOrderProducts() != null)
			orderInfo.setOrderProducts(order.getOrderProducts().stream().map(ModelToBean::toOrderProductInfoWithPro)
			        .collect(Collectors.toList()));
		return orderInfo;
	}

	// OrderProduct
	public static OrderProductInfo toOrderProductInfo(OrderProduct orderProduct) {
		return toOrderProductInfoWithPro(orderProduct);
	}

	// Product
	public static ProductInfo toProductInfo(Product product) {
		ProductInfo productInfo = toProductInfoWithPro(product);
		if (product.getImages() != null)
			productInfo.setImages(
			        product.getImages().stream().map(ModelToBean::toImageInfoWithPro).collect(Collectors.toList()));
		return productInfo;
	}

	// Profile
	public static ProfileInfo toProfileInfo(Profile profile) {
		ProfileInfo profileInfo = new ProfileInfo();
		profileInfo.setAddress(profile.getAddress());
		profileInfo.setBirthday(profile.getBirthday());
		profileInfo.setGender(profile.getGender());
		profileInfo.setId(profile.getId());
		profileInfo.setUser(toUserInfoWithPro(profile.getUser()));
		return profileInfo;
	}

	// Promotion
	public static PromotionInfo toPromotionInfo(Promotion promotion) {
		PromotionInfo promotionInfo = new PromotionInfo();
		promotionInfo.setId(promotion.getId());
		promotionInfo.setEndDate(promotion.getEndDate());
		promotionInfo.setStartDate(promotion.getStartDate());
		return promotionInfo;
	}

	// Rate
	public static RateInfo toRateInfo(Rate rate) {
		RateInfo rateInfo = new RateInfo();
		rateInfo.setId(rate.getId());
		rateInfo.setRating(rate.getRating());
		rateInfo.setUser(toUserInfoWithPro(rate.getUser()));
		rateInfo.setProduct(toProductInfoWithPro(rate.getProduct()));
		return rateInfo;
	}

	// Recent
	public static RecentInfo toRecentInfo(Recent recent) {
		RecentInfo recentInfo = new RecentInfo();
		recentInfo.setId(recent.getId());
		recentInfo.setViewed(recent.getViewed());
		recentInfo.setProduct(toProductInfoWithPro(recent.getProduct()));
		return recentInfo;
	}

	// Suggest
	public static SuggestInfo toSuggestInfo(Suggest suggest) {
		SuggestInfo suggestInfo = new SuggestInfo();
		suggestInfo.setId(suggest.getId());
		suggestInfo.setCategory(suggest.getCategory());
		suggestInfo.setAvatar(suggest.getAvatar());
		suggestInfo.setName(suggest.getName());
		suggestInfo.setCreatedAt(suggest.getCreatedAt());
		suggestInfo.setPrice(suggest.getPrice());
		suggestInfo.setInformation(suggest.getInformation());
		suggestInfo.setStatus(Status.getStrStatus(suggest.getStatus()));
		suggestInfo.setUser(toUserInfoWithPro(suggest.getUser()));
		return suggestInfo;
	}

	// User
	public static UserInfo toUserInfo(User user) {
		UserInfo userInfo = toUserInfoWithPro(user);
		return userInfo;
	}

	// ---------------- PRIVATE -------------------------------------------
	private static CategoryInfo toCategoryInfoWithPro(Category category) {
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setId(category.getId());
		categoryInfo.setName(category.getName());
		categoryInfo.setParentId(category.getParentId());
		return categoryInfo;
	}

	private static ImageInfo toImageInfoWithPro(Image image) {
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setId(image.getId());
		imageInfo.setImage(image.getImage());
		if (image.getProduct() != null)
			imageInfo.setProduct(toProductInfoWithPro(image.getProduct()));
		return imageInfo;
	}

	private static ProductInfo toProductInfoWithPro(Product product) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setId(product.getId());
		productInfo.setAvatar(product.getAvatar());
		productInfo.setInformation(product.getInformation());
		productInfo.setIsPromotion(product.getIsPromotion());
		productInfo.setName(product.getName());
		productInfo.setNumber(product.getNumber());
		productInfo.setPrice(product.getPrice());
		productInfo.setPromotionId(product.getPromotionId());
		productInfo.setRating(product.getRating());
		productInfo.setSaleOf(product.getSaleOf());
		if (product.getCategory() != null)
			productInfo.setCategory(toCategoryInfoWithPro(product.getCategory()));
		return productInfo;
	}

	private static OrderInfo toOrderInfoWithPro(Order order) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(order.getId());
		orderInfo.setCreatedAt(order.getCreatedAt());
		orderInfo.setStatus(Status.getStrStatus(order.getStatus()));
		orderInfo.setTotalPrice(order.getTotalPrice());
		if (order.getUser() != null)
			orderInfo.setUser(toUserInfoWithPro(order.getUser()));
		return orderInfo;
	}

	private static OrderProductInfo toOrderProductInfoWithPro(OrderProduct orderProduct) {
		OrderProductInfo orderProductInfo = new OrderProductInfo();
		orderProductInfo.setId(orderProduct.getId());
		orderProductInfo.setPrice(orderProduct.getPrice());
		orderProductInfo.setQuantity(orderProduct.getQuantity());
		orderProductInfo.setStatus(Status.getStrStatus(orderProduct.getStatus()));
		if (orderProduct.getOrder() != null)
			orderProductInfo.setOrder(toOrderInfoWithPro(orderProduct.getOrder()));

		if (orderProduct.getProduct() != null)
			orderProductInfo.setProduct(toProductInfoWithPro(orderProduct.getProduct()));
		return orderProductInfo;
	}

	private static UserInfo toUserInfoWithPro(User user) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(user.getId());
		userInfo.setName(user.getName());
		userInfo.setEmail(user.getEmail());
		userInfo.setPassword(user.getPassword());
		userInfo.setRole(user.getRole());
		userInfo.setAvatar(user.getAvatar());
		return userInfo;
	}
}