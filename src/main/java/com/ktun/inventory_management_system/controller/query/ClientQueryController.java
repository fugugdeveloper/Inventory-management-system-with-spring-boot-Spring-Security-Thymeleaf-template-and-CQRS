package com.ktun.inventory_management_system.controller.query;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientQueryController {

    @GetMapping("/add-client")
    public String addClientPage() {
        return "add-client";
    }

    @GetMapping("/client-list")
    public String clientListPage() {
        return "client-list";
    }

}

