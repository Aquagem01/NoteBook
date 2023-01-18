package com.tc.nb.service.exception;

import com.tc.nb.dal.exception.DAOException;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, DAOException e) {
		super(message);
	}
}
