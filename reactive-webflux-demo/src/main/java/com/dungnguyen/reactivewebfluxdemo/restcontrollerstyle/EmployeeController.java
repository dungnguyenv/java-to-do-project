package com.dungnguyen.reactivewebfluxdemo.restcontrollerstyle;

import com.dungnguyen.reactivewebfluxdemo.Employee;
import com.dungnguyen.reactivewebfluxdemo.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Mono<ServerResponse> getEmployeeById(@PathVariable Long id){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(employeeService.getEmployeeById(id));
    }


}
