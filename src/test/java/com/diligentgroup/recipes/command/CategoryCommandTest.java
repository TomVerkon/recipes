package com.diligentgroup.recipes.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryCommandTest {
	
	CategoryCommand command;

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
		command.setDescription("xyzzy");
		assertEquals("xyzzy", command.getDescription());
	}

	@Test
	public void testConstructor() {
		CategoryCommand catCom = new CategoryCommand();
		assertNull(catCom.getId());
		catCom.setDescription("xyzzy");
		assertEquals("xyzzy", catCom.getDescription());
	}

}
