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
import com.diligentgroup.recipes.domain.Category;
import com.diligentgroup.recipes.domain.Difficulty;
import com.diligentgroup.recipes.domain.Ingredient;
import com.diligentgroup.recipes.domain.Note;
import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.domain.UnitOfMeasure;

public class RecipeToRecipeCommandTest {

	RecipeToRecipeCommand converter;
	Recipe source;
	String description = "description";

	@Before
	public void setUp() throws Exception {

		UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter = new UnitOfMeasureToUnitOfMeasureCommand();
		CategoryToCategoryCommand categoryConverter = new CategoryToCategoryCommand();
		NoteToNoteCommand noteConverter = new NoteToNoteCommand();
		IngredientToIngredientCommand ingredientConverter = new IngredientToIngredientCommand(unitOfMeasureConverter);

		converter = new RecipeToRecipeCommand(ingredientConverter, categoryConverter, noteConverter);
		source = Recipe.builder().description(description).cookTime(Integer.MAX_VALUE).difficulty(Difficulty.EASY)
				.directions("directions").id(String.valueOf(Long.MAX_VALUE)).prepTime(Integer.MAX_VALUE).servings(Integer.MIN_VALUE)
				.source("source").url("url").build();
		UnitOfMeasure uom = new UnitOfMeasure(String.valueOf(Long.MAX_VALUE), description);
		source.addCategory(Category.builder().description(description).id(String.valueOf(Long.MAX_VALUE)).build());
		source.addIngredient(new Ingredient(String.valueOf(Long.MAX_VALUE), description, BigDecimal.TEN, uom));
		source.setNotes(Note.builder().id(String.valueOf(Long.MIN_VALUE)).recipeNotes("recipeNotes").build());
	}

	@Test
	public void testConvertNull() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert() {
		RecipeCommand target = converter.convert(source);

		assert (source.getId().equals(target.getId()));
		assertEquals(source.getId(), target.getId());

		assert (source.getDescription().equals(target.getDescription()));
		assertEquals(source.getDescription(), target.getDescription());

		assert (source.getCookTime().equals(target.getCookTime()));
		assertEquals(source.getCookTime(), target.getCookTime());

		assert (source.getDifficulty().equals(target.getDifficulty()));
		assertEquals(source.getDifficulty(), target.getDifficulty());

		assert (source.getDirections().equals(target.getDirections()));
		assertEquals(source.getDirections(), target.getDirections());

		assert (source.getPrepTime().equals(target.getPrepTime()));
		assertEquals(source.getPrepTime(), target.getPrepTime());

		assert (source.getServings().equals(target.getServings()));
		assertEquals(source.getServings(), target.getServings());

		assert (source.getSource().equals(target.getSource()));
		assertEquals(source.getSource(), target.getSource());

		assert (source.getUrl().equals(target.getUrl()));
		assertEquals(source.getUrl(), target.getUrl());

		Note note = source.getNotes();
		NoteCommand noteCommand = target.getNotes();

		assert (note.getId().equals(noteCommand.getId()));
		assertEquals(note.getId(), noteCommand.getId());

		assert (note.getRecipeNotes().equals(noteCommand.getRecipeNotes()));
		assertEquals(note.getRecipeNotes(), noteCommand.getRecipeNotes());

		assert (source.getCategories().size() > 0);
		assertEquals(source.getCategories().size(), target.getCategories().size());

		Category[] sourceCats = source.getCategories().toArray(new Category[source.getCategories().size()]);
		CategoryCommand[] targetCats = target.getCategories()
				.toArray(new CategoryCommand[target.getCategories().size()]);

		for (int i = 0; i < targetCats.length; i++) {
			assert (sourceCats[i].getId().equals(targetCats[i].getId()));
			assertEquals(sourceCats[i].getId(), targetCats[i].getId());

			assert (sourceCats[i].getDescription().equals(targetCats[i].getDescription()));
			assertEquals(sourceCats[i].getDescription(), targetCats[i].getDescription());
		}

		assert (source.getIngredients().size() > 0);
		assertEquals(source.getIngredients().size(), target.getIngredients().size());

		Ingredient[] sourceIngredients = source.getIngredients()
				.toArray(new Ingredient[source.getIngredients().size()]);
		IngredientCommand[] targetIngredients = target.getIngredients()
				.toArray(new IngredientCommand[target.getIngredients().size()]);

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
