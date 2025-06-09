package com.ktun.inventory_management_system.controller.command;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportQueryController {
    @GetMapping("/reports")
    public String reportsPage() {
        return "reports";
    }

}
