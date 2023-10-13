package com.shopping.esoshop.ErrorController;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerEnrror implements ErrorController {
    
    @GetMapping(value = "/error")
    public String handerError() {
        return "/error";
    }

}
