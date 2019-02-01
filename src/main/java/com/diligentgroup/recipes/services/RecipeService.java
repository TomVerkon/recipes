package com.diligentgroup.recipes.services;

import java.util.Set;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> getAllRecipes();

	public Recipe findById(Long id);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	
	public RecipeCommand findCommandById(Long id);
	
	public void deleteById(Long id);

}
