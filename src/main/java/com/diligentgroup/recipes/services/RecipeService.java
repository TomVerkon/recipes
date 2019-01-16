package com.diligentgroup.recipes.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.repositories.RecipeRepository;

@Service
public class RecipeService {
	
	private RecipeRepository recipeRepository;

	public RecipeService(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}
	
	public Iterable<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}
	
	

}
