package com.tc.nb.controller.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.tc.nb.controller.command.Command;
import com.tc.nb.entity.Note;
import com.tc.nb.presentation.NotePresentation;
import com.tc.nb.presentation.PresentationProvider;
import com.tc.nb.service.NotesFindService;
import com.tc.nb.service.ServiceProviderFind;
import com.tc.nb.service.exception.ServiceException;


public class FindContent implements Command {

	private final char paramDelimeter = ' ';

	@Override
	public String execute(String request) {

		String findString = request.substring(request.indexOf(paramDelimeter) + 1);
		String response;
		
		NotePresentation presentation = PresentationProvider.getInstance();
		

		ServiceProviderFind service = ServiceProviderFind.getInstance();
		NotesFindService findService = service.getFindServ();
		List<Note> searchList = new ArrayList<Note>();

		try {
			searchList = findService.findByContent(findString);
		} catch (ServiceException e) {
			response = "Bad request";
			e.printStackTrace();
		}

		if (searchList.isEmpty()) {
			response = "No matches found";
		} else {
			response = "Found " + searchList.size() + " matches: \n" + presentation.ListNotePresentation(searchList);
		}

		return response;
	}

}
