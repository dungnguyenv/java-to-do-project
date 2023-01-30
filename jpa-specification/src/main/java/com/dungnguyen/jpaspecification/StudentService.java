package com.dungnguyen.jpaspecification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;


    List<Student> findStudentByNameLike(String name){
        System.out.println(name);
//        return repo.findByNameLike("%"+name+"%");
        return repo.findAll(nameLike(name));
    }


    List<Student> findAll(){
        return repo.findAll();
    }

    private Specification<Student> nameLike(String name){
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(Student_.NAME), "%"+name+"%");
            }
        };
    }

}
