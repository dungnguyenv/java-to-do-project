package com.dungnguyen.jpaspecification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;


    @GetMapping("/name-like")
    List<Student> getStudentByNameLike(@RequestParam("name-like") String nameLike){
        System.out.println(nameLike);

        System.out.println(nameLike.equals("Dung"));
        List<Student>students =  service.findStudentByNameLike(new String(nameLike));
        System.out.println(students);
        return students;
    }

    @GetMapping("/")
    List<Student> getAll(){
        return service.findAll();
    }
}
