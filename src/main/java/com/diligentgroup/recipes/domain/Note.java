package com.diligentgroup.recipes.domain;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = { "recipe" })
@Component
public class Note extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3684426981819400142L;

	public Note(String id, String recipeNotes) {
		super(id);
		this.recipeNotes = recipeNotes;
	}

	public Note(String id, String recipeNotes, Recipe recipe) {
		super(id);
		this.recipeNotes = recipeNotes;
		this.recipe = recipe;
	}

	// no cascade because recipe manages this relationship
	@ToString.Exclude
	private Recipe recipe;

	private String recipeNotes;

}
