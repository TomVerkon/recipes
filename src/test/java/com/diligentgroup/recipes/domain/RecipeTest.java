package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class RecipeTest {

	Recipe object;
	Long idValue = Long.valueOf(Long.MAX_VALUE);
	Integer cookTime = Integer.valueOf(10);
	Integer prepTime = Integer.valueOf(20);
	Integer servings = Integer.valueOf(4);
	String description = "description";
	String directions = "directions";
	String source = "source";
	String url = "www.google.com";
	String recipeNote = "recipeNote";
	BigDecimal amount = BigDecimal.TEN;

	@Before
	public void setUp() throws Exception {
		Note note = Note.builder().id(idValue).recipeNotes(recipeNote).build();
		Category category = Category.builder().id(idValue).description(description).build();
		UnitOfMeasure each = new UnitOfMeasure(idValue, "Each");
		Ingredient ingredient = Ingredient.builder().id(idValue).amount(amount).uom(each).description(description).build();
		object = Recipe.builder().id(idValue).description(description).cookTime(cookTime)
				.difficulty(Difficulty.HARD).directions(directions).prepTime(prepTime).servings(servings)
				.source(source).url(url).build();
		object.setNotes(note);
		object.addCategory(category);
		object.addIngredient(ingredient);
		
	}

	@Test
	public void testBuilderAndGetters() {
		// builder was used in setup
		assertEquals(idValue, object.getId());
		assertEquals(description, object.getDescription());
		assertEquals(cookTime, object.getCookTime());
		assertEquals(prepTime, object.getPrepTime());
		assertEquals(servings, object.getServings());
		assertEquals(directions, object.getDirections());
		assertEquals(source, object.getSource());
		assertEquals(url, object.getUrl());
		assertEquals(Difficulty.HARD, object.getDifficulty());
		assertEquals(1, object.getCategories().size());
		assertEquals(1, object.getIngredients().size());
		
	}

	@Test
	public void testConstructorsAndSetters() {
		Recipe localObject = new Recipe();
		assertNull(localObject.getId());
		localObject.setId(idValue);
		localObject.setDescription(description);
		localObject.setPrepTime(prepTime);
		localObject.setCookTime(cookTime);
		localObject.setServings(servings);
		localObject.setSource(source);
		localObject.setUrl(url);
		localObject.setDirections(directions);
		localObject.setDifficulty(Difficulty.HARD);
		Recipe localObject2 = new Recipe(idValue, description, prepTime, cookTime, servings, source, url, directions, Difficulty.HARD);
		assert(localObject.equals(localObject2));
		assertNull(localObject.getNotes());
		assert(localObject.getCategories().isEmpty());
		assert(localObject2.getCategories().isEmpty());
		assert(localObject.getIngredients().isEmpty());
	}

	@Test
	public void testIsNewAndEquals() {
		Recipe localObject = new Recipe(null, description, prepTime, cookTime, servings, source, url, directions, Difficulty.HARD);
		assert (localObject.isNew());
		assert (!object.isNew());
		assertNotEquals(localObject, object);
		localObject.setId(idValue);
		localObject.setNotes(Note.builder().id(idValue).recipeNotes(recipeNote).build());
		assertEquals(localObject, object);
	}

}
