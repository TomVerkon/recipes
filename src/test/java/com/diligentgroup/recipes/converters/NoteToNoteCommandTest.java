package com.diligentgroup.recipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.diligentgroup.recipes.command.NoteCommand;
import com.diligentgroup.recipes.domain.Note;

public class NoteToNoteCommandTest {

	NoteToNoteCommand converter;
	Note source;

	@Before
	public void setUp() throws Exception {
		this.converter = new NoteToNoteCommand();
		source = new Note(1L, "recipeNotes");
	}

	@Test
	public void testConvertNull() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert() {
		NoteCommand target = converter.convert(source);
		assert (source.getId().equals(target.getId()));
		assertEquals(source.getId(), target.getId());
		assert (source.getRecipeNotes().equals(target.getRecipeNotes()));
		assertEquals(source.getRecipeNotes(), target.getRecipeNotes());
	}
}
