package com.diligentgroup.recipes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.diligentgroup.recipes.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
