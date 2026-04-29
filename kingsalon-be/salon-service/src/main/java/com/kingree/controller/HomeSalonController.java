package com.kingree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeSalonController {

    @GetMapping
    public String HomeControllerHandler () {
        return "⭐ Salon Service is running";
    }
}
