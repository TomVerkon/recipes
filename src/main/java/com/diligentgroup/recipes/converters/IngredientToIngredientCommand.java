package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.command.UnitOfMeasureCommand;
import com.diligentgroup.recipes.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		return IngredientCommand
				.builder().amount(source.getAmount()).description(source.getDescription()).uom(UnitOfMeasureCommand
						.builder().id(source.getUom().getId()).description(source.getUom().getDescription()).build())
				.id(source.getId()).build();
	}

}
