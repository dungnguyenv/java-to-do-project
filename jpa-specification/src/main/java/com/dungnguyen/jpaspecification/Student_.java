package com.dungnguyen.jpaspecification;


import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {


    public static volatile SingularAttribute<Student, String> address;
    public static volatile SingularAttribute<Student, String> name;
    public static volatile SingularAttribute<Student, String> id;
    public static final String ADDRESS = "address";
    public static final String NAME = "name";
    public static final String ID = "id";

}
