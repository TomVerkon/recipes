package com.diligentgroup.recipes.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.diligentgroup.recipes.command.UnitOfMeasureCommand;
import com.diligentgroup.recipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.diligentgroup.recipes.domain.UnitOfMeasure;
import com.diligentgroup.recipes.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImplTest {

	// We need a real command and service for this test, not a mock
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
	UnitOfMeasureService service;

	// We can use a mock repository
	@Mock
	UnitOfMeasureRepository repository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	public void testGetAllUnitOfMeasures() {

		// givin - setup the mock repository return values
		Set<UnitOfMeasure> uoms = new HashSet<>();
		uoms.add(UnitOfMeasure.builder().id(1L).build());
		uoms.add(UnitOfMeasure.builder().id(2L).build());

		// when - stipulate that the repository will return the values above
		when(repository.findAll()).thenReturn(uoms);

		// then - make the call to the service and
		Set<UnitOfMeasureCommand> uomComs = service.getAllUnitOfMeasures();
		// should receive to uoms back
		assertEquals(2, uomComs.size());
		// repository findAll should anly be called once
		verify(repository, times(1)).findAll();
	}

}
