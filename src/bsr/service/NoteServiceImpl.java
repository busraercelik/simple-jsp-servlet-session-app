package bsr.service;

import bsr.model.Note;
import bsr.repo.NoteRepo;
import bsr.repo.impl.NoteRepoImpl;

public class NoteServiceImpl implements NoteService {

	NoteRepo noteRepo = new NoteRepoImpl();
	
	@Override
	public Note getNote(String name) {
		return noteRepo.getNote(name);
	}

	@Override
	public Note updateNote(Note note) {
		return noteRepo.updateNote(note);
	}

}
