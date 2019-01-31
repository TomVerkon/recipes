package com.diligentgroup.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.services.RecipeService;

@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String showRecipeById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeById(Long.decode(id)));
		return "/recipe/show";
	}

	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}
	
	@PostMapping
	@RequestMapping("/recipe")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/show/" + savedRecipeCommand.getId();
	}

}
