package com.diligentgroup.recipes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.converters.RecipeCommandToRecipe;
import com.diligentgroup.recipes.converters.RecipeToRecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;
	private RecipeCommandToRecipe recipeCommandToRecipe;
	private RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
	}

	public Set<Recipe> getAllRecipes() {
		log.debug("Entering getAllRecipes");
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes;
	}

	@Override
	public Recipe findById(Long id) {
		log.debug("Entering findById");
		return recipeRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
		Recipe recipe = recipeRepository.save(detachedRecipe);
		log.debug("saved recipe id: " + recipe.getId());
		return recipeToRecipeCommand.convert(recipe);
	}

	@Override
	@Transactional
	public RecipeCommand findCommandById(Long id) {
		return recipeToRecipeCommand.convert(findById(id));
	}

	@Override
	public void deleteById(Long id) {
		recipeRepository.deleteById(id);
	}
	
	

}
