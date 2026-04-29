package com.kingree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeBookingServiceController {

    @GetMapping
    public String HomeControllerHandler () {
        return "⭐ Booking Service is running";
    }
}
