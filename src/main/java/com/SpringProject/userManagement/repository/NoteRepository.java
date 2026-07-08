package com.SpringProject.userManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SpringProject.userManagement.entity.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository  extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);
    List<Note> findByCategoryId(Long categoryId);


    List<Note> findByTitleContainingIgnoreCase(String title);
    List<Note> findByDescriptionContainingIgnoreCase(String description);





   @Query("SELECT n FROM Note n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(n.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Note> searchNotes(@Param("keyword") String keyword);
}
