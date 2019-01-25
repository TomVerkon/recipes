package com.diligentgroup.recipes.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DifficultyCommandTest {

	DifficultyCommand command;

	@Before
	public void setUp() throws Exception {
		command = DifficultyCommand.builder().id(1L).build();
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
		DifficultyCommand difCom = new DifficultyCommand();
		assertNull(difCom.getId());
		difCom.setDescription("xyzzy");
		assertEquals("xyzzy", difCom.getDescription());
	}

}
