package com.tc.nb.controller.command.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tc.nb.controller.command.Command;
import com.tc.nb.entity.Note;
import com.tc.nb.service.NotesCreateService;
import com.tc.nb.service.ServiceProviderCreate;
import com.tc.nb.service.exception.ServiceException;

public class AddNote implements Command {

	private final char paramDelimeter = ' ';
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public String execute(String request) {
		String findString = request.substring(request.indexOf(paramDelimeter) + 1);
		String response;

		ServiceProviderCreate service = ServiceProviderCreate.getInstance();
		NotesCreateService createService = service.getCreateServ();

		Note note = null;
		
		try {
			note = requestStringToNote(findString);
		} catch (ServiceException e1) {
			response = "Failed to convert date\n";
			e1.printStackTrace();
		}

		try {
			createService.add(note);
			response = "Note creation was successful";
		} catch (ServiceException e) {
			response = "Failed to create Note\n" + e;
			e.printStackTrace();

		}

		return response;
	}

	private Note requestStringToNote(String findString) throws ServiceException {
		
		Note note = new Note();

		Pattern stringStart = Pattern.compile("(\\d+)");
		Pattern stringNext = Pattern.compile("(\\d{2}\\-\\d{2}\\-\\d{4});(.+);", Pattern.CASE_INSENSITIVE);

		Matcher lineMatch = stringStart.matcher(findString);
		lineMatch.lookingAt();

		note.setNumberOfNote(Integer.parseInt(lineMatch.group(1)));
		lineMatch.reset();

		lineMatch = stringNext.matcher(findString);

		lineMatch.find();

		try {
			note.setDate(sdf.parse(lineMatch.group(1)));
		} catch (ParseException e) {
			throw new ServiceException("Failed to convert date\n" + e);
		}
		note.setTextNote(lineMatch.group(2));

		return note;
	}
}
