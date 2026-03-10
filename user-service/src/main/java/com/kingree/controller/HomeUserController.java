package com.kingree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeUserController {

    @GetMapping
    public String HomeControllerHandler () {
        return "⭐ User Service is running";
    }
}
