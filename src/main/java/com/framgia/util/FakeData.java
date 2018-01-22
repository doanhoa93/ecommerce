package com.framgia.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Gender;
import com.framgia.model.Image;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Profile;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;
import com.framgia.model.Role;
import com.framgia.model.Status;
import com.framgia.model.User;

public class FakeData {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
		System.exit(0);
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
			for (int i = 1; i < 10; i++) {
				User user = new User();
				user.setId(i);
				user.setEmail("example-" + i + "@gmail.com");
				user.setPassword(Encode.encode("123456"));
				user.setRole(Role.User);
				user.setName("Example-" + i);
				session.save(user);
				Profile profile = new Profile();
				profile.setId(i);
				profile.setUser(user);
				profile.setAddress("Ha Noi");
				profile.setGender(Gender.MALE);
				profile.setBirthday(new Date(1995, 11, 05));

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

	public static void addProducts(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Product").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			@SuppressWarnings({ "unchecked" })
			List<Category> categories = (List<Category>) session.createCriteria(Category.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();
			for (int i = 1; i < 10; i++) {
				Product product = new Product();
				product.setId(i);
				product.setCategory(categories.get(i - 1));
				product.setName("Product-" + i);
				product.setInformation("This is information");
				product.setIsPromotion(false);
				product.setNumber(100);
				product.setPrice(new Float(100.0));
				product.setRating(new Float(4.0));
				product.setAvatar("assets/images/home/product" + i + ".jpg");

				session.save(product);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	public static void addOrders(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Order").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			@SuppressWarnings({ "unchecked" })
			List<User> users = (List<User>) session.createCriteria(User.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();
			for (int i = 1; i < 10; i++) {
				Order order = new Order();
				order.setId(i);
				order.setUser(users.get(i - 1));
				order.setStatus(Status.WAITING);
				order.setTotalPrice(new Float(100));

				session.save(order);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	public static void addCarts(Session session) {
		try {
			Transaction t = null;
			t = session.beginTransaction();
			session.createQuery("delete from Cart").executeUpdate();
			t.commit();

			t = session.beginTransaction();
			@SuppressWarnings({ "unchecked" })
			List<User> users = (List<User>) session.createCriteria(User.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			@SuppressWarnings({ "unchecked" })
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
			List<User> users = (List<User>) session.createCriteria(User.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
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
			List<Product> products = (List<Product>) session.createCriteria(Product.class)
			        .add(Restrictions.in("id", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))))
			        .list();

			for (int i = 1; i < 10; i++) {
				Image image = new Image();
				image.setId(i);
				image.setProduct(products.get(i - 1));
				image.setImage("assets/images/home/product" + i + ".jpg");

				session.save(image);
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
				promotion.setStartDate(new Date(2018, 01, 01));
				promotion.setEndDate(new Date(2018, 02, 01));

				session.save(promotion);
			}
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
}
