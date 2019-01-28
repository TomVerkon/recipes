package com.diligentgroup.recipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.command.NoteCommand;
import com.diligentgroup.recipes.domain.Note;

import lombok.Synchronized;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

	@Synchronized
	@Nullable
	@Override
	public Note convert(NoteCommand source) {
		if (source == null) {
			return null;
		}
		return Note.builder().id(source.getId()).recipeNotes(source.getRecipeNotes()).build();
	}

}
