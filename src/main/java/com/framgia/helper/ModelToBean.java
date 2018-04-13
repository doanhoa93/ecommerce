package com.framgia.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.CartInfo;
import com.framgia.bean.CategoryInfo;
import com.framgia.bean.ChatInfo;
import com.framgia.bean.CommentInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.bean.NotificationInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.ProfileInfo;
import com.framgia.bean.PromotionInfo;
import com.framgia.bean.RateInfo;
import com.framgia.bean.RecentInfo;
import com.framgia.bean.SuggestInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Gender;
import com.framgia.constant.Status;
import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Chat;
import com.framgia.model.Comment;
import com.framgia.model.Image;
import com.framgia.model.Notification;
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
		return toCartInfoWithPro(cart);
	}

	// Category
	public static CategoryInfo toCategoryInfo(Category category) {
		CategoryInfo categoryInfo = toCategoryInfoWithPro(category);
		if (categoryInfo == null)
			return null;

		if (category.getProducts() != null)
			categoryInfo
			    .setProducts(category.getProducts().stream().filter(object -> (object != null))
			        .map(ModelToBean::toProductInfoWithPro).collect(Collectors.toList()));
		return categoryInfo;

	}

	// Chat
	public static ChatInfo toChatInfo(Chat chat) {
		return toChatInfoWithPro(chat);
	}

	// Comment
	public static CommentInfo toCommentInfo(Comment comment) {
		return toCommentInfoWithPro(comment);
	}

	// Image
	public static ImageInfo toImageInfo(Image image) {
		return toImageInfoWithPro(image);
	}

	// Notification
	public static NotificationInfo toNotificationInfo(Notification notification) {
		return toNotificationInfoWithPro(notification);
	}

	// Order
	public static OrderInfo toOrderInfo(Order order) {
		OrderInfo orderInfo = toOrderInfoWithPro(order);
		if (orderInfo == null)
			return null;

		if (order.getOrderProducts() != null)
			orderInfo.setOrderProducts(
			    order.getOrderProducts().stream().filter(object -> (object != null))
			        .map(ModelToBean::toOrderProductInfoWithPro).collect(Collectors.toList()));
		return orderInfo;
	}

	// OrderProduct
	public static OrderProductInfo toOrderProductInfo(OrderProduct orderProduct) {
		return toOrderProductInfoWithPro(orderProduct);
	}

	// Product
	public static ProductInfo toProductInfo(Product product) {
		ProductInfo productInfo = toProductInfoWithPro(product);
		if (productInfo == null)
			return null;

		if (product.getImages() != null)
			productInfo.setImages(product.getImages().stream().filter(object -> (object != null))
			    .map(ModelToBean::toImageInfoWithPro).collect(Collectors.toList()));

		if (product.getOrderProducts() != null)
			productInfo.setOrderProducts(
			    product.getOrderProducts().stream().filter(object -> (object != null))
			        .map(ModelToBean::toOrderProductInfoWithPro).collect(Collectors.toList()));
		return productInfo;
	}

	// Profile
	public static ProfileInfo toProfileInfo(Profile profile) {
		ProfileInfo profileInfo = toProfileInfoWithPro(profile);
		if (profileInfo == null)
			return null;

		if (profile.getUser() != null)
			profileInfo.setUser(toUserInfoWithPro(profile.getUser()));
		return profileInfo;
	}

	// Promotion
	public static PromotionInfo toPromotionInfo(Promotion promotion) {
		return toPromotionInfoWithPro(promotion);
	}

	// Rate
	public static RateInfo toRateInfo(Rate rate) {
		return toRateInfoWithPro(rate);
	}

	// Recent
	public static RecentInfo toRecentInfo(Recent recent) {
		return toRecentInfoWithPro(recent);
	}

	// Suggest
	public static SuggestInfo toSuggestInfo(Suggest suggest) {
		return toSuggestInfoWithPro(suggest);
	}

	// User
	public static UserInfo toUserInfo(User user) {
		UserInfo userInfo = toUserInfoWithPro(user);
		if (userInfo == null)
			return null;

		if (user.getProfile() != null)
			userInfo.setProfile(toProfileInfoWithPro(user.getProfile()));

		if (user.getCarts() != null)
			userInfo.setCarts(user.getCarts().stream().filter(object -> (object != null))
			    .map(ModelToBean::toCartInfoWithPro).collect(Collectors.toList()));

		if (user.getOrders() != null)
			userInfo.setOrders(user.getOrders().stream().filter(object -> (object != null))
			    .map(ModelToBean::toOrderInfoWithPro).collect(Collectors.toList()));

		if (user.getNotifications() != null) {
			userInfo.setNotifications(
			    user.getNotifications().stream().filter(object -> (object != null))
			        .map(ModelToBean::toNotificationInfoWithPro).collect(Collectors.toList()));
			int unwatched = userInfo.getNotifications().stream().map(notification -> {
				if (!notification.isWatched())
					return notification;
				return null;
			}).filter(object -> (object != null)).collect(Collectors.toList()).size();
			userInfo.setUnWatchedNotifications(unwatched);
		}
		return userInfo;
	}

	// ---------------- PRIVATE -------------------------------------------
	private static CartInfo toCartInfoWithPro(Cart cart) {
		if (cart == null)
			return null;

		CartInfo cartInfo = new CartInfo();
		cartInfo.setId(cart.getId());
		cartInfo.setSessionId(cart.getSessionId());
		cartInfo.setQuantity(cart.getQuantity());
		if (cart.getProduct() != null)
			cartInfo.setProduct(toProductInfoWithPro(cart.getProduct()));

		if (cart.getUser() != null)
			cartInfo.setUser(toUserInfoWithPro(cart.getUser()));
		return cartInfo;
	}

	private static CategoryInfo toCategoryInfoWithPro(Category category) {
		if (category == null)
			return null;

		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setId(category.getId());
		categoryInfo.setName(category.getName());
		categoryInfo.setParentId(category.getParentId());
		categoryInfo.setCreatedAt(category.getCreatedAt());
		return categoryInfo;
	}

	private static ChatInfo toChatInfoWithPro(Chat chat) {
		if (chat == null)
			return null;

		ChatInfo chatInfo = new ChatInfo();
		chatInfo.setId(chat.getId());
		chatInfo.setCreatedAt(chat.getCreatedAt());
		chatInfo.setContent(chat.getContent());
		chatInfo.setWatched(chat.isWatched());

		if (chat.getReceiver() != null)
			chatInfo.setReceiver(toUserInfoWithPro(chat.getReceiver()));

		if (chat.getSender() != null)
			chatInfo.setSender(toUserInfoWithPro(chat.getSender()));

		return chatInfo;
	}

	private static CommentInfo toCommentInfoWithPro(Comment comment) {
		if (comment == null)
			return null;

		CommentInfo commentInfo = new CommentInfo();
		commentInfo.setId(comment.getId());
		commentInfo.setContent(comment.getContent());
		if (comment.getUser() != null)
			commentInfo.setUser(toUserInfo(comment.getUser()));

		if (comment.getProduct() != null)
			commentInfo.setProduct(toProductInfoWithPro(comment.getProduct()));
		return commentInfo;
	}

	private static ImageInfo toImageInfoWithPro(Image image) {
		if (image == null)
			return null;

		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setId(image.getId());
		imageInfo.setImage(image.getImage());
		if (image.getProduct() != null)
			imageInfo.setProduct(toProductInfoWithPro(image.getProduct()));
		return imageInfo;
	}

	private static NotificationInfo toNotificationInfoWithPro(Notification notification) {
		if (notification == null)
			return null;

		NotificationInfo notificationInfo = new NotificationInfo();
		notificationInfo.setId(notification.getId());
		notificationInfo.setContent(notification.getContent());
		notificationInfo.setWatched(notification.isWatched());
		notificationInfo.setCreatedAt(notification.getCreatedAt());

		if (notification.getOrder() != null)
			notificationInfo.setOrder(toOrderInfoWithPro(notification.getOrder()));

		if (notification.getUser() != null)
			notificationInfo.setUser(toUserInfoWithPro(notification.getUser()));

		return notificationInfo;
	}

	private static ProductInfo toProductInfoWithPro(Product product) {
		if (product == null)
			return null;

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
		productInfo.setSaleOff(product.getSaleOff());
		productInfo.setCreatedAt(product.getCreatedAt());
		if (product.getCategory() != null)
			productInfo.setCategory(toCategoryInfoWithPro(product.getCategory()));
		return productInfo;
	}

	private static PromotionInfo toPromotionInfoWithPro(Promotion promotion) {
		if (promotion == null)
			return null;

		PromotionInfo promotionInfo = new PromotionInfo();
		promotionInfo.setId(promotion.getId());
		promotionInfo.setEndDate(promotion.getEndDate());
		promotionInfo.setStartDate(promotion.getStartDate());
		return promotionInfo;
	}

	private static OrderInfo toOrderInfoWithPro(Order order) {
		if (order == null)
			return null;

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(order.getId());
		orderInfo.setCreatedAt(order.getCreatedAt());
		orderInfo.setStatus(Status.getStrStatus(order.getStatus()));
		orderInfo.setTotalPrice(order.getTotalPrice());
		orderInfo.setAddress(order.getAddress());
		orderInfo.setEmail(order.getEmail());
		orderInfo.setName(order.getName());
		orderInfo.setPhoneNumber(order.getPhoneNumber());
		orderInfo.setSessionId(order.getSessionId());
		if (order.getUser() != null)
			orderInfo.setUser(toUserInfoWithPro(order.getUser()));
		return orderInfo;
	}

	private static OrderProductInfo toOrderProductInfoWithPro(OrderProduct orderProduct) {
		if (orderProduct == null)
			return null;

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

	private static ProfileInfo toProfileInfoWithPro(Profile profile) {
		if (profile == null)
			return null;

		ProfileInfo profileInfo = new ProfileInfo();
		profileInfo.setId(profile.getId());
		profileInfo.setPhoneNumber(profile.getPhoneNumber());
		profileInfo.setAddress(profile.getAddress());
		profileInfo.setBirthday(profile.getBirthday());
		profileInfo.setGender(Gender.getStr(profile.getGender()));
		return profileInfo;
	}

	private static RateInfo toRateInfoWithPro(Rate rate) {
		if (rate == null)
			return null;

		RateInfo rateInfo = new RateInfo();
		rateInfo.setId(rate.getId());
		rateInfo.setRating(rate.getRating());
		if (rate.getUser() != null)
			rateInfo.setUser(toUserInfoWithPro(rate.getUser()));

		if (rate.getProduct() != null)
			rateInfo.setProduct(toProductInfoWithPro(rate.getProduct()));
		return rateInfo;
	}

	private static RecentInfo toRecentInfoWithPro(Recent recent) {
		if (recent == null)
			return null;

		RecentInfo recentInfo = new RecentInfo();
		recentInfo.setId(recent.getId());
		recentInfo.setCreatedAt(recent.getCreatedAt());
		if (recent.getProduct() != null)
			recentInfo.setProduct(toProductInfoWithPro(recent.getProduct()));
		return recentInfo;
	}

	private static SuggestInfo toSuggestInfoWithPro(Suggest suggest) {
		if (suggest == null)
			return null;

		SuggestInfo suggestInfo = new SuggestInfo();
		suggestInfo.setId(suggest.getId());
		suggestInfo.setCategory(suggest.getCategory());
		suggestInfo.setAvatar(suggest.getAvatar());
		suggestInfo.setName(suggest.getName());
		suggestInfo.setCreatedAt(suggest.getCreatedAt());
		suggestInfo.setPrice(suggest.getPrice());
		suggestInfo.setInformation(suggest.getInformation());
		suggestInfo.setStatus(Status.getStrStatus(suggest.getStatus()));
		if (suggest.getUser() != null)
			suggestInfo.setUser(toUserInfoWithPro(suggest.getUser()));
		return suggestInfo;
	}

	private static UserInfo toUserInfoWithPro(User user) {
		if (user == null)
			return null;

		UserInfo userInfo = new UserInfo();
		userInfo.setId(user.getId());
		userInfo.setName(user.getName());
		userInfo.setEmail(user.getEmail());
		userInfo.setPassword(user.getPassword());
		userInfo.setRole(user.getRole());
		userInfo.setToken(user.getToken());
		userInfo.setAvatar(user.getAvatar());
		userInfo.setCreatedAt(user.getCreatedAt());
		List<Chat> chats = user.getChats();
		if (chats != null) {
			chats = chats.stream().filter(object -> (object != null)).collect(Collectors.toList());
			for (Chat chat : chats)
				if (!chat.isWatched()) {
					userInfo.setNewMessage(true);
					break;
				}
		}

		List<Chat> sendedChats = user.getSendedChats();
		if (sendedChats != null) {
			sendedChats = sendedChats.stream().filter(object -> (object != null))
			    .collect(Collectors.toList());
			for (Chat chat : sendedChats)
				if (!chat.isWatched()) {
					userInfo.setAdminNewMessage(true);
					break;
				}
		}
		return userInfo;
	}
}
