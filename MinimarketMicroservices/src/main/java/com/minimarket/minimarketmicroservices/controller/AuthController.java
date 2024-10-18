package com.minimarket.minimarketmicroservices.controller;

import com.minimarket.minimarketmicroservices.dtos.AuthRequest;
import com.minimarket.minimarketmicroservices.dtos.AuthResponse;
import com.minimarket.minimarketmicroservices.dtos.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest auth) {
        return authService.login(auth);
    }
}

