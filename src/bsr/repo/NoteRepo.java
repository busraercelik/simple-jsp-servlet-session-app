package bsr.repo;

import bsr.model.Note;

public interface NoteRepo {
	public Note getNote(String name); 
	public Note updateNote(Note note);
}
