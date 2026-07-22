/*package com.SpringProject.userManagement.kafka.consumer;

//public class NotificationConsumer {
    //package com.SpringProject.userManagement.kafka.consumer;

import com.SpringProject.userManagement.kafka.dto.NotificationMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

    @Service
    public class NotificationConsumer {

        @KafkaListener(
                topics = "daily-reminder",
                groupId = "notification-group"
        )
        public void consume(NotificationMessage message) {

            System.out.println("==================================");
            System.out.println("Notification Received");
            System.out.println("User Id : " + message.getUserId());
            System.out.println("Title : " + message.getTitle());
            System.out.println("Message : " + message.getMessage());
            System.out.println("Time : " + message.getCreatedAt());
            System.out.println("==================================");
        }
    }*/
package com.SpringProject.userManagement.kafka.consumer;

import com.SpringProject.userManagement.entity.Notification;
import com.SpringProject.userManagement.kafka.dto.NotificationMessage;
import com.SpringProject.userManagement.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    public NotificationConsumer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "daily-reminder", groupId = "notification-group")
    public void consume(NotificationMessage message) {

        System.out.println("Notification Received");
        System.out.println(message);

        Notification notification = new Notification();

        notification.setTitle(message.getTitle());
        notification.setMessage(message.getMessage());
        notification.setTime(message.getCreatedAt());
        notification.setReadStatus(false);

        notificationRepository.save(notification);

        System.out.println("Notification Saved To MySQL");
    }
}
