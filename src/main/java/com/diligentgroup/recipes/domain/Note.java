package com.diligentgroup.recipes.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Document
public class Note extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3684426981819400142L;

	public Note(String id, String recipeNotes) {
		super(id);
		this.recipeNotes = recipeNotes;
	}

	private String recipeNotes;

}
