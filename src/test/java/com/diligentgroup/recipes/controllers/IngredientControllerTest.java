package com.diligentgroup.recipes.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.services.IngredientService;
import com.diligentgroup.recipes.services.RecipeService;

public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;

	@Mock
	IngredientService ingredientService;

	IngredientController controller;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(recipeService, ingredientService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void listIngredientTest() throws Exception {
		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		// when
		mockMvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk())
				.andExpect(view().name("/recipe/ingredient/list"))
				.andExpect(model().attribute("recipe", recipeCommand));

		// then
		verify(recipeService, times(1)).findCommandById(anyLong());
	}

	@Test
	public void findIngredientTest() throws Exception {

		// given
		IngredientCommand ingredientCommand = new IngredientCommand();

		// when
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

		// then
		mockMvc.perform(get("/recipe/1/ingredient/1/show")).andExpect(status().isOk())
				.andExpect(view().name("/recipe/ingredient/show"))
				.andExpect(model().attribute("ingredient", ingredientCommand));

	}
}
