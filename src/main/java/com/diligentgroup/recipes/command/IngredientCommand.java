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

	private BigDecimal amount;
	private UnitOfMeasureCommand uom;

}
