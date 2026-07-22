package com.SpringProject.userManagement.config;

//public class KafkaConfig {
  //  package com.SpringProject.userManagement.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class KafkaConfig {

        @Bean
        public NewTopic notificationTopic() {
            return new NewTopic("daily-reminder", 1, (short) 1);
        }
    }

