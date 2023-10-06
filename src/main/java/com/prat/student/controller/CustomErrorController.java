package com.prat.student.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Return the name of your custom error page (e.g., "error.html")
        return "error";
    }

}