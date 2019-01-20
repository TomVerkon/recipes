package com.diligentgroup.recipes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}
	
	public Set<Recipe> getAllRecipes() {
		log.info("Entering getAllRecipes");
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes;
	}
	
	

}
