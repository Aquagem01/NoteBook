package com.tc.nb.dal.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tc.nb.dal.NotesDAO;
import com.tc.nb.dal.exception.DAOException;
import com.tc.nb.entity.Note;

public class FileNotesDAO implements NotesDAO {

	private final List<Note> notes = new ArrayList<Note>();

	private static final String SOURCE_PATH = "resources//notes.txt";
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public FileNotesDAO() {

	}

	@Override
	public void save(Note note) throws DAOException {
		notes.add(note);
		try (FileWriter writer = new FileWriter(SOURCE_PATH, false)) {
			for (Note notei : notes) {
				String writeToFile = noteToString(notei);
				writer.write(writeToFile);
			}
		} catch (IOException e) {
			throw new DAOException("Everything broke down on the DAO level. Details: ", e);

		}

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
		return formString;
	}

	@Override
	public List<Note> load() throws DAOException {
		if (notes.isEmpty()) {
			List<String> dataString = new ArrayList<String>();
			try {

				readFromFile(dataString);
				parseString(dataString);
			} catch (FileNotFoundException e) {
				throw new DAOException("Сan't find file.", e);
			} catch (IOException e) {
				throw new DAOException("Everything broke down on the DAO level. Details: ", e);
			} catch (ParseException e) {
				throw new DAOException("Date not parsed.", e);
			}

		}
		return notes;
	}

	private List<String> readFromFile(List<String> list) throws IOException, FileNotFoundException {

		try (FileReader fr = new FileReader(SOURCE_PATH); BufferedReader reader = new BufferedReader(fr);) {

			String oneLine;
			while ((oneLine = reader.readLine()) != null) {
				list.add(oneLine);
			}
		}
		return list;
	}
	// попробуем переписать метод parseString через Pattern и Matcher

	private List<Note> parseString(List<String> dataStringArray) throws ParseException {

		Pattern stringStart = Pattern.compile("(\\d+)");
		Pattern stringFromFile = Pattern.compile("(\\d{2}\\-\\d{2}\\-\\d{4});(.+);");

		for (String lineData : dataStringArray) {
			Note note = new Note();

			Matcher lineMatch = stringStart.matcher(lineData);
			lineMatch.lookingAt();

			note.setNumberOfNote(Integer.parseInt(lineMatch.group(1)));

			lineMatch.reset();
			lineMatch = stringFromFile.matcher(lineData);

			lineMatch.find();
			note.setDate(sdf.parse(lineMatch.group(1)));
			note.setTextNote(lineMatch.group(2));
			notes.add(note);
		}
		return notes;
	}

	private List<Note> parseString1(List<String> dataStringArray) throws ParseException {
		String numberOfNote;
		String date;
		String textNote;
		// парсим строку и ее части помещаем в создаваемые Note, из которых в свою
		// очередь формируем коллекцию
		for (String lineData : dataStringArray) {
			Note note = new Note();
			String[] partsOFSinleLine = lineData.split(";");

			
			numberOfNote = partsOFSinleLine[0];
			note.setNumberOfNote(Integer.parseInt(numberOfNote));

			
			date = partsOFSinleLine[1];
			try {
				note.setDate(sdf.parse(date));

			} finally {
			}
			
			textNote = partsOFSinleLine[2];
			note.setTextNote(textNote);

			notes.add(note);
		}
		return notes;
	}

// для самоконтроля
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Note note : notes) {
			str.append(note.toString());
			str.append("\n");
		}
		String strFin = str.toString();
		return strFin;
	}
}
