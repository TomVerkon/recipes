package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

//Light weight unit test
public class NoteTest {

	Note object;
	String idValue = String.valueOf(Long.MAX_VALUE);
	String recipeNotes = "recipeNotes";

	@Before
	public void setUp() throws Exception {
		object = Note.builder().id(idValue).recipeNotes(recipeNotes).build();
	}

	@Test
	public void testBuilderAndGetters() {
		// builder was used in
		assertEquals(idValue, object.getId());
		assertEquals(recipeNotes, object.getRecipeNotes());
		assertNull(object.getRecipe());
	}

	@Test
	public void testConstructorsAndSetters() {
		Note localObject = new Note();
		assertNull(localObject.getId());
		localObject = new Note(idValue, recipeNotes);
		assertEquals(idValue, localObject.getId());
		assertEquals(recipeNotes, localObject.getRecipeNotes());
		assertNull(localObject.getRecipe());
	}

	@Test
	public void testIsNewAndEquals() {
		Note localObject = new Note();
		localObject.setRecipeNotes(recipeNotes);
		assert (localObject.isNew());
		assert (!object.isNew());
		assert (!localObject.equals(object));

		localObject.setId(idValue);
		assert (localObject.equals(object));
		localObject.setRecipe(Recipe.builder().id(idValue).build());
		// recipe not part of equals right now
		assert (localObject.equals(object));
	}

}
