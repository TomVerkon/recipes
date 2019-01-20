package com.diligentgroup.recipes.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.services.RecipeServiceImpl;

public class RootControllerTest {

	RootController controller;

	@Mock
	RecipeServiceImpl recipeService;

	@Mock
	Model model;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new RootController(recipeService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	public void test() {

		// givin
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(Recipe.builder().id(1L).build());
		recipes.add(Recipe.builder().id(2L).build());
		when(recipeService.getAllRecipes()).thenReturn(recipes);

		@SuppressWarnings("unchecked")
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor
				.forClass(Set.class);
		String returnStr = controller.getIndexPage(model);
		assertEquals("index", returnStr);
		verify(recipeService, times(1)).getAllRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"),
				argumentCaptor.capture());
		assertEquals(2, argumentCaptor.getValue().size());

	}

}
