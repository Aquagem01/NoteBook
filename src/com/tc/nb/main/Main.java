package com.tc.nb.main;

import java.io.IOException;
import com.tc.nb.controller.Controller;
import com.tc.nb.dal.DAOProvider;
import com.tc.nb.dal.NotesDAO;
import com.tc.nb.dal.exception.DAOException;
import com.tc.nb.dal.impl.FileNotesDAO;
import com.tc.nb.entity.Note;
import com.tc.nb.service.NotesCreateService;
import com.tc.nb.service.NotesFindService;
import com.tc.nb.service.exception.ServiceException;
import com.tc.nb.service.impl.NotesCreateServiceImpl;
import com.tc.nb.service.impl.NotesFindServiceImpl;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//Note n001 = new Note (1, new Date(2022-1900,11,8), "meeting with Victor");
		//Note n002 = new Note (2, new Date(2022-1900,11,9), "meeting with Alla");
		//Note n003 = new Note (3, new Date(2022-1900,11,11), "meeting with Vika");
		
		DAOProvider  provider = DAOProvider.getInstance();
		NotesDAO notesDAO = provider.getNotesDAO();
		
		
		
		try {
			notesDAO.load();
			
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		Controller contr = new Controller ();
		
		String s = contr.executeTask("Add_Note 5;10-12-2022;meeting with Nikola;");
		System.out.println(s);
		
		/*NotesFindServiceImpl find = new NotesFindServiceImpl();
		
		try {
			System.out.println(find.findByDate(new Date(0)));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		*/
		/*
		
		try {
			System.out.println(find.findByDate(new Date(2022-1900,11,8)));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*NotesCreateServiceImpl create = new NotesCreateServiceImpl ();
		
		try {
			create.add(n002);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		*/
		
		//notesDAO.save(n001);
		//notesDAO.save(n002);
		//notesDAO.save(n003);
		
		//System.out.println();
		
		
		
		//System.out.println(notesDAO.toString());
		
					
	}
}
