package com.diligentgroup.recipes.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Table(name = "ingredients")
@EqualsAndHashCode(callSuper = true, exclude = { "recipe" })
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

	private UnitOfMeasure uom;

	@ToString.Exclude
	private Recipe recipe;

}
