package com.dungnguyen.kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    private Long id;

    private String name;

    private List<Student> students;

    private List<Teacher> teachers;
}
