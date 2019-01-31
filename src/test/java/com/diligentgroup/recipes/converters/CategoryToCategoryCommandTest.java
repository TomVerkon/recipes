package com.diligentgroup.recipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.diligentgroup.recipes.command.CategoryCommand;
import com.diligentgroup.recipes.domain.Category;

public class CategoryToCategoryCommandTest {

	Category source;

	CategoryToCategoryCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
		source = new Category(10L, "description");
	}

	@Test
	public void testConvertNull() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert() {
		CategoryCommand target = converter.convert(source);
		assert (source.getId().equals(target.getId()));
		assertEquals(source.getId(), target.getId());
		assert (source.getDescription().equals(target.getDescription()));
		assertEquals(source.getDescription(), target.getDescription());
	}

}
