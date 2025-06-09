package com.ktun.inventory_management_system.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktun.inventory_management_system.exceptions.ExceptionMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
//        // Clear the invalid JWT token cookie
//        Cookie clearCookie = new Cookie("token", null);
//        clearCookie.setHttpOnly(true);
//        clearCookie.setPath("/"); // ensure path matches the original cookie
//        clearCookie.setMaxAge(0); // instantly expire the cookie
//        response.addCookie(clearCookie);
//
//        System.out.println("Invalid JWT token detected. Cookie cleared from CustomAccessDeniedHandler.");
//
//        System.out.println("Access Denied Log from CustomAccessDeniedHandler");
//        response.sendRedirect("/login");
        ExceptionMessage e = new ExceptionMessage("Exception: "+accessDeniedException.getMessage(),  false);
        ExceptionMessage cs = new ExceptionMessage("Cause: "+accessDeniedException.getCause().getMessage(), false);
        OutputStream out = response.getOutputStream();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, e);
        mapper.writeValue(out, cs);
        out.flush();
    }
}
