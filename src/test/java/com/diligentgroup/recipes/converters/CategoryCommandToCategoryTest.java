package com.diligentgroup.recipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.diligentgroup.recipes.command.CategoryCommand;
import com.diligentgroup.recipes.domain.Category;

public class CategoryCommandToCategoryTest {

	CategoryCommand source;

	CategoryCommandToCategory converter;

	@Before
	public void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
		source = new CategoryCommand(10L, "description");
	}

	@Test
	public void testConvertNull() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert() {
		Category target = converter.convert(source);
		assert (target.getId().equals(source.getId()));
		assertEquals(target.getId(), source.getId());
		assert (target.getDescription().equals(source.getDescription()));
		assertEquals(target.getDescription(), source.getDescription());
	}

}
