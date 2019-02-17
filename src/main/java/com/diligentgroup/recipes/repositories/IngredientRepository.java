package com.diligentgroup.recipes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.diligentgroup.recipes.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

	public long deleteByIdAndRecipeId(String id, String recipeId);

}
