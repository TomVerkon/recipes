package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private UnitOfMeasureToUnitOfMeasureCommand uomConverter;

	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
		this.uomConverter = uomConverter;
	}

	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		IngredientCommand ingredientCommand = IngredientCommand.builder().amount(source.getAmount())
				.description(source.getDescription()).uom(uomConverter.convert(source.getUom())).id(source.getId())
				.build();
		return ingredientCommand;
	}

}
