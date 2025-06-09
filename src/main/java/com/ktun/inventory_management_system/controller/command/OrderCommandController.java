package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderCommandController {
    @PostMapping("/order")
    public String createOrder(@ModelAttribute Order order, RedirectAttributes redirectAttributes) {
        // Save order logic here
        return "redirect:/order";
    }
}
