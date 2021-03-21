package com.ece356.demographics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;


@RestController
public class TestController {
    @RequestMapping("/test")
    public String index() {
        return "yah, this is about demographics mate";
    }
}

