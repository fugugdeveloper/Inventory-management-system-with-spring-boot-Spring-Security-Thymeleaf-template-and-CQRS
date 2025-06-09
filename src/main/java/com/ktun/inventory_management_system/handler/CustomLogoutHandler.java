package com.ktun.inventory_management_system.handler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        // Our custom logout logic here
        System.out.println("CustomLogoutHandler invoked");
        Cookie clearCookie = new Cookie("token", null);
        clearCookie.setHttpOnly(true);
        clearCookie.setPath("/");
        clearCookie.setMaxAge(0); // instantly expire the cookie
        System.out.println("Token Expired . Cookie cleared from CustomLogoutHandler.");
        response.addCookie(clearCookie);
    }
}
