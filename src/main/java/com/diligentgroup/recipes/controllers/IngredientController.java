package com.diligentgroup.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.services.IngredientService;
import com.diligentgroup.recipes.services.RecipeService;
import com.diligentgroup.recipes.services.UnitOfMeasureService;

@Controller
public class IngredientController {

	private final RecipeService recipeService;

	private final IngredientService ingredientService;

	private final UnitOfMeasureService uomService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService uomService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}

	@GetMapping("/recipe/{id}/ingredients")
	public String listIngredients(@PathVariable String id, Model model) {
		RecipeCommand recipe = recipeService.findCommandById(id);
		model.addAttribute("recipe", recipe);
		return "/recipe/ingredient/list";
	}

	@GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
	public String getIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		IngredientCommand ingredient = ingredientService.findByRecipeIdAndIngredientId(recipeId, id);
		model.addAttribute("ingredient", ingredient);
		return "/recipe/ingredient/show";
	}

	@GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
	public String getIngredientForUpdate(@PathVariable String recipeId, @PathVariable String id, Model model) {
		IngredientCommand ingredient = ingredientService.findByRecipeIdAndIngredientId(recipeId, id);
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("uoms", uomService.getAllUnitOfMeasures());
		return "/recipe/ingredient/edit";
	}

	@PostMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdateIngredient(@PathVariable String recipeId,
			@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand savedIngredientCommand = ingredientService.saveIngredient(ingredientCommand);

		return "redirect:/recipe/" + recipeId + "/ingredient/" + savedIngredientCommand.getId() + "/show";

	}

	@GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {

		ingredientService.deleteIngredientByIdAndRecipeId(id, recipeId);

		return "redirect:/recipe/{recipeId}/ingredients";
	}

}
