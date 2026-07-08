package com.SpringProject.userManagement.service.Impl;

import com.SpringProject.userManagement.entity.Category;
import com.SpringProject.userManagement.exception.ResourceNotFoundException;
import com.SpringProject.userManagement.repository.CategoryRepository;
import com.SpringProject.userManagement.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SpringProject.userManagement.repository.NoteRepository;
import com.SpringProject.userManagement.entity.Note;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Note createNoteUnderCategory(Long categoryId, Note note) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        note.setCategory(category);
        return noteRepository.save(note);
    }
    @Override
    public List<Note> getNotesByCategoryId(Long categoryId) {
        return noteRepository.findByCategoryId(categoryId);
    }


    @Autowired
    private NoteRepository noteRepository;


    @Override
    public Note createNote(Note note) {
        return null;
    }

    @Override
    public List<Note> getAllNotes() {
        return null;
    }

    @Override
    public Note getNoteById(Long id) {
        return null;
    }

    @Override
    public Note updateNote(Long id, Note note) {
        return null;
    }

    @Override
    public void deleteNote(Long id) {

    }
    @Override
    public List<Note> getNoteByUserId(Long userId) {
        return null;
    }
    @Override
    public List<Note> searchByTitle(String title) {
        return noteRepository.findByTitleContainingIgnoreCase(title);
    }
    @Override
    public List<Note> searchByDescription(String description) {
        return noteRepository.findByDescriptionContainingIgnoreCase(description);
    }
    @Override
    public List<Note> searchNotes(String keyword) {
        return noteRepository.searchNotes(keyword);
  }
}
