package com.tc.nb.presentation;

import java.util.List;

import com.tc.nb.entity.Note;

public class NotePresentation implements Presentation{

	public NotePresentation() {

	}

	public String OneNotePresentation(Note note) {
		StringBuilder strBul = new StringBuilder();
		strBul.append("Number of Note:" + note.getNumberOfNote() + "\t");
		strBul.append("Date: " + note.getDate() + "\t");
		strBul.append("Content: " + note.getTextNote() + ".\n");
		String noteString = strBul.toString();
		return noteString;
	}

	private StringBuilder NotePresentationStaff(Note note) {
		StringBuilder strBul = new StringBuilder();
		strBul.append("Number of Note:" + note.getNumberOfNote() + "\t");
		strBul.append("Date: " + note.getDate() + "\t");
		strBul.append("Content: " + note.getTextNote() + ".\n");
		return strBul;
	}

	public String ListNotePresentation(List<Note> notes) {
		StringBuilder strs = new StringBuilder();

		for (Note note : notes) {
			strs.append(NotePresentationStaff(note));
		}
		String listNoteString = strs.toString();
		return listNoteString;
	}

}
