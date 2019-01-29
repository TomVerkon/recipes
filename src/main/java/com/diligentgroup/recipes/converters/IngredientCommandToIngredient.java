package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.domain.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private UnitOfMeasureCommandToUnitOfMeasure uomConverter;

	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null) {
			return null;
		}
		return Ingredient.builder().id(source.getId()).amount(source.getAmount()).description(source.getDescription())
				.uom(uomConverter.convert(source.getUom())).build();
	}

}
