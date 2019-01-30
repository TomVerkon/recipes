package com.diligentgroup.recipes.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class NoteCommand extends IdCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4715980346154920445L;

	private String recipeNotes;

	public NoteCommand(Long id, String recipeNotes) {
		super(id);
		this.recipeNotes = recipeNotes;
	}

}
