package com.dungnguyen.jpaspecificationnativequerysearching.service;

import com.dungnguyen.jpaspecificationnativequerysearching.dto.request.PeopleSearchingRequestDTO;
import com.dungnguyen.jpaspecificationnativequerysearching.entity.Person;
import com.dungnguyen.jpaspecificationnativequerysearching.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> searchAllBySpec(PeopleSearchingRequestDTO requestDTO){
        Specification<Person> spec = createPeopleSearchingSpec(requestDTO);
//        return personRepository.searchAllBySpec(spec);
        return personRepository.findAll(spec);
    }

    private Specification<Person> createPeopleSearchingSpec(PeopleSearchingRequestDTO requestDTO){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + requestDTO.getName() + "%");
        });
    }
}
