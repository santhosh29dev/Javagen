package com.demo2.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // TODO: Inject UserService

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // TODO: Implement login logic
        return "token-placeholder";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        // TODO: Implement registration logic
        return "registered";
    }

    public record LoginRequest(String username, String password) {}
    public record RegisterRequest(String username, String password, String email) {}
}
