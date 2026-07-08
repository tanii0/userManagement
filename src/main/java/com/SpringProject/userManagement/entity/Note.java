package com.SpringProject.userManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-note")
    private User user;
        public User getUser() {
        return user;
        }
        public void setUser(User user) {
            this.user = user;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category-note")
    private Category category;
        public Category getCategory() {
        return category;
        }
        public void setCategory(Category category) {
            this.category = category;
        }
}
