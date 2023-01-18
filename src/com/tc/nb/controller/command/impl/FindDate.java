package com.tc.nb.controller.command.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.tc.nb.controller.command.Command;
import com.tc.nb.entity.Note;
import com.tc.nb.presentation.NotePresentation;
import com.tc.nb.presentation.PresentationProvider;
import com.tc.nb.service.NotesFindService;
import com.tc.nb.service.ServiceProviderFind;
import com.tc.nb.service.exception.ServiceException;

public class FindDate implements Command {

	private final char paramDelimeter = ' ';
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public String execute(String request) {
		String findString = request.substring(request.indexOf(paramDelimeter) + 1);

		String response;
		NotePresentation presentation = PresentationProvider.getInstance();

		ServiceProviderFind service = ServiceProviderFind.getInstance();
		NotesFindService findService = service.getFindServ();
		List<Note> searchList = new ArrayList<Note>();

		try {
			searchList = findService.findByDate(sdf.parse(findString));

		} catch (ServiceException e) {
			response = "Bad request" + e;
			e.printStackTrace();
		} catch (ParseException e1) {
			response = "Entered data is not correct." + e1;
			e1.printStackTrace();

		}

		if (searchList.isEmpty()) {
			response = "No matches found";
		} else {
			response = "Found " + searchList.size() + " matches: \n" + presentation.ListNotePresentation(searchList);
		}

		return response;
	}

}
