package com.framgia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.framgia.constant.Gender;
import com.framgia.constant.Role;
import com.framgia.constant.Status;
import com.framgia.model.Cart;
import com.framgia.model.Category;
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

public class FakeData {
	public static void main(String[] args) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			destroy();
			addUsers(session);
			addCategories(session);
			addProducts(session);
			addImages(session);
			addRecents(session);
			addPromotions(session);
			addComments(session);
			addCarts(session);
			addOrders(session);
			addOrderProducts(session);
			addNotifications(session);
			addSuggests(session);
			addRates(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	@SuppressWarnings("deprecation")
	public static void addUsers(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from User").executeUpdate();
			session.createQuery("delete from Profile").executeUpdate();
			t.commit();

			t = session.beginTransaction();

			User admin = new User();
			admin.setId(1);
			admin.setEmail("admin@gmail.com");
			admin.setPassword(Encode.encode("123456"));
			admin.setRole(Role.Admin);
			admin.setName("Admin");
			session.save(admin);
			Profile profileA = new Profile();
			profileA.setId(2);
			profileA.setUser(admin);
			profileA.setAddress("Ha Noi");
			profileA.setGender(Gender.getInt(Gender.MALE));
			profileA.setBirthday(new Date(1995 - 1900, 11, 05));
			session.save(profileA);

			User user = new User();
			user.setId(2);
			user.setEmail("tiennh1995@gmail.com");
			user.setPassword(Encode.encode("123456"));
			user.setRole(Role.User);
			user.setName("Nguyen Huu Tien");
			session.save(user);
			Profile profile = new Profile();
			profile.setId(1);
			profile.setUser(user);
			profile.setAddress("Ha Noi");
			profile.setGender(Gender.getInt(Gender.MALE));
			profile.setBirthday(new Date(1995 - 1900, 11, 05));
			session.save(profile);

			for (int i = 3; i < 11; i++) {
				user = new User();
				user.setId(i);
				user.setEmail("example-" + i + "@gmail.com");
				user.setPassword(Encode.encode("123456"));
				user.setRole(Role.User);
				user.setName("Example-" + i);
				session.save(user);
				profile = new Profile();
				profile.setId(i);
				profile.setUser(user);
				profile.setAddress("Ha Noi");
				profile.setGender(Gender.getInt(Gender.MALE));
				profile.setBirthday(new Date(1995 - 1900, 11, 05));

				session.save(profile);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	public static void addCategories(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Category").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			for (int i = 1; i < 10; i++) {
				Category category = new Category();
				category.setId(i);
				category.setName("Category-" + i);

				session.save(category);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addProducts(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Product").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<Category> categories = (List<Category>) session.createCriteria(Category.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();
			Map<String, Object> map = null;
			for (int i = 1; i < 10; i++) {
				Product product = new Product();
				product.setId(i);
				product.setCategory(categories.get(i - 1));
				product.setName("Product-" + i);
				product.setInformation("This is information");
				product.setIsPromotion(false);
				product.setNumber(10);
				product.setPrice(new Float(100.0));
				product.setRating(new Float(4.0));
				map = upload(new File(
				        System.getProperty("user.dir") + "/src/main/webapp/assets/images/home/product" + i + ".jpg"));
				product.setAvatar((String) map.get("url"));

				session.save(product);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void addOrders(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Order").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<User> users = (List<User>) session.createCriteria(User.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10))))
			        .list();
			for (int i = 1; i < 10; i++) {
				Order order = new Order();
				order.setId(i);
				order.setUser(users.get(i - 1));
				order.setStatus(Status.getIntStatus(Status.WAITING));
				order.setTotalPrice(new Float(100));
				order.setCreatedAt(new Date());

				session.save(order);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void addCarts(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Cart").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<User> users = (List<User>) session.createCriteria(User.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10))))
			        .list();
			List<Product> products = (List<Product>) session.createCriteria(Product.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			for (int i = 1; i < 10; i++) {
				Cart cart = new Cart();
				cart.setId(i);
				cart.setUser(users.get(i - 1));
				cart.setProduct(products.get(i - 1));

				session.save(cart);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void addComments(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Comment").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<User> users = (List<User>) session.createCriteria(User.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10))))
			        .list();
			List<Product> products = (List<Product>) session.createCriteria(Product.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			for (int i = 1; i < 10; i++) {
				Comment comment = new Comment();
				comment.setId(i);
				comment.setUser(users.get(i - 1));
				comment.setProduct(products.get(i - 1));
				comment.setContent("This is content");

				session.save(comment);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void addOrderProducts(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from OrderProduct").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<Order> orders = (List<Order>) session.createCriteria(Order.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			List<Product> products = (List<Product>) session.createCriteria(Product.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			for (int i = 1; i < 10; i++) {
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setId(i);
				orderProduct.setProduct(products.get(i - 1));
				orderProduct.setQuantity(1);
				orderProduct.setPrice(products.get(i - 1).getPrice());
				orderProduct.setOrder(orders.get(i - 1));

				session.save(orderProduct);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void addImages(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Image").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<Product> products = (List<Product>) session.createCriteria(Product.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			Map<String, Object> map = null;
			for (int i = 1; i < 10; i++) {
				Image image = new Image();
				image.setId(i);
				image.setProduct(products.get(i - 1));
				map = upload(new File(
				        System.getProperty("user.dir") + "/src/main/webapp/assets/images/home/product" + i + ".jpg"));
				image.setImage((String) map.get("url"));

				session.save(image);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void addNotifications(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Notification").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<User> users = (List<User>) session.createCriteria(User.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10))))
			        .list();

			List<Order> orders = (List<Order>) session.createCriteria(Order.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			for (int i = 0; i < 9; i++) {
				Notification notification = new Notification();
				notification.setId(i + 1);
				notification.setUser(users.get(i));
				notification.setOrder(orders.get(i));
				notification.setContent("The Order (created at: " + orders.get(i).getCreatedAt() + ") was setted with accepted!");
				notification.setWatched(false);
				notification.setCreatedAt(new Date());

				session.save(notification);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void addRecents(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Recent").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<Product> products = (List<Product>) session.createCriteria(Product.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			for (int i = 1; i < 10; i++) {
				Recent recent = new Recent();
				recent.setId(i);
				recent.setProduct(products.get(i - 1));
				recent.setViewed(10);

				session.save(recent);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings("deprecation")
	public static void addPromotions(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Promotion").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			for (int i = 1; i < 10; i++) {
				Promotion promotion = new Promotion();
				promotion.setId(i);
				promotion.setStartDate(new Date(2018 - 1900, 01, 01));
				promotion.setEndDate(new Date(2018 - 1900, 02, 01));

				session.save(promotion);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addSuggests(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Suggest").executeUpdate();
			t.commit();

			t = session.beginTransaction();

			Map<String, Object> map = null;
			for (int i = 1; i < 10; i++) {
				Suggest suggest = new Suggest();
				suggest.setId(i);
				suggest.setUser(new User(i + 1));
				suggest.setPrice(100);
				map = upload(new File(
				        System.getProperty("user.dir") + "/src/main/webapp/assets/images/home/product" + i + ".jpg"));
				suggest.setAvatar((String) map.get("url"));
				suggest.setCreatedAt(new Date());
				suggest.setCategory("Category-" + i);
				suggest.setInformation("This is information");
				suggest.setName("Suggest product " + i);
				session.save(suggest);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	public static void addRates(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Rate").executeUpdate();
			t.commit();

			t = session.beginTransaction();

			for (int i = 1; i < 10; i++) {
				Rate rate = new Rate();
				rate.setId(i);
				rate.setUser(new User(i + 1));
				rate.setProduct(new Product(i));
				rate.setRating(3);
				session.save(rate);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	@SuppressWarnings("rawtypes")
	public static Map upload(File file) throws IOException {
		Map uploadParams = ObjectUtils.asMap("invalidate", true);
		return getCloudinary().uploader().upload(file, uploadParams);
	}

	public static void destroy() throws Exception {
		getCloudinary().api().deleteAllResources(ObjectUtils.asMap("invalidate", true));
	}

	public static Cloudinary getCloudinary() {
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream(
			        System.getProperty("user.dir") + "/src/main/resources/cloudinary-config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Cloudinary(ObjectUtils.asMap("cloud_name", prop.getProperty("cloudinary.cloud_name"), "api_key",
		        prop.getProperty("cloudinary.api_key"), "api_secret", prop.getProperty("cloudinary.api_secret")));
	}
}
