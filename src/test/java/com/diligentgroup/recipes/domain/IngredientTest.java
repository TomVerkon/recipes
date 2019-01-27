package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class IngredientTest {

	Ingredient object;
	Long idValue = Long.valueOf(Long.MAX_VALUE);

	String description = "xyzzy";

	@Before
	public void setUp() throws Exception {
		object = Ingredient.builder().id(idValue).description(description)
				.build();
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
		Ingredient localObject = new Ingredient();
		localObject.setDescription(description);
		assert (localObject.isNew());
		assert (!object.isNew());
		assertNotEquals(localObject, object);
		localObject.setId(idValue);
		assertEquals(localObject, object);
	}

	@Test
	public void testUomAndAmount() {

		object.setUom(UnitOfMeasure.builder().id(idValue)
				.description(description).build());

		Ingredient localObject = new Ingredient();
		localObject.setId(idValue);
		localObject.setDescription(description);
		localObject.setUom(UnitOfMeasure.builder().id(idValue).build());
		assertNotEquals(localObject, object);

		localObject.getUom().setDescription(description);
		assertEquals(localObject, object);

		object.setAmount(BigDecimal.ONE);
		assertNotEquals(localObject, object);

		localObject.setAmount(BigDecimal.ONE);
		;
		assertEquals(localObject, object);

	}
}
