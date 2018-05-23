package com.framgia.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.framgia.model.Category;

public interface CategoryRepository extends ElasticsearchRepository<Category, String> {
	Page<Category> findByName(String name, Pageable pageable);
}
