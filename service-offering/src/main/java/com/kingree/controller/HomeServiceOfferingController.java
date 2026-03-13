package com.kingree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeServiceOfferingController {

    @GetMapping
    public String HomeControllerHandler() {
        return "⭐ Service Offering is running";
    }
}
