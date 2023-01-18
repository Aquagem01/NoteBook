package com.tc.nb.service;

import com.tc.nb.entity.Note;
import com.tc.nb.service.exception.ServiceException;

public interface NotesCreateService {

	void add(Note n) throws ServiceException;
}
