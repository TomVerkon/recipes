package com.diligentgroup.recipes.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
@EqualsAndHashCode(callSuper = true, exclude = { "recipe" })
@Entity
public class Note extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3684426981819400142L;

	@Builder
	public Note(Long id, String recipeNotes) {
		super(id);
		this.recipeNotes = recipeNotes;
	}

	// no cascade because recipe manages this relationship
	@ToString.Exclude
	@OneToOne
	private Recipe recipe;

	@Lob
	private String recipeNotes;

}
