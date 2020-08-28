package com.volkov.userregsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserregsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserregsystemApplication.class, args);
    }

    @RequestMapping("/hello")
    public String greeting() {
        return "Hello World!";
    }
}
