package com.SpringProject.userManagement.controller;

import com.SpringProject.userManagement.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.SpringProject.userManagement.entity.Note;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notes")

public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.createNote(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }
    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "Note with id " + id + " has been deleted.";
    }
    @GetMapping("/user/{userId}")
    public List<Note> getNotesByUserId(@PathVariable Long userId) {
        return noteService.getNoteByUserId(userId);
    }
    @PostMapping("/category/{categoryId}")
    public Note createNoteUnderCategory(@PathVariable Long categoryId, @RequestBody Note note) {
      //  Note saved = noteService.createNoteUnderCategory(categoryId, note);
        //return new ResponseEntity<>(saved, HttpStatus.CREATED);
        return noteService.createNoteUnderCategory(categoryId, note);
    }
    @GetMapping("/category/{categoryId}")
    public List<Note> getNotesByCategoryId(@PathVariable Long categoryId) {
        return noteService.getNotesByCategoryId(categoryId);
    }
    @GetMapping("/search/title")
    public List<Note> searchByTitle(@RequestParam String title) {
        return noteService.searchByTitle(title);
    }
    @GetMapping("/search/description")
    public List<Note> searchByDescription(@RequestParam String description) {
        return noteService.searchByDescription(description);
    }
    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam String keyword) {
        return noteService.searchNotes(keyword);
    }
}
