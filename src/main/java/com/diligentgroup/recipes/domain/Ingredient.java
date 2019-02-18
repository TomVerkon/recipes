package com.diligentgroup.recipes.domain;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Document
public class Ingredient extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 644119757531943358L;

	private BigDecimal amount;

	public Ingredient(String id, String description, BigDecimal amount, UnitOfMeasure uom) {
		super(id, description);
		this.amount = amount;
		this.uom = uom;
	}

	@DBRef
	private UnitOfMeasure uom;

}
