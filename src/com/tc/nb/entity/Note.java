package com.tc.nb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Note implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int numberOfNote;
	private Date date;
	private String textNote;

	public Note() {

	}

	public Note(int num, Date date, String text) {
		this.numberOfNote = num;
		this.date = date;
		this.textNote = text;
	}

	public int getNumberOfNote() {
		return numberOfNote;
	}

	public void setNumberOfNote(int numberOfNote) {
		this.numberOfNote = numberOfNote;
	}

	public String getTextNote() {
		return textNote;
	}

	public void setTextNote(String textNote) {
		this.textNote = textNote;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, numberOfNote, textNote);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		return Objects.equals(date, other.date) && numberOfNote == other.numberOfNote
				&& Objects.equals(textNote, other.textNote);
	}

	@Override
	public String toString() {
		return "Note [numberOfNote=" + numberOfNote + ", date=" + date + ", textNote=" + textNote + "]";
	}

}
