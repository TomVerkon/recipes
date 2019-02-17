package com.diligentgroup.recipes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.diligentgroup.recipes.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, String> {

}
