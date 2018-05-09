package com.framgia.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.framgia.model.Product;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
	Page<Product> findByNameOrInformation(String name, String information, Pageable pageable);
}
