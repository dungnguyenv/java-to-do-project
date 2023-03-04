package com.dungnguyen.springbootvalidation;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {

    @PostMapping("/add")
    public ResponseEntity<StudentDTO> addNewStudent(@Valid @RequestBody StudentDTO dto){

        System.out.println(dto);

        return ResponseEntity.ok(null);
    }
}
