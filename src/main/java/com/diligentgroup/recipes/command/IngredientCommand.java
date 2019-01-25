package com.diligentgroup.recipes.command;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.diligentgroup.recipes.domain.UnitOfMeasure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand uom;
	
}
