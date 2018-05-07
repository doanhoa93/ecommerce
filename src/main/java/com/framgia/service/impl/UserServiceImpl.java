package com.framgia.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.framgia.bean.CommentInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.ProfileInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Gender;
import com.framgia.constant.Provider;
import com.framgia.constant.Role;
import com.framgia.helper.CustomSession;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Order;
import com.framgia.model.Profile;
import com.framgia.model.User;
import com.framgia.security.CustomUserDetails;
import com.framgia.service.UserService;
import com.framgia.social.CustomUserSocial;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Override
	public UserInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toUserInfo(getUserDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo findByEmail(String email) {
		try {
			return findBy("email", email, true);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CustomUserDetails findByEmailWithSecurity(String email) {
		try {
			User user = getUserDAO().findByEmail(email);
			CustomUserDetails userDetail = new CustomUserDetails();
			userDetail.setId(user.getId());
			userDetail.setEmail(user.getEmail());
			userDetail.setPassword(user.getPassword());
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
			userDetail.setAuthorities(authorities);
			return userDetail;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo findById(Serializable key, boolean lock) {
		try {
			return findBy("id", key, lock);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo saveOrUpdate(UserInfo entity) {
		try {
			return ModelToBean.toUserInfo(getUserDAO().saveOrUpdate(toUser(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(UserInfo entity) {
		try {
			User user = getUserDAO().findById(entity.getId(), true);
			getUserDAO().delete(user);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<UserInfo> getObjects() {
		try {
			return getUserDAO().getObjects().stream().map(ModelToBean::toUserInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getUserDAO().getObjectsByIds(keys).stream().map(ModelToBean::toUserInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProfileInfo getProfile(Integer userId) {
		try {
			return ModelToBean.toProfileInfo(getUserDAO().getProfile(userId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CommentInfo> getComments(Integer userId) {
		try {
			return getUserDAO().getComments(userId).stream().map(ModelToBean::toCommentInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderProductInfo> getOrderProducts(Integer userId) {
		try {
			List<Order> orders = getOrderDAO().getOrders(userId, 0, 0, null);
			List<Integer> orderIds = (List<Integer>) orders.stream().map(Order::getId)
			    .collect(Collectors.toList());
			return getOrderProductDAO().getObjectsByIds(orderIds).stream()
			    .map(ModelToBean::toOrderProductInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getOrderedProducts(Integer userId) {
		try {
			return getOrderProducts(userId).stream().map(OrderProductInfo::getProduct)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getCartedProducts(Integer userId) {
		try {
			return getCartDAO().getCarts(userId, 0, 0, null).stream().map(cart -> {
				return ModelToBean.toProductInfo(cart.getProduct());
			}).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void logout(String email) {
		try {
			HashMap<String, Object> hashMap = new HashMap<>();
			UserInfo userInfo = findByEmail(email);
			hashMap.put("id", userInfo.getId());
			hashMap.put("token", userInfo.getToken());
			simpMessagingTemplate.convertAndSend("/topic/unregisters", hashMap);
			updateToken(userInfo, CustomSession.randomToken());
		} catch (Exception e) {
			logger.error(e);
			return;
		}
	}

	@Override
	public List<UserInfo> getUsers(String role) {
		try {
			return getUserDAO().getUsers(role).stream().map(ModelToBean::toUserInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getNewUsers(Date date, int limit) {
		try {
			return getUserDAO().getNewObjects(date, limit).stream().map(ModelToBean::toUserInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getUsers(int off, int limit, org.hibernate.criterion.Order order) {
		try {
			return getUserDAO().getUsers(off, limit, order).stream().map(ModelToBean::toUserInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<String> getTokens() {
		try {
			return getUserDAO().getTokens();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo findByToken(String token) {
		try {
			return findBy("token", token, true);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean createUser(UserInfo userInfo) {
		try {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			User user = toUser(userInfo);
			user.setPassword(bcrypt.encode(userInfo.getPassword()));
			if (StringUtils.isEmpty(userInfo.getAvatar()))
				user.setAvatar(request.getContextPath() + "/assets/images/default/user.png");
			else
				user.setAvatar(userInfo.getAvatar());
			getUserDAO().saveOrUpdate(user);

			ProfileInfo profileInfo = userInfo.getProfile();
			Profile profile = new Profile();
			profile.setUser(user);
			if (profileInfo != null)
				profile.setGender(Gender.getInt(profileInfo.getGender()));
			getProfileDAO().saveOrUpdate(profile);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean createUser(CustomUserSocial userSocial) {
		try {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			User user = getUserDAO().findByEmail(userSocial.getEmail());
			boolean result = false;
			if (user != null) {
				if (StringUtils.isNotEmpty(user.getProvider())
				    && user.getProvider().equals(Provider.FACEBOOK)) {
					user.setPassword(bcrypt.encode(userSocial.getPassword()));
					getUserDAO().saveOrUpdate(user);
					result = true;
				}
			} else {
				user = new User();
				user.setEmail(userSocial.getEmail());
				user.setName(userSocial.getName());
				user.setProvider(Provider.FACEBOOK);
				user.setRole(Role.USER);
				user.setCreatedAt(new Date());
				user.setPassword(bcrypt.encode(userSocial.getPassword()));
				user.setAvatar(userSocial.getPicture().getUrl());
				getUserDAO().saveOrUpdate(user);

				Profile profile = new Profile();
				profile.setUser(user);
				profile.setGender(Gender.getInt(userSocial.getGender().toUpperCase()));
				getProfileDAO().saveOrUpdate(profile);
				result = true;
			}
			return result;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean updateUser(UserInfo userInfo) {
		try {
			User user = getUserDAO().findById(userInfo.getId(), true);
			user.setName(userInfo.getName());
			if (StringUtils.isNotEmpty(userInfo.getNewPassword())) {
				BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
				user.setPassword(bcrypt.encode(userInfo.getNewPassword()));
			}

			Profile profile = user.getProfile();
			profile.setPhoneNumber(userInfo.getProfile().getPhoneNumber());
			profile.setGender(Gender.getInt(userInfo.getProfile().getGender()));
			profile.setBirthday(userInfo.getProfile().getBirthday());
			profile.setAddress(userInfo.getProfile().getAddress());
			getUserDAO().saveOrUpdate(user);
			getProfileDAO().saveOrUpdate(profile);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public UserInfo updateToken(UserInfo userInfo, String token) {
		try {
			User user = getUserDAO().findById(userInfo.getId(), true);
			user.setToken(token);
			getUserDAO().saveOrUpdate(user);
			userInfo.setToken(token);
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("id", user.getId());
			hashMap.put("token", user.getToken());
			simpMessagingTemplate.convertAndSend("/topic/registers", hashMap);
			return userInfo;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private User toUser(UserInfo userInfo) {
		User user = getUserDAO().getFromSession(userInfo.getId());
		if (user == null) {
			user = new User();
			user.setId(userInfo.getId());
			if (StringUtils.isEmpty(userInfo.getName()))
				return null;
		}

		user.setName(userInfo.getName());
		user.setEmail(userInfo.getEmail());
		user.setPassword(userInfo.getPassword());
		user.setRole(userInfo.getRole());
		user.setAvatar(userInfo.getAvatar());
		user.setCreatedAt(new Date());
		user.setToken(userInfo.getToken());
		return user;
	}
}
