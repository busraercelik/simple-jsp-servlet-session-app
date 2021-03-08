package bsr.service;

import bsr.model.Note;

public interface NoteService {
	public Note getNote(String name); 
	public Note updateNote(Note note);
}
