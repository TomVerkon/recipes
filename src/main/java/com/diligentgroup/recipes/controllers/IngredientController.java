package com.diligentgroup.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.services.RecipeService;

@Controller
public class IngredientController {

	private final RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/ingredients")
	public String listIngredients(@PathVariable Long id, Model model) {
		RecipeCommand recipe = recipeService.findCommandById(id);
		model.addAttribute("recipe", recipe);
		return "/recipe/ingredient/list";
	}

}
