package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

//Light weight unit test
public class NoteTest {

	Note object;
	Long idValue = 4L;
	String recipeNotes = "xyzzy";

	@Before
	public void setUp() throws Exception {
		object = Note.builder().id(idValue).recipeNotes(recipeNotes).build();
	}

	@Test
	public void testId() {
		assertEquals(idValue, object.getId());
	}

	@Test
	public void testDescription() {
		assertEquals(recipeNotes, object.getRecipeNotes());
	}

	@Test
	public void testIsNewAndEquals() {
		Note localObject = new Note();
		localObject.setRecipeNotes(recipeNotes);
		assert (localObject.isNew());
		assert (!object.isNew());
		assertNotEquals(localObject, object);
		localObject.setId(idValue);
		assertEquals(localObject, object);
	}

}
