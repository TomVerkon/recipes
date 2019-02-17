package com.diligentgroup.recipes.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class NoteCommandTest {

	NoteCommand command;
	String recipeNotes = "xyzzy";

	@Before
	public void setUp() throws Exception {
		command = NoteCommand.builder().id("1").recipeNotes(recipeNotes).build();
	}

	@Test
	public void testId() {
		assertEquals(String.valueOf("1"), command.getId());
	}

	@Test
	public void testDescription() {
		command.setRecipeNotes(recipeNotes);
		assertEquals(recipeNotes, command.getRecipeNotes());
	}

	@Test
	public void testConstructor() {
		NoteCommand localCom = new NoteCommand();
		assertNull(localCom.getId());
		localCom.setRecipeNotes(recipeNotes);
		assertEquals(recipeNotes, localCom.getRecipeNotes());
	}

}
