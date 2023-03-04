package com.dungnguyen.elasticsearchdemo.controller;

import com.dungnguyen.elasticsearchdemo.entity.People;
import com.dungnguyen.elasticsearchdemo.service.PeopleElasticsearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class ElasticsearchController {
    private final PeopleElasticsearchService peopleElasticsearchService;

    @PostMapping("/insert")
    public void insert(@RequestBody People people) throws JsonProcessingException {
        peopleElasticsearchService.save(people);
    }

    @GetMapping("/find-all")
    public void findAll() throws IOException {
        peopleElasticsearchService.findAll();
    }
}
