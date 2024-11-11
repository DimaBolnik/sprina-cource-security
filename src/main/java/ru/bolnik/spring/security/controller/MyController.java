package ru.bolnik.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MyController {


    @GetMapping
    public String getInfoForAllEmps() {
        return "view_all_employees";
    }

    @GetMapping("/hr_info")
    public String getInfoForHr() {
        return "view_for_hr";
    }

    @GetMapping("/manager_info")
    public String getInfoForManager() {
        return "view_for_managers";
    }
}
