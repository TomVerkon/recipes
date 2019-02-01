package com.diligentgroup.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	@RequestMapping("/recipe/{id}/show")
	public String showRecipeById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(Long.decode(id)));
		return "/recipe/show";
	}

	@GetMapping
	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "/recipe/recipeform";
	}
	
	@PostMapping
	@RequestMapping("/recipe")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show" ;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "/recipe/recipeform";
	}
	
	@RequestMapping("/recipe/{id}/delete")
	public String deleteRecipeById(@PathVariable String id) {
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}

}
