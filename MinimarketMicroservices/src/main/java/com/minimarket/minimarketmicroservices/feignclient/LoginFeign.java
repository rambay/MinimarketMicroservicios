package com.minimarket.minimarketmicroservices.feignclient;

import com.minimarket.minimarketmicroservices.dtos.AuthRequest;
import com.minimarket.minimarketmicroservices.dtos.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "login-service", url = "http://localhost:8092")
public interface LoginFeign {

    @PostMapping(value = "/login", consumes = "application/json")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest);
}
