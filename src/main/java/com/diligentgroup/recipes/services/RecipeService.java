package com.diligentgroup.recipes.services;

import java.util.Set;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> getAllRecipes();

	public Recipe findById(String id);

	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

	public RecipeCommand findCommandById(String id);

	public void deleteById(String id);

}
