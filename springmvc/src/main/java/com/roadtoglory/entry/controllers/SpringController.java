package com.roadtoglory.entry.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SpringController {
    Logger logger = LoggerFactory.getLogger(SpringController.class);

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String helloWorld(){
        logger.warn("In hello world!");
        System.out.println("inside the hello world!");
        return "Hello";
    }
}
