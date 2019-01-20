package com.diligentgroup.recipes.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import com.diligentgroup.recipes.services.RecipeService;
import com.diligentgroup.recipes.services.RecipeServiceImpl;

public class RootControllerTest {

	RootController rootController;

	@Mock
	RecipeServiceImpl recipeService;

	@Mock
	Model model;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		rootController = new RootController(recipeService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// when(rootController.getIndexPage(model)).
		String returnStr = rootController.getIndexPage(model);
		assertEquals("index", returnStr);
		verify(recipeService, times(1)).getAllRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
	}

}
