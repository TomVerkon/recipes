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

	private String recipeNotes;

}
