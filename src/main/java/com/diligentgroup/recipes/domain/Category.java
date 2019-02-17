package com.diligentgroup.recipes.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = { "recipes" })
@Component
public class Category extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6593873604164076107L;

	public Category(String id, String description) {
		super(id, description);
	}

	public Category(String id, String description, Set<Recipe> recipes) {
		super(id, description);
		this.recipes = recipes;
	}

	@Builder.Default
	private Set<Recipe> recipes = new HashSet<>();

	public Set<Recipe> getRecipes() {
		if (recipes == null) {
			recipes = new HashSet<>();
		}
		return recipes;
	}

}
