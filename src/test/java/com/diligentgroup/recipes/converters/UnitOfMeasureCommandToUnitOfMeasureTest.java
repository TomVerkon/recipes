package com.diligentgroup.recipes.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.diligentgroup.recipes.command.UnitOfMeasureCommand;
import com.diligentgroup.recipes.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	UnitOfMeasureCommand source;
	
	UnitOfMeasureCommandToUnitOfMeasure converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
		source = new UnitOfMeasureCommand(10L, "description");
	}

	@Test
	public void testConvertNull() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert() {
		UnitOfMeasure target = converter.convert(source);
		assert(target.getId().equals(source.getId()));
		assertEquals(target.getId(), source.getId());
		assert(target.getDescription().equals(source.getDescription()));
		assertEquals(target.getDescription(), source.getDescription());
	}
}
