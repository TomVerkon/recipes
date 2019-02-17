package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

// Light weight unit test
public class CategoryTest {

	Category object;
	String idValue = String.valueOf(Long.MAX_VALUE);
	String description = "description";

	@Before
	public void setUp() throws Exception {
		object = Category.builder().id(idValue).description(description).build();
	}

	@Test
	public void testBuilderAndGetters() {
		// builder was used in
		assertEquals(idValue, object.getId());
		assertEquals(description, object.getDescription());
	}

	@Test
	public void testConstructorsAndSetters() {
		Category localObject = new Category();
		assertNull(localObject.getId());
		localObject = new Category(idValue, description);
		assertEquals(idValue, localObject.getId());
		assertEquals(description, localObject.getDescription());
		assert (localObject.getRecipes().isEmpty());
	}

	@Test
	public void testIsNewAndEquals() {
		Category localObject = new Category();
		localObject.setDescription(description);
		assert (localObject.isNew());
		assert (!object.isNew());
		assert (!localObject.equals(object));

		localObject.setId(idValue);
		assert (localObject.equals(object));

		object.getRecipes().add(Recipe.builder().id(idValue).build());
		// recipes not part of equals right now
		assert (localObject.equals(object));
	}

}
