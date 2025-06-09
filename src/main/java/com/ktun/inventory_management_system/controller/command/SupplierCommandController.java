package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.model.Supplier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SupplierCommandController {
    @PostMapping("/supplier")
    public String createSupplier(@ModelAttribute Supplier supplier, RedirectAttributes redirectAttributes) {
        // Save supplier logic here
        return "redirect:/supplier";
    }


}
