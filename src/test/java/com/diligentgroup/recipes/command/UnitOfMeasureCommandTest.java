package com.diligentgroup.recipes.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class UnitOfMeasureCommandTest {

	UnitOfMeasureCommand command;
	String description = "xyzzy";

	@Before
	public void setUp() throws Exception {
		command = UnitOfMeasureCommand.builder().id(1L).build();
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
		UnitOfMeasureCommand localCom = new UnitOfMeasureCommand();
		assertNull(localCom.getId());
		localCom.setDescription(description);
		assertEquals(description, localCom.getDescription());
	}

}
