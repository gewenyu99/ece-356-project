package com.ece356.demographics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {
    @RequestMapping("/sup")
    public String index() {
        return "yah, this is about demographics mate";
    }
}
