package com.dungnguyen.jpaspecificationnativequerysearching.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "public")
public class Person {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
}
