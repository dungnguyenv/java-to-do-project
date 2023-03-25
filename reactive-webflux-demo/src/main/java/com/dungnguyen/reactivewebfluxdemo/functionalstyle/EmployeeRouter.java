package com.dungnguyen.reactivewebfluxdemo.functionalstyle;

import com.dungnguyen.reactivewebfluxdemo.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class EmployeeRouter {

  @Bean
  public RouterFunction<ServerResponse> routerFunction(EmployeeService employeeService) {
    return RouterFunctions.route(
            GET("/employee/{id}").and(accept(MediaType.APPLICATION_JSON)),
            request -> {
              Long id = Long.parseLong(request.pathVariable("id"));
              return ServerResponse.ok()
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(employeeService.getEmployeeById(id));
            })
        .andRoute(
            GET("/employee/get-all").and(accept(MediaType.APPLICATION_JSON)),
            request -> {
              return ServerResponse.ok()
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(employeeService.getAllEmployee());
            });
  }

  public static void main(String[] args) {
    System.out.println();
  }
}
