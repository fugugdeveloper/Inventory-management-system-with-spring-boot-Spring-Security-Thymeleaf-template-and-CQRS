package com.ktun.inventory_management_system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public RedirectView handleIllegalState(IllegalStateException ex) {
        if ("UNAUTHORIZED_REDIRECT".equals(ex.getMessage()) || "FORBIDDEN_REDIRECT".equals(ex.getMessage())) {

            return new RedirectView("/login");
        }
        return new RedirectView("/error"); // or another generic error page
    }
    @ExceptionHandler(ResourceNotFoundException.class)  // these exception is working from created class
    public ResponseEntity<ExceptionMessage> resourceNotFoundExceptionHandler()
    {
        String message = "User not found";
        ExceptionMessage em = new ExceptionMessage(message,false);

        System.out.println(em);
        return new ResponseEntity<ExceptionMessage>(em, HttpStatus.NOT_FOUND);

    }
}
