package com.diligentgroup.recipes.services;

import com.diligentgroup.recipes.command.IngredientCommand;

public interface IngredientService {

	public IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String id);

	public IngredientCommand saveIngredient(IngredientCommand ingredientCommand);

	public long deleteIngredientByIdAndRecipeId(String id, String recipeId);

}
