package com.kingree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homePaymentHandler() {
        return "⭐ Payment Service is running";
    }
}
