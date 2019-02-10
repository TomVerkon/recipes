package com.diligentgroup.recipes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.diligentgroup.recipes.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	public long deleteByIdAndRecipeId(Long id, Long recipeId);

}
