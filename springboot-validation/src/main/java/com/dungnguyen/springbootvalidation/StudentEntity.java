package com.dungnguyen.springbootvalidation;


import lombok.Data;

@Data
public class StudentEntity {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}
