package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private IngredientCommandToIngredient ingredientCommandToIngredient;
	private CategoryCommandToCategory categoryCommandToCategory;
	private NoteCommandToNote noteCommandToNote;

	public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
			CategoryCommandToCategory categoryCommandToCategory, NoteCommandToNote noteCommandToNote) {
		super();
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.noteCommandToNote = noteCommandToNote;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		Recipe recipe = Recipe.builder().id(source.getId()).cookTime(source.getCookTime())
				.description(source.getDescription()).directions(source.getDirections()).prepTime(source.getPrepTime())
				.servings(source.getServings()).source(source.getSource()).url(source.getUrl())
				.difficulty(source.getDifficulty()).build();

		recipe.setNotes(noteCommandToNote.convert(source.getNotes()));

		source.getCategories().forEach(categoryCommand -> {
			recipe.addCategory(categoryCommandToCategory.convert(categoryCommand));
		});

		source.getIngredients().forEach(ingredientCommand -> {
			recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
		});
		return recipe;
	}

}
