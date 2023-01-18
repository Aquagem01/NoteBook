package com.tc.nb.service.impl;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tc.nb.dal.DAOProvider;
import com.tc.nb.dal.NotesDAO;
import com.tc.nb.dal.exception.DAOException;
import com.tc.nb.entity.Note;
import com.tc.nb.service.NotesCreateService;
import com.tc.nb.service.exception.ServiceException;

public class NotesCreateServiceImpl implements NotesCreateService {

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public void add(Note note) throws ServiceException {

		boolean valid = validate(note);
		if (valid) {

			DAOProvider provider = DAOProvider.getInstance();
			NotesDAO notesDAO = provider.getNotesDAO();

			try {
				notesDAO.load();
				notesDAO.save(note);
			} catch (DAOException e) {
				throw new ServiceException("Сan't save List.", e);
			}
		} else {
			throw new ServiceException("Entered data is not correct.");
		}
	}

	private boolean validate(Note note) {
		String controlStr = noteToString(note);
		boolean val;

		Pattern stringStart = Pattern.compile("(\\d+);(\\d{2}\\-\\d{2}\\-\\d{4});(.+);\n", Pattern.CASE_INSENSITIVE);

		Matcher lineMatch = stringStart.matcher(controlStr);

		if (!lineMatch.matches()) {
			val = false;
		} else {
			val = true;
		}
		return val;
	}

	private String noteToString(Note note) {
		StringBuilder formStr = new StringBuilder();
		formStr.append(String.valueOf(note.getNumberOfNote()));
		formStr.append(";");
		formStr.append(sdf.format(note.getDate()));
		formStr.append(";");
		formStr.append(note.getTextNote());
		formStr.append(";\n");

		String formString = formStr.toString();
		System.out.println(formString);
		return formString;
	}
}
