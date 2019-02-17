package com.diligentgroup.recipes.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	RecipeController controller;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testShowRecipeById() throws Exception {
		when(recipeService.findById(anyString())).thenReturn(Recipe.builder().id("1").build());
		mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().name("/recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testNewRecipe() throws Exception {
		mockMvc.perform(get("/recipe/new")).andExpect(status().isOk()).andExpect(view().name("/recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testSaveOrUpdateRecipe() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId("1");
		when(recipeService.saveRecipeCommand(any())).thenReturn(command);
		mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "1")
				.param("description", "Some new Discription")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/1/show"));
	}

	@Test
	public void testUpdateRecipe() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId("1");
		when(recipeService.findCommandById(anyString())).thenReturn(command);
		mockMvc.perform(get("/recipe/1/update")).andExpect(status().isOk()).andExpect(view().name("/recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testDeleteRecipeById() throws Exception {
		mockMvc.perform(get("/recipe/1/delete")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}

}
