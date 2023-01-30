package com.dungnguyen.jpaspecification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long>,
        JpaSpecificationExecutor<Student> {

//    List<Student> findAll(Specification<Student> spec);

    List<Student> findByNameLike(String name);

}

