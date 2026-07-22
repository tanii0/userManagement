package com.SpringProject.userManagement.scheduler;

//public class ReminderScheduler {

    //package com.SpringProject.userManagement.scheduler;

import com.SpringProject.userManagement.kafka.dto.NotificationMessage;
import com.SpringProject.userManagement.kafka.producer.NotificationProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

    @Component
    public class ReminderScheduler {

        private final NotificationProducer producer;

        public ReminderScheduler(NotificationProducer producer) {
            this.producer = producer;
        }

       // @Scheduled(fixedRate = 30000)
        @Scheduled(cron = "0 0 9 * * ?")
        public void sendDailyReminder() {

            NotificationMessage message = new NotificationMessage(
                    1L,
                    "Daily Reminder",
                    "Don't forget to add today's notes.",
                    LocalDateTime.now()
            );

            producer.sendNotification(message);

            System.out.println("Scheduler Sent Notification");
        }
    }

