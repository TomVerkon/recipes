package com.diligentgroup.recipes.command;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class IngredientCommand extends DescribedCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1343369070688040643L;

	private BigDecimal amount;
	private UnitOfMeasureCommand uom;
	private Long recipeId;

	public IngredientCommand(Long id, String description, BigDecimal amount, UnitOfMeasureCommand uom) {
		super(id, description);
		this.amount = amount;
		this.uom = uom;
	}

}
