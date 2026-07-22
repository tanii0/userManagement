package com.SpringProject.userManagement.service;

import com.SpringProject.userManagement.entity.Notification;
import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Notification markAsRead(Long id);
    void deleteNotification(Long id);
}
