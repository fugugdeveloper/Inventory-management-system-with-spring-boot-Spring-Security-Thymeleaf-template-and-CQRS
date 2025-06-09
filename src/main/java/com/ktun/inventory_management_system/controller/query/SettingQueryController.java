package com.ktun.inventory_management_system.controller.query;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingQueryController {
    @GetMapping("/settings")
    public String getSettingsPage() {
        return "settings";
    }

}
