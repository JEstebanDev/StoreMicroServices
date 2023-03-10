package com.jestebandev.NotificationService;

import com.jestebandev.NotificationService.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "NotificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent){
        //send out an email notification is possible
        log.info("Received Kafka NotificationTopic - {}",orderPlacedEvent.getOrderNumber());
    }
}
