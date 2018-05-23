package com.framgia.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import com.framgia.model.Category;
import com.framgia.model.Product;
import com.framgia.util.HibernateUtil;

public class MakeDataEngine {

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) throws SchedulerException {
		new UpdateDataRedisTask().reloadData();

		try {
			Runtime.getRuntime().exec("/bin/bash -c ~/elasticsearch-1.5.2/bin/./elasticsearch");
			ApplicationContext context = new ClassPathXmlApplicationContext(
			    "/WEB-INF/configs/spring-elastic-search-config.xml");
			ElasticsearchTemplate elasticsearchTemplate = (ElasticsearchTemplate) context
			    .getBean("elasticsearchTemplate");
			elasticsearchTemplate.deleteIndex("products");
			elasticsearchTemplate.deleteIndex("categories");
			List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			List<Category> categories = (List<Category>) session.createCriteria(Category.class)
			    .list();
			List<Product> products = (List<Product>) session.createCriteria(Product.class).list();

			for (Category category : categories) {
				IndexQuery indexQuery = new IndexQueryBuilder()
				    .withId("category-" + category.getId()).withObject(category).build();
				indexQueries.add(indexQuery);
			}

			for (Product product : products) {
				IndexQuery indexQuery = new IndexQueryBuilder().withId("product-" + product.getId())
				    .withObject(product).build();
				indexQueries.add(indexQuery);
			}

			elasticsearchTemplate.bulkIndex(indexQueries);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}
