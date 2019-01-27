package com.diligentgroup.recipes.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class RecipeCommandTest {

	RecipeCommand command;
	String description = "xyzzy";

	@Before
	public void setUp() throws Exception {
		command = RecipeCommand.builder().id(1L).build();
	}

	@Test
	public void testId() {
		assertEquals(Long.valueOf(1L), command.getId());
	}

	@Test
	public void testDescription() {
		command.setDescription(description);
		assertEquals(description, command.getDescription());
	}

	@Test
	public void testConstructor() {
		RecipeCommand localCom = new RecipeCommand();
		assertNull(localCom.getId());
		localCom.setDescription(description);
		assertEquals(description, localCom.getDescription());
	}

	@Test
	public void testIngredient() {
		command.addIngredient(IngredientCommand.builder().id(1L).build());
		assertEquals(1, command.getIngredients().size());
	}

}
