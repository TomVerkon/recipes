package com.diligentgroup.recipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.domain.Ingredient;
import com.diligentgroup.recipes.domain.UnitOfMeasure;

public class IngredientToIngredientCommandTest {

	Ingredient source;
	UnitOfMeasureToUnitOfMeasureCommand uomConverter;
	UnitOfMeasure uom;

	IngredientToIngredientCommand converter;

	BigDecimal amount = BigDecimal.TEN;
	String description = "description";
	Long id = Long.MAX_VALUE;

	@Before
	public void setUp() throws Exception {
		uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
		uom = UnitOfMeasure.builder().id(id).description(description).build();
		source = Ingredient.builder().amount(amount).description(description).id(id).uom(uom).build();
		converter = new IngredientToIngredientCommand(uomConverter);
	}

	@Test
	public void testConvertNull() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert() {
		IngredientCommand target = converter.convert(source);
		assert (source.getId().equals(target.getId()));
		assertEquals(source.getId(), target.getId());

		assert (source.getDescription().equals(target.getDescription()));
		assertEquals(source.getDescription(), target.getDescription());

		assert (source.getAmount().equals(target.getAmount()));
		assertEquals(source.getAmount(), target.getAmount());

		assert (source.getUom().getId().equals(target.getUom().getId()));
		assertEquals(source.getUom().getId(), target.getUom().getId());

		assert (source.getUom().getDescription().equals(target.getUom().getDescription()));
		assertEquals(source.getUom().getDescription(), target.getUom().getDescription());
	}
}
