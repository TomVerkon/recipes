package com.diligentgroup.recipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.diligentgroup.recipes.command.CategoryCommand;
import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.command.NoteCommand;
import com.diligentgroup.recipes.command.RecipeCommand;
import com.diligentgroup.recipes.command.UnitOfMeasureCommand;
import com.diligentgroup.recipes.domain.Category;
import com.diligentgroup.recipes.domain.Difficulty;
import com.diligentgroup.recipes.domain.Ingredient;
import com.diligentgroup.recipes.domain.Note;
import com.diligentgroup.recipes.domain.Recipe;

public class RecipeCommandToRecipeTest {

	RecipeCommandToRecipe converter;
	RecipeCommand source;
	String description = "description";

	@Before
	public void setUp() throws Exception {

		UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
		CategoryCommandToCategory categoryCommandToCategory = new CategoryCommandToCategory();
		NoteCommandToNote noteCommandToNote = new NoteCommandToNote();
		IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient(
				unitOfMeasureCommandToUnitOfMeasure);

		converter = new RecipeCommandToRecipe(ingredientCommandToIngredient, categoryCommandToCategory,
				noteCommandToNote);
		source = RecipeCommand.builder().description(description).cookTime(Integer.MAX_VALUE)
				.difficulty(Difficulty.EASY).directions("directions").id(Long.MAX_VALUE).prepTime(Integer.MAX_VALUE)
				.servings(Integer.MIN_VALUE).source("source").url("url").build();
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand(Long.MAX_VALUE, description);
		source.addCategory(CategoryCommand.builder().description(description).id(Long.MAX_VALUE).build());
		source.addIngredient(new IngredientCommand(Long.MAX_VALUE, description, BigDecimal.TEN, uomCommand));
		source.setNotes(NoteCommand.builder().id(Long.MIN_VALUE).recipeNotes("recipeNotes").build());
	}

	@Test
	public void testConvertNull() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert() {
		Recipe target = converter.convert(source);

		assert (target.getId().equals(source.getId()));
		assertEquals(target.getId(), source.getId());

		assert (target.getDescription().equals(source.getDescription()));
		assertEquals(target.getDescription(), source.getDescription());

		assert (target.getCookTime().equals(source.getCookTime()));
		assertEquals(target.getCookTime(), source.getCookTime());

		assert (target.getDifficulty().equals(source.getDifficulty()));
		assertEquals(target.getDifficulty(), source.getDifficulty());

		assert (target.getDirections().equals(source.getDirections()));
		assertEquals(target.getDirections(), source.getDirections());

		assert (target.getPrepTime().equals(source.getPrepTime()));
		assertEquals(target.getPrepTime(), source.getPrepTime());

		assert (target.getServings().equals(source.getServings()));
		assertEquals(target.getServings(), source.getServings());

		assert (target.getSource().equals(source.getSource()));
		assertEquals(target.getSource(), source.getSource());

		assert (target.getUrl().equals(source.getUrl()));
		assertEquals(target.getUrl(), source.getUrl());

		NoteCommand noteCommand = source.getNotes();
		Note note = target.getNotes();

		assert (note.getId().equals(noteCommand.getId()));
		assertEquals(note.getId(), noteCommand.getId());

		assert (note.getRecipeNotes().equals(noteCommand.getRecipeNotes()));
		assertEquals(note.getRecipeNotes(), noteCommand.getRecipeNotes());

		assert (source.getCategories().size() > 0);
		assertEquals(source.getCategories().size(), target.getCategories().size());

		CategoryCommand[] sourceCats = source.getCategories()
				.toArray(new CategoryCommand[source.getCategories().size()]);
		Category[] targetCats = target.getCategories().toArray(new Category[target.getCategories().size()]);

		for (int i = 0; i < targetCats.length; i++) {
			assert (sourceCats[i].getId().equals(targetCats[i].getId()));
			assertEquals(sourceCats[i].getId(), targetCats[i].getId());

			assert (sourceCats[i].getDescription().equals(targetCats[i].getDescription()));
			assertEquals(sourceCats[i].getDescription(), targetCats[i].getDescription());
		}

		assert (source.getIngredients().size() > 0);
		assertEquals(source.getIngredients().size(), target.getIngredients().size());

		IngredientCommand[] sourceIngredients = source.getIngredients()
				.toArray(new IngredientCommand[source.getIngredients().size()]);
		Ingredient[] targetIngredients = target.getIngredients()
				.toArray(new Ingredient[target.getIngredients().size()]);

		for (int i = 0; i < targetIngredients.length; i++) {
			assert (sourceIngredients[i].getId().equals(targetIngredients[i].getId()));
			assertEquals(sourceIngredients[i].getId(), targetIngredients[i].getId());

			assert (sourceIngredients[i].getDescription().equals(targetIngredients[i].getDescription()));
			assertEquals(sourceIngredients[i].getDescription(), targetIngredients[i].getDescription());

			assert (sourceIngredients[i].getAmount().equals(targetIngredients[i].getAmount()));
			assertEquals(sourceIngredients[i].getAmount(), targetIngredients[i].getAmount());

			assert (sourceIngredients[i].getUom().getId().equals(targetIngredients[i].getUom().getId()));
			assertEquals(sourceIngredients[i].getUom().getId(), targetIngredients[i].getUom().getId());

			assert (sourceIngredients[i].getUom().getDescription()
					.equals(targetIngredients[i].getUom().getDescription()));
			assertEquals(sourceIngredients[i].getUom().getDescription(),
					targetIngredients[i].getUom().getDescription());
		}
	}
}
