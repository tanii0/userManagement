package com.SpringProject.userManagement.controller;

//public class NotificationTestController {
  //  package com.SpringProject.userManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SpringProject.userManagement.kafka.dto.NotificationMessage;
import com.SpringProject.userManagement.kafka.producer.NotificationProducer;

import java.time.LocalDateTime;

    @RestController
    public class NotificationTestController {

        private final NotificationProducer producer;

        public NotificationTestController(NotificationProducer producer) {
            this.producer = producer;
        }

        @GetMapping("/test/kafka")
        public String testKafka() {

            NotificationMessage message =
                    new NotificationMessage(
                            1L,
                            "Daily Reminder",
                            "Don't forget to add today's notes.",
                            LocalDateTime.now());

            producer.sendNotification(message);

            return "Notification Sent Successfully";
        }
    }

