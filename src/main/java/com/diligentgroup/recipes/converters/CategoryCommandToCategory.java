package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.CategoryCommand;
import com.diligentgroup.recipes.domain.Category;

import lombok.Synchronized;

@Component
public class CategoryCommandToCategory
		implements Converter<CategoryCommand, Category> {

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if (source == null) {
			return null;
		}
		return Category.builder().id(source.getId())
				.description(source.getDescription()).build();
	}

}
