package com.tc.nb.presentation;

public class PresentationProvider {

	private static final NotePresentation instance = new NotePresentation();

	private Presentation presentation = new NotePresentation();

	public Presentation getPresentation() {
		return presentation;
	}

	public static NotePresentation getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "PresentationProvider [presentation=" + presentation + "]";
	}

}
