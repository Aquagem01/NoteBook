package com.tc.nb.presentation;

import java.util.List;

import com.tc.nb.entity.Note;

public interface Presentation {
	
	public String OneNotePresentation(Note note); 
	
	public String ListNotePresentation(List<Note> notes);

}
