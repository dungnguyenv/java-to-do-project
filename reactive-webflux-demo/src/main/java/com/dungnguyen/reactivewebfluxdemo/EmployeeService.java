package com.dungnguyen.reactivewebfluxdemo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

  public Employee getEmployeeById(Long id) {
    return
        Employee.builder().id(id).name("Nguyen Hoang Nam").address("Hanoi").build();
  }

  public List<Employee> getAllEmployee() {

    return List.of(
        Employee.builder().id(1L).name("Nguyen Hoang Nam").address("Hanoi").build(),
        Employee.builder().id(2L).name("Nguyen Hoang Nam").address("Hanoi").build());
  }
}
