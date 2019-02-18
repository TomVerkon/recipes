package com.diligentgroup.recipes.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.diligentgroup.recipes.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

	Optional<Category> findByDescription(String description);

}
