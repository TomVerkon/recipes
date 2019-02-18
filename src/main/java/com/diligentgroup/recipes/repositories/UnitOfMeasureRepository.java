package com.diligentgroup.recipes.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.diligentgroup.recipes.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends MongoRepository<UnitOfMeasure, String> {

	Optional<UnitOfMeasure> findByDescription(String description);

}
