package com.diligentgroup.recipes.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class IngredientCommandTest {

	IngredientCommand command;
	String description = "xyzzy";

	@Before
	public void setUp() throws Exception {
		command = IngredientCommand.builder().id(1L).description(description)
				.amount(new BigDecimal(2.0)).uom(UomCommand.builder()
						.id(1L).description(description).build())
				.build();
	}

	@Test
	public void testIds() {
		assertEquals(Long.valueOf(1L), command.getId());
		assertEquals(Long.valueOf(1L), command.getUom().getId());
	}

	@Test
	public void testDescriptions() {
		assertEquals(description, command.getDescription());
		assertEquals(description, command.getUom().getDescription());
	}

	@Test
	public void testConstructor() {
		IngredientCommand localCom = new IngredientCommand();
		assertNull(localCom.getId());
		localCom.setDescription(description);
		assertEquals(description, localCom.getDescription());
	}

}
