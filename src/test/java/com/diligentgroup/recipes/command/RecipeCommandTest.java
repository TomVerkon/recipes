package com.diligentgroup.recipes.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.diligentgroup.recipes.domain.Difficulty;

public class RecipeCommandTest {

	RecipeCommand command;
	String description = "description";
	String directions = "directions";
	String source = "source";
	String url = "url";
	String id = String.valueOf(Long.MAX_VALUE);
	Integer prepTime = Integer.valueOf(20);
	Integer cookTime = Integer.valueOf(30);
	Integer servings = Integer.valueOf(4);
	Difficulty difficulty = Difficulty.HARD;
	String recipeNotes = "recipeNotes";
	BigDecimal amount = BigDecimal.TEN;

	@Before
	public void setUp() throws Exception {
		command = RecipeCommand.builder().id(id).description(description).difficulty(difficulty).directions(directions)
				.prepTime(prepTime).servings(servings).source(source).url(url).cookTime(cookTime).build();
		command.setNotes(NoteCommand.builder().id(id).recipeNotes(recipeNotes).build());
		command.addIngredient(IngredientCommand.builder().amount(amount).description(description).id(id)
				.uom(UnitOfMeasureCommand.builder().id(id).description(description).build()).build());
		command.addCategory(CategoryCommand.builder().description(description).id(id).build());
	}

	@Test
	public void testBuilderAndValues() {
		// builder is exercised in setup
		assertEquals(id, command.getId());
		assertEquals(description, command.getDescription());
		assertEquals(directions, command.getDirections());
		assertEquals(source, command.getSource());
		assertEquals(url, command.getUrl());
		assertEquals(prepTime, command.getPrepTime());
		assertEquals(cookTime, command.getCookTime());
		assertEquals(servings, command.getServings());
		assertEquals(difficulty, command.getDifficulty());
		assertNotNull(command.getNotes());
		assertEquals(id, command.getNotes().getId());
		assertEquals(recipeNotes, command.getNotes().getRecipeNotes());
		assertNotNull(command.getCategories());
		assertEquals(1, command.getCategories().size());
		command.getCategories().forEach(categoryCommand -> {
			assertEquals(id, categoryCommand.getId());
			assertEquals(description, categoryCommand.getDescription());
		});
		assertNotNull(command.getIngredients());
		assertEquals(1, command.getIngredients().size());
		command.getIngredients().forEach(ingredientCommand -> {
			assertEquals(amount, ingredientCommand.getAmount());
			assertEquals(id, ingredientCommand.getId());
			assertEquals(description, ingredientCommand.getDescription());
			assertNotNull(ingredientCommand.getUom());
			assertEquals(id, ingredientCommand.getUom().getId());
			assertEquals(description, ingredientCommand.getUom().getDescription());
		});
	}

	@Test
	public void testAllArgsConstructor() {
		RecipeCommand command = new RecipeCommand(id, description, prepTime, cookTime, servings, source, url,
				directions, difficulty);
		assertEquals(id, command.getId());
		assertEquals(description, command.getDescription());
		assertEquals(directions, command.getDirections());
		assertEquals(source, command.getSource());
		assertEquals(url, command.getUrl());
		assertEquals(prepTime, command.getPrepTime());
		assertEquals(cookTime, command.getCookTime());
		assertEquals(servings, command.getServings());
		assertEquals(difficulty, command.getDifficulty());
	}

	@Test
	public void testNoArgsConstructorAndSetters() {
		RecipeCommand command = new RecipeCommand();
		assertNull(command.getId());
		command.setDescription(description);
		assertEquals(description, command.getDescription());
		command.setId(id);
		assertEquals(id, command.getId());
		command.setDirections(directions);
		assertEquals(directions, command.getDirections());
		command.setSource(source);
		assertEquals(source, command.getSource());
		command.setUrl(url);
		assertEquals(url, command.getUrl());
		command.setPrepTime(prepTime);
		assertEquals(prepTime, command.getPrepTime());
		command.setCookTime(cookTime);
		assertEquals(cookTime, command.getCookTime());
		command.setServings(servings);
		assertEquals(servings, command.getServings());
		command.setDifficulty(difficulty);
		assertEquals(difficulty, command.getDifficulty());

	}

}
