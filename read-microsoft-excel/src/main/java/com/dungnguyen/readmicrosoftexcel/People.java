package com.dungnguyen.readmicrosoftexcel;

import lombok.Data;

@Data
public class People {
    private Long id;
    private String name;

    private Integer age;
    private String address;
    private Degree degree;
}
