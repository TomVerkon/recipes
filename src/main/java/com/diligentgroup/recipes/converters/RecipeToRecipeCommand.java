package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.CategoryCommand;
import com.diligentgroup.recipes.command.NoteCommand;
import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private IngredientToIngredientCommand ingredientConverter;
	private CategoryToCategoryCommand categoryConverter;
	private NoteToNoteCommand noteConverter;

	public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
			CategoryToCategoryCommand categoryConverter, NoteToNoteCommand noteConverter) {
		super();
		this.ingredientConverter = ingredientConverter;
		this.categoryConverter = categoryConverter;
		this.noteConverter = noteConverter;
	}

	/**
	 * Converts a recipe and all contained objects into their equivalent Command objects
	 * 
	 * @param source  a Recipe object, source can be null
	 * @return a RecipeCommand or null if source is null
	 * @see Recipe
	 * @see RecipeCommand
	 */
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

		recipeCommand.setNotes(noteConverter.convert(source.getNotes()));

		source.getCategories().forEach(category -> {
			recipeCommand.addCategory(categoryConverter.convert(category));
		});

		source.getIngredients().forEach(ingredient -> {
			recipeCommand.addIngredient(ingredientConverter.convert(ingredient));
		});

		recipeCommand.setNotes(NoteCommand.builder().id(source.getNotes().getId())
				.recipeNotes(source.getNotes().getRecipeNotes()).build());
		source.getCategories().forEach(category -> {
			recipeCommand.addCategory(
					CategoryCommand.builder().id(category.getId()).description(category.getDescription()).build());
		});
		return recipeCommand;
	}

}
