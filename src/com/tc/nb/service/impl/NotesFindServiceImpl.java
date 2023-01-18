package com.tc.nb.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tc.nb.dal.DAOProvider;
import com.tc.nb.dal.NotesDAO;
import com.tc.nb.dal.exception.DAOException;
import com.tc.nb.entity.Note;
import com.tc.nb.service.NotesFindService;
import com.tc.nb.service.exception.ServiceException;

public class NotesFindServiceImpl implements NotesFindService {

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public List<Note> findByContent(String content) throws ServiceException {

		Pattern p = Pattern.compile("[.[^;]]+");
		Matcher match = p.matcher(content);

		List<Note> searchNotes = new ArrayList<Note>();

		if (match.matches()) {

			DAOProvider provider = DAOProvider.getInstance();
			NotesDAO notesDAO = provider.getNotesDAO();

			try {
				List<Note> sourceList = notesDAO.load();

				if (sourceList != null) {
					for (Note note : sourceList) {
						if (content.equals(note.getTextNote())) {
							searchNotes.add(note);

						}
					}
				}

			} catch (DAOException e) {
				throw new ServiceException("Сan't load List.", e);
			}
		} else {
			throw new ServiceException("Entered data is not correct.");
		}
		return searchNotes;
	}

	@Override
	public List<Note> findByDate(Date number) throws ServiceException {

		List<Note> searchNotes = new ArrayList<Note>();

		String numberStr = sdf.format(number);
		Pattern p = Pattern.compile("(\\d{2}\\-\\d{2}\\-\\d{4})");
		Matcher match = p.matcher(numberStr);
		System.out.println(numberStr);

		if (match.matches()) {

			DAOProvider provider = DAOProvider.getInstance();
			NotesDAO notesDAO = provider.getNotesDAO();

			try {
				List<Note> sourceList = notesDAO.load();
				if (sourceList != null) {
					for (Note note : sourceList) {
						if (number.equals(note.getDate())) {
							searchNotes.add(note);
						}
					}
				}
			} catch (DAOException e) {
				throw new ServiceException("Сan't load List.", e);
			}
		} else {
			throw new ServiceException("Entered data is not correct.");
		}
		return searchNotes;
	}

}
