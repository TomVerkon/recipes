package com.diligentgroup.recipes.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.converters.IngredientCommandToIngredient;
import com.diligentgroup.recipes.converters.IngredientToIngredientCommand;
import com.diligentgroup.recipes.domain.Ingredient;
import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.repositories.IngredientRepository;
import com.diligentgroup.recipes.repositories.RecipeRepository;
import com.diligentgroup.recipes.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository uomRepository;
	private IngredientRepository ingredientRepository;
	private IngredientToIngredientCommand ingredientToIngredientCommand;
	private IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository,
			IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
		this.ingredientRepository = ingredientRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String id) {

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

	@Override
	public IngredientCommand saveIngredient(IngredientCommand ingredientCommand) {

		IngredientCommand localIngredientCommand = null;

		if (ingredientCommand.getRecipeId() == null) {
			log.error("Ingredients recipeId is null");
		} else {
			Recipe recipe = recipeRepository.findById(ingredientCommand.getRecipeId()).orElse(null);
			if (recipe == null) {
				log.error("Recipe with id: " + ingredientCommand.getRecipeId() + " not found!");
			} else {
				Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
						.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();

				if (ingredientOptional.isPresent()) {
					Ingredient ingredientFound = ingredientOptional.get();
					ingredientFound.setDescription(ingredientCommand.getDescription());
					ingredientFound.setAmount(ingredientCommand.getAmount());
					ingredientFound.setUom(uomRepository.findById(ingredientCommand.getUom().getId())
							.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); // todo address this
				} else {
					// add new Ingredient
					recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
				}
				Recipe savedRecipe = recipeRepository.save(recipe);

				// to do check for fail
				localIngredientCommand = ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
						.findFirst().get());

			}

		}
		return localIngredientCommand;
	}

	@Override
	@Transactional
	public long deleteIngredientByIdAndRecipeId(String id, String recipeId) {
		return ingredientRepository.deleteByIdAndRecipeId(id, recipeId);
	}

}
