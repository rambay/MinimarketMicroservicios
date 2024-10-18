package com.minimarket.minimarketmicroservices.dtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:8092/login";

    public AuthResponse login(AuthRequest auth) {
        return restTemplate.postForObject(USER_SERVICE_URL, auth, AuthResponse.class);
    }
}
