package com.dungnguyen.kafkademo.controller;


import com.dungnguyen.kafkademo.SequenceGenerator;
import com.dungnguyen.kafkademo.kafka.KafkaProducer;
import com.dungnguyen.kafkademo.model.Classroom;
import com.dungnguyen.kafkademo.model.Student;
import com.dungnguyen.kafkademo.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/class-room")
public class ClassroomController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.kafka.topics.class-topic}")
    private String CLASS_ROOM_TOPIC;

    @Autowired private KafkaProducer kafkaProducer;

    @Autowired private SequenceGenerator sequenceGenerator;

    @GetMapping("/")
    public void demoKafka(){
        Classroom classroom = new Classroom(
                sequenceGenerator.nextId(),
                "class A",
                Collections.singletonList(new Student(sequenceGenerator.nextId(), "Nguyen Hoang Nam")),
                Collections.singletonList(new Teacher(sequenceGenerator.nextId(), "Nguyen Nam Anh"))
        );
        kafkaProducer.sendMessage(CLASS_ROOM_TOPIC, classroom);
    }

}
