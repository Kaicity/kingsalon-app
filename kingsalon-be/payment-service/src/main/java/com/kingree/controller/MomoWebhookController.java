package com.kingree.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.processor.impl.MomoProcessor;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments/momo")
@RequiredArgsConstructor
public class MomoWebhookController {
    private final MomoProcessor momoProcessor;

    @PostMapping("/ipn")
    public void handleMomoIPN(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        momoProcessor.handleIPN(json);
    }
}
