package com.diligentgroup.recipes.domain;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.diligentgroup.recipes.repositories.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureTest {
	
	@Autowired
	UnitOfMeasureRepository repository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//@DirtiesContext
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

}
