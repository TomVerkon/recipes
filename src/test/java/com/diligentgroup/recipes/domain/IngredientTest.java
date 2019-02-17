package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class IngredientTest {

	Ingredient object;
	String idValue = String.valueOf(Long.MAX_VALUE);
	String description = "description";
	UnitOfMeasure uom;
	BigDecimal amount = BigDecimal.TEN;

	@Before
	public void setUp() throws Exception {
		uom = UnitOfMeasure.builder().id(idValue).description(description).build();
		object = Ingredient.builder().id(idValue).description(description).uom(uom).amount(amount).build();
	}

	@Test
	public void testBuilderAndGetters() {
		// builder was used in setup
		assertEquals(idValue, object.getId());
		assertEquals(description, object.getDescription());
		assertEquals(uom, object.getUom());
	}

	@Test
	public void testConstructorsAndSetters() {
		Ingredient localObject = new Ingredient();
		assertNull(localObject.getId());
		localObject.setAmount(amount);
		localObject.setDescription(description);
		localObject.setId(idValue);
		localObject.setUom(uom);
		Ingredient localObject2 = new Ingredient(idValue, description, amount, uom);
		assert (localObject.equals(localObject2));
		assertNull(localObject.getRecipe());
		localObject2.setRecipe(Recipe.builder().id(idValue).build());
		assertEquals(localObject, localObject2);
	}

	@Test
	public void testIsNewAndEquals() {
		Ingredient localObject = new Ingredient();
		localObject.setDescription(description);
		assert (localObject.isNew());
		assert (!object.isNew());
		localObject.setId(idValue);
		localObject.setAmount(amount);
		localObject.setUom(uom);
		assert (localObject.equals(object));
	}

}
