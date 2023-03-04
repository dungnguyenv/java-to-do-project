package com.dungnguyen.kafkademo.kafka;

import com.dungnguyen.kafkademo.model.Classroom;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(id = "consumer-01", topics = "${spring.kafka.topics.class-topic}")
    public void consumeClassRoomTopic(String message) {
        try {
            logger.info("CLASS_ROOM_CONSUMER: " + message);
            Classroom classroom = objectMapper.readValue(message, Classroom.class);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
