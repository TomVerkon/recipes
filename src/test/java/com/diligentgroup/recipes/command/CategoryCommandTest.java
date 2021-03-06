package com.diligentgroup.recipes.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CategoryCommandTest {

	CategoryCommand command;
	String description = "xyzzy";

	@Before
	public void setUp() throws Exception {
		command = CategoryCommand.builder().id(1L).build();
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
		CategoryCommand catCom = new CategoryCommand();
		assertNull(catCom.getId());
		catCom.setDescription(description);
		assertEquals(description, catCom.getDescription());
	}

}
