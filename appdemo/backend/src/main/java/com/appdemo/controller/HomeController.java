package com.appdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/health")
    public String health() {
        return "appdemo API is running";
    }

    // TODO: Add endpoints
}
