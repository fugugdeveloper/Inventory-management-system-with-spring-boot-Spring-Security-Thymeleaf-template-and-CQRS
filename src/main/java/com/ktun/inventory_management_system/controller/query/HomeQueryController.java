package com.ktun.inventory_management_system.controller.query;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeQueryController {

    @GetMapping("/")
    public String getIndexPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            System.out.println("userDetail is null in index page");
            return "redirect:/login"; // fallback, shouldn't occur if security config is correct
        }
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "index";
    }

}
