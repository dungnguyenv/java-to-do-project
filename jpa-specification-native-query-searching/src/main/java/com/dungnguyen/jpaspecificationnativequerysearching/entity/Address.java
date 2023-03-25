package com.dungnguyen.jpaspecificationnativequerysearching.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity()
@Table(schema = "public")
public class Address {
    @Id
    private Long id;
    private String province;
    private String district;
}
