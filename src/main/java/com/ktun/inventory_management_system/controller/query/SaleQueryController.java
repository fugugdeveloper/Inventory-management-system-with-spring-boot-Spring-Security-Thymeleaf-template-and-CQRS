package com.ktun.inventory_management_system.controller.query;

import com.ktun.inventory_management_system.model.Sale;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaleQueryController {
    @GetMapping("/add-sales")
    public String addSalesPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Sale sale = new Sale();
        model.addAttribute("sale", sale);
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "add-sales";
    }
}
