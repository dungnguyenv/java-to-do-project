package com.dungnguyen.jpaspecification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseInit {

    @Autowired
    private StudentRepo repo;

    @Autowired StudentService service;


    @Bean
    Object initializeDatabase(){
        repo.save(new Student(1, "Nguyen Van Dung", "Hanoi", 21, "12A1"));
        repo.save(new Student(2, "Hoang Van Dung", "Nghe An", 25, "12A2"));
        repo.save(new Student(3, "Nguyen Nam Dung", "Hochiminh", 24, "12B1"));
        repo.save(new Student(4, "Do Hoai Nam", "Hanoi", 21, "12A1"));
        repo.save(new Student(5, "Nguyen Tien Nam", "Hanoi", 21, "12A1"));
        repo.save(new Student(6, "Nguyen Trong Hoang", "Hanoi", 21, "12A1"));
        repo.save(new Student(7, "Nguyen Trong Doanh", "Hanoi", 21, "12A1"));


//        List<Student> students = service.findStudentByNameLike("Nguyen Van Dung");
//        System.out.println(students);
        return new Object();
    }
}
