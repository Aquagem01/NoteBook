package com.tc.nb.dal.exception;

import java.io.IOException;
import java.text.ParseException;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}

	public DAOException(String message, IOException e) {
		super(message, e);
	}

	public DAOException(String message, ParseException e) {
		super(message, e);
	}

}
