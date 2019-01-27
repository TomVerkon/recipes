package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class RecipeTest {

	Recipe object;
	Long idValue = Long.valueOf(Long.MAX_VALUE);
	Integer integerValue = Integer.valueOf(10);
	String description = "xyzzy";

	@Before
	public void setUp() throws Exception {
		object = Recipe.builder().id(idValue).description(description)
				.cookTime(integerValue).difficulty(Difficulty.HARD)
				.directions(description).prepTime(integerValue)
				.servings(integerValue).source(description).url(description)
				.build();
		object.setNotes(
				Note.builder().id(idValue).recipeNotes(description).build());
	}

	@Test
	public void testId() throws Exception {
		assertEquals(idValue, object.getId());
	}

	@Test
	public void testDescription() {
		assertEquals(description, object.getDescription());
	}

	@Test
	public void testIsNewAndEquals() {
		Recipe localObject = new Recipe();
		localObject.setDescription(description);
		localObject.setCookTime(integerValue);
		localObject.setDifficulty(Difficulty.HARD);
		localObject.setDirections(description);
		localObject.setPrepTime(integerValue);
		localObject.setServings(integerValue);
		localObject.setSource(description);
		localObject.setUrl(description);
		assert (localObject.isNew());
		assert (!object.isNew());
		assertNotEquals(localObject, object);
		localObject.setId(idValue);
		localObject.setNotes(
				Note.builder().id(idValue).recipeNotes(description).build());
		assertEquals(localObject, object);
	}

//	@Test
//	public void testUomAndAmount() {
//		
//		object.builder().cookTime(1).
//
//		Ingredient localObject = new Ingredient();
//		localObject.setId(idValue);
//		localObject.setDescription(description);
//		localObject.setUom(UnitOfMeasure.builder().id(idValue).build());
//		assertNotEquals(localObject, object);
//		
//		localObject.getUom().setDescription(description);
//		assertEquals(localObject, object);
//		
//		object.setAmount(BigDecimal.ONE);
//		assertNotEquals(localObject, object);
//		
//		localObject.setAmount(BigDecimal.ONE);;
//		assertEquals(localObject, object);
//
//	}
}
