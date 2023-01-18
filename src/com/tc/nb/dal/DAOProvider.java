package com.tc.nb.dal;

import com.tc.nb.dal.impl.FileNotesDAO;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	
	private NotesDAO noteDAO = new FileNotesDAO();
	
	public NotesDAO getNotesDAO () {
		return noteDAO;
	}
	public static DAOProvider getInstance () {
		return instance;
	}
	@Override
	public String toString() {
		return "DAOProvider [noteDAO=" + noteDAO + "]";
	}
	
}
