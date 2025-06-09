package com.ktun.inventory_management_system.controller.query;

import com.ktun.inventory_management_system.model.UserForm;
import com.ktun.inventory_management_system.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserQueryController {
    @Autowired
    private UserQueryService userQueryService;
    @GetMapping("/adduser")
    public String addUserQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "adduser";
    }
    @GetMapping("/edituser/{id}")
    public String editUserQuery(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") String userId, Model model) {
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role",userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "edituser";
    }
    @GetMapping("/userlist")
    public String userlistQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("users", userQueryService.getAllUsers());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "userlist";
    }
}
