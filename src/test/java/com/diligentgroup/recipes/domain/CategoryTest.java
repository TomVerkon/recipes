package com.diligentgroup.recipes.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;

	Long idValue = 4L;

	@Before
	public void setUp() throws Exception {
		category = new Category();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		category.setId(idValue);
		assertEquals(idValue, category.getId());
	}

}
