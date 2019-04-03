package com.wan.controller;

import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class demo {

    @GetMapping("/test")
    public String test1(){
        return "success";
    }

}
