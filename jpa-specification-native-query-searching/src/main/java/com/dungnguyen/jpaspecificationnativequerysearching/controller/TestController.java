package com.dungnguyen.jpaspecificationnativequerysearching.controller;

import com.dungnguyen.jpaspecificationnativequerysearching.dto.request.PeopleSearchingRequestDTO;
import com.dungnguyen.jpaspecificationnativequerysearching.entity.Person;
import com.dungnguyen.jpaspecificationnativequerysearching.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final PersonService personService;

    @GetMapping("/person/search")
    public List<Person> searchPeople(@RequestBody PeopleSearchingRequestDTO requestDTO){
        return personService.searchAllBySpec(requestDTO);
    }
}
