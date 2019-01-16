package com.diligentgroup.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diligentgroup.recipes.services.RecipeService;

@Controller
public class RootController {
	
	private RecipeService recipeService;

	
	public RootController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}



	@RequestMapping({"", "/", "index", "/index.html"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getAllRecipes());
		return "index";
	}
}
