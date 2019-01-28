package com.diligentgroup.recipes.command;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class IngredientCommand extends DescribedCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1343369070688040643L;
	
	private BigDecimal amount;
	private UnitOfMeasureCommand uom;

}
