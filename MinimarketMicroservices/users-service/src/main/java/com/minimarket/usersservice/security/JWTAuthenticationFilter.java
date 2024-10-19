package com.minimarket.usersservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minimarket.usersservice.model.Auth;
import com.minimarket.usersservice.serviceImpl.UserDetailImplement;
import com.minimarket.usersservice.util.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        Auth authCredenciales;
        try {
            authCredenciales = new ObjectMapper().readValue(request.getReader(), Auth.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse authentication request body", e);
        }

        UsernamePasswordAuthenticationToken userPAT = new UsernamePasswordAuthenticationToken(
                authCredenciales.getEmail(),
                authCredenciales.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(userPAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailImplement userDetails = (UserDetailImplement) authResult.getPrincipal();
        String token = Token.crearToken(userDetails.getId(), userDetails.getName(), userDetails.getUsername(), userDetails.getRol());

        response.setContentType("application/json");
        response.addHeader("Authorization", "Bearer " + token);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(Collections.singletonMap("token", token));

        response.getWriter().write(jsonResponse);
    }
}
