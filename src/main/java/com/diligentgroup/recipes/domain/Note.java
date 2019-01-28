package com.diligentgroup.recipes.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "notes")
@EqualsAndHashCode(callSuper = true, exclude = { "recipe" })
@Entity
public class Note extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3684426981819400142L;

	public Note(Long id, String recipeNotes) {
		super(id);
		this.recipeNotes = recipeNotes;
	}

	public Note(Long id, String recipeNotes, Recipe recipe) {
		super(id);
		this.recipeNotes = recipeNotes;
		this.recipe = recipe;
	}

	// no cascade because recipe manages this relationship
	@ToString.Exclude
	@OneToOne
	private Recipe recipe;

	@Lob
	private String recipeNotes;

}
