package com.framgia.job;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.framgia.model.Category;
import com.framgia.model.Product;
import com.framgia.search.CategoryRepository;
import com.framgia.search.ProductRepository;
import com.framgia.util.HibernateUtil;

@Service("updateDataElastic")
public class UpdateDataElasticTask {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@SuppressWarnings("unchecked")
	public void reloadData() {
		elasticsearchTemplate.deleteIndex("products");
		elasticsearchTemplate.deleteIndex("categories");
		List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Product> products = (List<Product>) session.createCriteria(Product.class).list();
		List<Category> categories = (List<Category>) session.createCriteria(Category.class).list();

		for (Category category : categories) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId("category-" + category.getId())
			    .withObject(category).build();
			indexQueries.add(indexQuery);
		}

		for (Product product : products) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId("product-" + product.getId())
			    .withObject(product).build();
			indexQueries.add(indexQuery);
		}

		elasticsearchTemplate.bulkIndex(indexQueries);
	}

	public void insertData(Object object) {
		String id;
		if (object instanceof Product)
			id = "product-" + ((Product) object).getId();
		else
			id = "category-" + ((Category) object).getId();

		List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
		IndexQuery indexQuery = new IndexQueryBuilder().withId(id).withObject(object).build();
		indexQueries.add(indexQuery);
		elasticsearchTemplate.bulkIndex(indexQueries);
	}

	public void updateData(Object object) {
		String id;
		if (object instanceof Product) {
			id = "product-" + ((Product) object).getId();
			productRepository.delete(id);
		} else {
			id = "category-" + ((Category) object).getId();
			categoryRepository.delete(id);
		}

		List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
		IndexQuery indexQuery = new IndexQueryBuilder().withId(id).withObject(object).build();
		indexQueries.add(indexQuery);
		elasticsearchTemplate.bulkIndex(indexQueries);
	}

	public void deleteData(Object object) {
		String id;
		if (object instanceof Product) {
			id = "product-" + ((Product) object).getId();
			productRepository.delete(id);
		} else {
			id = "category-" + ((Category) object).getId();
			categoryRepository.delete(id);
		}
	}
}
