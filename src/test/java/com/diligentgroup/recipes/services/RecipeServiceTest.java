package com.diligentgroup.recipes.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.repositories.RecipeRepository;

public class RecipeServiceTest {

	RecipeService recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getAllRecipes() {
		Set<Recipe> recipeData = new HashSet<>();
		recipeData.add(Recipe.builder().id(1L).build());

		when(recipeService.getAllRecipes()).thenReturn(recipeData);

		Set<Recipe> recipes = recipeService.getAllRecipes();
		assertEquals(1L, recipes.size());
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void getRecipeById() {
		Optional<Recipe> optionalRecipe = Optional.of(Recipe.builder().id(1L).build());
		
		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

		Recipe fetchedRecipe = recipeService.getRecipeById(1L);
		assertNotNull(fetchedRecipe);
		verify(recipeRepository, times(1)).findById(1L);
	}

}
