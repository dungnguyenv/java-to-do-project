package com.dungnguyen.jpaspecificationnativequerysearching.repository;

import com.dungnguyen.jpaspecificationnativequerysearching.entity.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>,
        JpaSpecificationExecutor<Person> {

    @Query(value = "select * from public.person where age > 12 ", nativeQuery = true)
    List<Person> searchAllBySpec(Specification<Person> spec);
}
