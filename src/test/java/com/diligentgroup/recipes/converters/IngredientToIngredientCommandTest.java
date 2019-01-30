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
		assert(target.getId().equals(source.getId()));
		assertEquals(target.getId(), source.getId());
		
		assert(target.getDescription().equals(source.getDescription()));
		assertEquals(target.getDescription(), source.getDescription());
		
		assert(target.getAmount().equals(source.getAmount()));
		assertEquals(target.getAmount(), source.getAmount());
		
		assert(target.getUom().getId().equals(source.getUom().getId()));
		assertEquals(target.getUom().getId(), source.getUom().getId());
		
		assert(target.getUom().getDescription().equals(source.getUom().getDescription()));
		assertEquals(target.getUom().getDescription(), source.getUom().getDescription());
	}
}
