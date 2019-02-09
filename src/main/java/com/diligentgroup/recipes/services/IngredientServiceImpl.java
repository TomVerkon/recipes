package com.diligentgroup.recipes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.converters.IngredientToIngredientCommand;
import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private RecipeRepository recipeRepository;
	private IngredientToIngredientCommand ingredientToIngredientCommand;

	public IngredientServiceImpl(RecipeRepository recipeRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {

		Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
		if (null == recipe) {
			log.error("Recipe not found with id: " + recipeId);
			return null;
		}
		Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(id))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		if (optionalIngredientCommand.isPresent()) {
			return optionalIngredientCommand.get();
		} else {
			log.error("Ingredient not found with id: " + id);
			return null;
		}
	}

}
