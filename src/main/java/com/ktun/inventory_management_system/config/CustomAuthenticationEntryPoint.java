package com.ktun.inventory_management_system.config;

import com.ktun.inventory_management_system.exceptions.InvalidTokenException;
import com.ktun.inventory_management_system.exceptions.TokenExpiredException;
import com.ktun.inventory_management_system.handler.CustomLogoutHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        System.out.println("Exception: " + authException.getMessage());

        // Only clear the token cookie if it's a token-related error
        if (authException instanceof TokenExpiredException || authException instanceof InvalidTokenException) {
            customLogoutHandler.logout(request, response, null);
        }

        // Redirect or send error
        response.sendRedirect("/login");
    }
}
