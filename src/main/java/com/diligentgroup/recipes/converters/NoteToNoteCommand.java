package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.NoteCommand;
import com.diligentgroup.recipes.domain.Note;

import lombok.Synchronized;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

	@Synchronized
	@Nullable
	@Override
	public NoteCommand convert(Note source) {
		if (source == null) {
			return null;
		}
		return NoteCommand.builder().id(source.getId()).recipeNotes(source.getRecipeNotes()).build();
	}

}
