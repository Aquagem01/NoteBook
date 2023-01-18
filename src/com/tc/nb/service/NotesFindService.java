package com.tc.nb.service;

import java.util.Date;
import java.util.List;

import com.tc.nb.entity.Note;
import com.tc.nb.service.exception.ServiceException;

public interface NotesFindService {
	List<Note> findByContent(String content) throws ServiceException;

	List<Note> findByDate(Date number) throws ServiceException;

}
