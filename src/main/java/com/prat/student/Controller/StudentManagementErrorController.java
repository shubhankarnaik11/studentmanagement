package com.prat.student.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.servlet.error.ErrorController;
@RestController
public class StudentManagementErrorController implements ErrorController {
    @RequestMapping("/error")
    public String errorHandler() {
        return "Incorrect url, please enter a valid url" ;
    }
}
