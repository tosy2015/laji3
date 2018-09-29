package com.tosylab.tosylabbot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloC {
    @RequestMapping("/")
    public String index() {


        return "OK";
    }
}
