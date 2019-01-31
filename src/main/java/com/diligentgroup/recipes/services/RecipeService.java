package com.diligentgroup.recipes.services;

import java.util.Set;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> getAllRecipes();

	public Recipe getRecipeById(Long id);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

}
