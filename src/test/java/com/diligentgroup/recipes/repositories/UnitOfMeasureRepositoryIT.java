package com.diligentgroup.recipes.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.diligentgroup.recipes.domain.UnitOfMeasure;

//Integration test with the back end.
@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository repository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@DirtiesContext
	public void testFindByTsps() {
		Optional<UnitOfMeasure> optionalUom = repository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", optionalUom.get().getDescription());
	}

	@Test
	@DirtiesContext
	public void testFindByCup() {
		Optional<UnitOfMeasure> optionalUom = repository.findByDescription("Cup");
		assertEquals("Cup", optionalUom.get().getDescription());
	}

	@Test
	@DirtiesContext
	public void testSaveQuartAndFindByQuartAndEquals() {
		String quart = "Quart";
		UnitOfMeasure uom = UnitOfMeasure.builder().description(quart).build();
		uom = repository.save(uom);
		assertNotNull(uom.getId());
		assertEquals(quart, uom.getDescription());

		UnitOfMeasure fetchedUom = repository.findByDescription(quart).orElse(null);
		assertNotNull(fetchedUom);
		assertEquals(uom, fetchedUom);
	}

}
