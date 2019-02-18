package com.diligentgroup.recipes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diligentgroup.recipes.domain.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

}
