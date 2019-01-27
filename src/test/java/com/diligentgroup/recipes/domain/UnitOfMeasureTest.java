package com.diligentgroup.recipes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

//Light weight unit test
public class UnitOfMeasureTest {

	UnitOfMeasure object;
	Long idValue = 4L;
	String description = "xyzzy";

	@Before
	public void setUp() throws Exception {
		object = UnitOfMeasure.builder().id(idValue).description(description)
				.build();
	}

	@Test
	public void testId() {
		assertEquals(idValue, object.getId());
	}

	@Test
	public void testDescription() {
		assertEquals(description, object.getDescription());
	}

	@Test
	public void testIsNewAndEquals() {
		UnitOfMeasure localObject = new UnitOfMeasure();
		localObject.setDescription(description);
		assert (localObject.isNew());
		assert (!object.isNew());
		assertNotEquals(localObject, object);
		localObject.setId(idValue);
		assertEquals(localObject, object);
	}

}
