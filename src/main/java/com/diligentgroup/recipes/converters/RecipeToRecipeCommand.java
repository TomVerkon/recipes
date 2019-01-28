package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import com.diligentgroup.recipes.command.CategoryCommand;
import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.command.NoteCommand;
import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.command.UnitOfMeasureCommand;
import com.diligentgroup.recipes.domain.Recipe;

import lombok.Synchronized;

public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}
		RecipeCommand recipeCommand = RecipeCommand.builder().cookTime(source.getCookTime())
				.description(source.getDescription()).difficulty(source.getDifficulty())
				.directions(source.getDirections()).id(source.getId()).prepTime(source.getPrepTime())
				.servings(source.getServings()).source(source.getSource()).url(source.getUrl()).build();
		recipeCommand.setNotes(NoteCommand.builder().id(source.getNotes().getId())
				.recipeNotes(source.getNotes().getRecipeNotes()).build());
		source.getCategories().forEach(category -> {
			recipeCommand.addCategory(
					CategoryCommand.builder().id(category.getId()).description(category.getDescription()).build());
		});
		source.getIngredients().forEach(ingredient -> {
			UnitOfMeasureCommand uomCommand = UnitOfMeasureCommand.builder()
					.description(ingredient.getUom().getDescription()).id(ingredient.getUom().getId()).build();
			recipeCommand.addIngredient(IngredientCommand.builder().amount(ingredient.getAmount())
					.description(ingredient.getDescription()).id(ingredient.getId()).uom(uomCommand).build());
		});
		return recipeCommand;
	}

}
