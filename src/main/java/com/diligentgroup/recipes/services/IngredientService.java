package com.diligentgroup.recipes.services;

import com.diligentgroup.recipes.command.IngredientCommand;

public interface IngredientService {
	
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);

}
