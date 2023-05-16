package com.danny;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/greet")
    public GreetResponse greet(){
        List<String> favProgrammingLanguages =  List.of("Java", "PHP", "Javascript");
        Person alex= new Person("Alex", 20, 30_000);

        return new GreetResponse("Hello", favProgrammingLanguages, alex);
    }

    record Person(String name, int age, double savings){}

    record GreetResponse(String greet, List<String> favProgrammingLanguages, Person person){}
}
