package com.diligentgroup.recipes.command;

import java.math.BigDecimal;

import com.diligentgroup.recipes.domain.Ingredient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = { "recipe" })
@SuperBuilder
public class IngredientCommand extends DescribedCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1343369070688040643L;
	
	private BigDecimal amount;
	private UnitOfMeasureCommand uom;

}
