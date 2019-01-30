package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private IngredientCommandToIngredient ingredientConverter;
	private CategoryCommandToCategory categoryConverter;
	private NoteCommandToNote noteConverter;

	public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter,
			CategoryCommandToCategory categoryConverter, NoteCommandToNote noteConverter) {
		super();
		this.ingredientConverter = ingredientConverter;
		this.categoryConverter = categoryConverter;
		this.noteConverter = noteConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}
		Recipe recipe = Recipe.builder().id(source.getId()).cookTime(source.getCookTime())
				.description(source.getDescription()).directions(source.getDirections()).prepTime(source.getPrepTime())
				.servings(source.getServings()).source(source.getSource()).url(source.getUrl())
				.difficulty(source.getDifficulty()).build();

		recipe.setNotes(noteConverter.convert(source.getNotes()));

		source.getCategories().forEach(categoryCommand -> {
			recipe.addCategory(categoryConverter.convert(categoryCommand));
		});

		source.getIngredients().forEach(ingredientCommand -> {
			recipe.addIngredient(ingredientConverter.convert(ingredientCommand));
		});
		return recipe;
	}

}
