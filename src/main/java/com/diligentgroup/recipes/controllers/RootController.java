package com.diligentgroup.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diligentgroup.recipes.services.RecipeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RootController {

	private RecipeServiceImpl recipeService;

	public RootController(RecipeServiceImpl recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({ "", "/", "index", "/index.html" })
	public String getIndexPage(Model model) {
		log.debug("Enter getIndexPage()");
		model.addAttribute("recipes", recipeService.getAllRecipes());
		log.debug("Exiting getIndexPage()");
		return "index";
	}
}
