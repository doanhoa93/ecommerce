package com.framgia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.framgia.constant.Gender;
import com.framgia.constant.Rating;
import com.framgia.constant.Role;
import com.framgia.constant.Status;
import com.framgia.model.Cart;
import com.framgia.model.Category;
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
			addPromotions(session);
			addProducts(session);
			addImages(session);
			addCarts(session);
			addOrders(session);
			addNotifications(session);
			addSuggests(session);
			addRates(session);
			addChats(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addUsers(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from User").executeUpdate();
			session.createQuery("delete from Profile").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			Map<String, Object> map = upload(new File(System.getProperty("user.dir")
			    + "/src/main/webapp/assets/images/default/supervisor.png"));
			String avatar = (String) map.get("url");
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(bcrypt.encode("123456"));
			admin.setRole(Role.ADMIN);
			admin.setName("Admin");
			admin.setCreatedAt(new Date());
			admin.setAvatar(avatar);
			session.save(admin);
			Profile profileA = new Profile();
			profileA.setUser(admin);
			profileA.setAddress("Ha Noi");
			profileA.setGender(Gender.getInt(Gender.MALE));
			Calendar calendar = Calendar.getInstance();
			calendar.set(1995, 11, 05);
			profileA.setBirthday(calendar.getTime());
			session.save(profileA);

			map = upload(new File(System.getProperty("user.dir")
			    + "/src/main/webapp/assets/images/default/user.png"));
			avatar = (String) map.get("url");
			User user = new User();
			user.setEmail("tiennh1995@gmail.com");
			user.setPassword(bcrypt.encode("123456"));
			user.setRole(Role.USER);
			user.setName("Nguyen Huu Tien");
			user.setCreatedAt(new Date());
			user.setAvatar(avatar);
			session.save(user);
			Profile profile = new Profile();
			profile.setUser(user);
			profile.setAddress("Ha Noi");
			profile.setGender(Gender.getInt(Gender.MALE));
			profile.setBirthday(calendar.getTime());
			session.save(profile);

			for (int i = 3; i < 11; i++) {
				user = new User();
				user.setEmail("example-" + i + "@gmail.com");
				user.setPassword(bcrypt.encode("123456"));
				user.setRole(Role.USER);
				user.setName("Example-" + i);
				user.setCreatedAt(new Date());
				user.setAvatar(avatar);
				session.save(user);
				profile = new Profile();
				profile.setUser(user);
				profile.setAddress("Ha Noi");
				profile.setGender(Gender.getInt(Gender.MALE));
				profile.setBirthday(calendar.getTime());

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
			for (int i = 1; i <= 7; i++) {
				Category category = new Category();
				category.setId(i);
				category.setName("Category-" + i);
				category.setCreatedAt(new Date());
				session.save(category);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

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
				Calendar calendar = Calendar.getInstance();
				promotion.setStartDate(calendar.getTime());
				calendar.add(Calendar.MONTH, i);
				promotion.setEndDate(calendar.getTime());
				promotion.setSaleOf(i * 10);

				session.save(promotion);
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
			session.createQuery("delete from Recent").executeUpdate();
			session.createQuery("delete from Product").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<Category> categories = (List<Category>) session.createCriteria(Category.class)
			    .list();
			Map<String, Object> map = null;
			Random random = new Random();
			for (int i = 1; i < 52; i++) {
				Product product = new Product();
				product.setId(i);
				product.setCategory(categories.get(i % categories.size()));
				product.setName("Product-" + i);
				product.setInformation("This is information");
				product.setNumber(random.nextInt(10000) + 1);
				product.setPrice(new Float(random.nextInt(600)));
				if (random.nextInt(2) == 0)
					product.setPromotion(new Promotion(random.nextInt(9) + 1));

				product.setRating(new Float(random.nextInt(5) + 1));
				map = upload(new File(System.getProperty("user.dir")
				    + "/src/main/webapp/assets/images/sample/product" + ((i % 16) + 1) + ".jpg"));
				product.setAvatar((String) map.get("url"));
				product.setCreatedAt(new Date());
				session.save(product);

				Recent recent = new Recent();
				recent.setProduct(product);
				recent.setCreatedAt(new Date());
				session.save(recent);
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
			    .add(Restrictions.not(Restrictions.in("id", 1))).list();
			List<Product> products = (List<Product>) session.createCriteria(Product.class).list();
			int userSize = users.size();
			int productSize = products.size();
			for (int i = 1; i < 10; i++) {
				Cart cart = new Cart();
				cart.setId(i);
				int indexUser = i % userSize;
				int indexProduct = i % productSize;
				cart.setUser(users.get(indexUser));
				cart.setProduct(products.get(indexProduct));

				session.save(cart);
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
			session.createQuery("delete from OrderProduct").executeUpdate();
			session.createQuery("delete from Order").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<User> users = (List<User>) session.createCriteria(User.class)
			    .add(Restrictions.not(Restrictions.in("id", 1))).list();
			Random random = new Random();
			for (int i = 1; i < 10; i++) {
				Order order = new Order();
				order.setId(i);
				int index = i % users.size();
				order.setUser(users.get(index));
				order.setStatus(Status.getIntStatus(Status.WAITING));
				order.setCreatedAt(new Date());
				order.setPhoneNumber("+84123456789");
				order.setEmail(users.get(index).getEmail());
				order.setName(users.get(index).getName());
				order.setAddress("Ha Noi");

				session.save(order);
			}
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<Order> orders = (List<Order>) session.createCriteria(Order.class).list();
			List<Product> products = (List<Product>) session.createCriteria(Product.class).list();
			for (int i = 1; i < 10; i++) {
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setId(i);
				orderProduct.setProduct(products.get(i - 1));
				orderProduct.setQuantity(random.nextInt(10));
				orderProduct.setPrice(products.get(i - 1).getPrice());
				orderProduct.setOrder(orders.get(i - 1));
				orders.get(i - 1)
				    .setTotalPrice(orderProduct.getQuantity() * orderProduct.getPrice());

				session.save(orderProduct);
				session.save(orders.get(i - 1));
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
			List<Product> products = (List<Product>) session.createCriteria(Product.class).list();
			Map<String, Object> map = null;
			for (int i = 1; i < 10; i++) {
				Image image = new Image();
				image.setId(i);
				image.setProduct(products.get(i - 1));
				map = upload(new File(System.getProperty("user.dir")
				    + "/src/main/webapp/assets/images/sample/product" + i + ".jpg"));
				image.setImage((String) map.get("url"));

				session.save(image);
			}

			for (int i = 9; i >= 1; i--) {
				Image image = new Image();
				image.setId(i);
				image.setProduct(products.get(9 - i));
				map = upload(new File(System.getProperty("user.dir")
				    + "/src/main/webapp/assets/images/sample/product" + i + ".jpg"));
				image.setImage((String) map.get("url"));

				session.save(image);
			}

			for (int i = 1; i < 10; i++) {
				Image image = new Image();
				image.setId(i);
				image.setProduct(products.get(i - 1));
				map = upload(new File(System.getProperty("user.dir")
				    + "/src/main/webapp/assets/images/sample/product" + i + ".jpg"));
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
			    .add(Restrictions.not(Restrictions.in("id", 1))).list();
			List<Order> orders = (List<Order>) session.createCriteria(Order.class).list();
			for (int i = 1; i < 10; i++) {
				Notification notification = new Notification();
				notification.setId(i);
				notification.setUser(users.get(i - 1));
				notification.setOrder(orders.get(i - 1));
				notification.setContent("The Order (created at: " + orders.get(i - 1).getCreatedAt()
				    + ") was setted with waiting!");
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

	@SuppressWarnings("unchecked")
	public static void addSuggests(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Suggest").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			Map<String, Object> map = null;
			Random random = new Random();
			for (int i = 2; i < 10; i++) {
				Suggest suggest = new Suggest();
				suggest.setUser(new User(i));
				suggest.setPrice(new Float(random.nextInt(600)));
				map = upload(new File(System.getProperty("user.dir")
				    + "/src/main/webapp/assets/images/sample/product" + i + ".jpg"));
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

	@SuppressWarnings("unchecked")
	public static void addRates(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Rate").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			session.clear();
			List<Product> products = (List<Product>) session.createCriteria(Product.class).list();
			Random random = new Random();
			for (int i = 1; i < 10; i++) {
				Rate rate = new Rate();
				rate.setId(i);
				rate.setUser(new User(i + 1));
				rate.setProduct(products.get(i - 1));
				rate.setRating(random.nextInt(Rating.MAX) + 1);

				products.get(i - 1).setRating(rate.getRating());
				session.save(rate);
				session.save(products.get(i - 1));
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	public static void addChats(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Chat").executeUpdate();
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

			input = new FileInputStream(System.getProperty("user.dir")
			    + "/src/main/resources/cloudinary-config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Cloudinary(
		    ObjectUtils.asMap("cloud_name", prop.getProperty("cloudinary.cloud_name"), "api_key",
		        prop.getProperty("cloudinary.api_key"), "api_secret",
		        prop.getProperty("cloudinary.api_secret")));
	}
}
