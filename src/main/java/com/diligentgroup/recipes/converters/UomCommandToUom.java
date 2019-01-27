package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.UomCommand;
import com.diligentgroup.recipes.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UomCommandToUom
		implements Converter<UomCommand, UnitOfMeasure> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UomCommand source) {
		if (source == null) {
			return null;
		}
		return UnitOfMeasure.builder().id(source.getId())
				.description(source.getDescription()).build();
	}

}
