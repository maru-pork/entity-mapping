package com.example.entitymapping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/api")
    public @ResponseBody String greeting(){
        return "Hello API!";
    }
}
