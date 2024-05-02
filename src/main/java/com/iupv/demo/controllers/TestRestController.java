package com.iupv.demo.controllers;

import com.iupv.demo.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {
    @GetMapping
    public User test() {
        return User.builder().email("phamdangkhoa1005@gmail.com").name("phamdangkhoa1005").build();
    }
}
