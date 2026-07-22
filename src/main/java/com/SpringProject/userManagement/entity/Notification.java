package com.SpringProject.userManagement.entity;

//public class Notification {
   // package com.SpringProject.userManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "notifications")
    public class Notification {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private String message;

        private LocalDateTime time;

        private boolean readStatus;

        public Notification() {
        }

        public Notification(Long id, String title, String message, LocalDateTime time, boolean readStatus) {
            this.id = id;
            this.title = title;
            this.message = message;
            this.time = time;
            this.readStatus = readStatus;
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

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }

        public boolean isReadStatus() {
            return readStatus;
        }

        public void setReadStatus(boolean readStatus) {
            this.readStatus = readStatus;
        }
    }

