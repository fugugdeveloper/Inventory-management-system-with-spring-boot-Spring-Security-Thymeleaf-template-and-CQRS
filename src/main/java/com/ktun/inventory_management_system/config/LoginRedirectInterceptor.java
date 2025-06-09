package com.ktun.inventory_management_system.config;

import com.ktun.inventory_management_system.helper.JwtUtil;
import com.ktun.inventory_management_system.service.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LoginRedirectInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public LoginRedirectInterceptor(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {

        String path = request.getRequestURI();
        if ("/login".equals(path)) {
            String token = null;
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
            if (token != null) {
                String username = jwtUtil.extractUsername(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    UserDetails userDetailsLogin = customUserDetailsService.loadUserByUsername(username);
                    System.out.println("from Login Interceptor username: " + userDetailsLogin.getUsername());
                    System.out.println("from Login Interceptor password: " + userDetailsLogin.getPassword());
                    if (jwtUtil.validateToken(token, userDetailsLogin)) {
                        response.sendRedirect("/");
                        return false; // Stop further processing
                    }
                }

            }
        }

        return true;
    }
}
