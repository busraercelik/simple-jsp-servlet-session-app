package bsr.test;


import org.junit.Test;

import bsr.model.Note;
import bsr.repo.NoteRepo;
import bsr.repo.impl.NoteRepoImpl;



public class RepoTest {
	
	NoteRepo noteRepo = new NoteRepoImpl();
	
	/*
	@Test
	public void getNoteTest() {
		Note note = noteRepo.getNote("bercelik");
		System.out.println(note);
	}
	*/
	
	@Test
	public void updateNoteTest() {
		Note note = new Note();
		note.setNote("hi there");
		note.setUserId((long) 2);
		Note updatedNote = noteRepo.updateNote(note);
		Note newNote = noteRepo.getNote("jdepp");
		System.out.println("newNote: "+newNote);
	}

}
