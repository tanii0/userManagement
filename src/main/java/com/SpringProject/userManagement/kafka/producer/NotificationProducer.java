package com.SpringProject.userManagement.kafka.producer;


import com.SpringProject.userManagement.kafka.dto.NotificationMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

    @Service
    public class NotificationProducer {

        private final KafkaTemplate<String, Object> kafkaTemplate;

        public NotificationProducer(KafkaTemplate<String, Object> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        public void sendNotification(NotificationMessage message) {

            kafkaTemplate.send("daily-reminder", message);

            System.out.println("Notification Sent To Kafka");
        }
    }

