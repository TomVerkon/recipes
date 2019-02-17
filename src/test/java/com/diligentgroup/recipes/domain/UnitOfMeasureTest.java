package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

//Light weight unit test
public class UnitOfMeasureTest {

	UnitOfMeasure object;
	String idValue = String.valueOf(Long.MAX_VALUE);
	String description = "description";

	@Before
	public void setUp() throws Exception {
		object = UnitOfMeasure.builder().id(idValue).description(description).build();
	}

	@Test
	public void testBuilderAndGetters() {
		// builder was used in
		assertEquals(idValue, object.getId());
		assertEquals(description, object.getDescription());
	}

	@Test
	public void testConstructorsAndSetters() {
		UnitOfMeasure localObject = new UnitOfMeasure();
		assertNull(localObject.getId());
		localObject = new UnitOfMeasure(idValue, description);
		assertEquals(idValue, localObject.getId());
		assertEquals(description, localObject.getDescription());
	}

	@Test
	public void testIsNewAndEquals() {
		UnitOfMeasure localObject = new UnitOfMeasure();
		localObject.setDescription(description);
		assert (localObject.isNew());
		assert (!object.isNew());
		assert (!localObject.equals(object));
		localObject.setId(idValue);
		assert (localObject.equals(object));
	}

}
