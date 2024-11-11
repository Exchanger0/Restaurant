package com.restaurant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @GetMapping
    public String home() {
        LOGGER.info("Received a GET request to url: /home");
        return "home/index";
    }
}
