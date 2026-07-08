package com.SpringProject.userManagement.service;
import com.SpringProject.userManagement.entity.Note;

import com.SpringProject.userManagement.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



public interface NoteService {
    Note createNote(Note note);

    List<Note> getAllNotes();

    Note getNoteById(Long id);

    Note updateNote(Long id, Note note);

    void deleteNote(Long id);
    List<Note> getNoteByUserId(Long userId);

    Note createNoteUnderCategory(Long categoryId, Note note);
    List<Note> getNotesByCategoryId(Long categoryId);

    List<Note> searchByTitle(String title);
    List<Note> searchByDescription(String description);
    List<Note> searchNotes(String keyword);
}





