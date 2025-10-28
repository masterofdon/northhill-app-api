package com.northhill.api.sahibinden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.northhill.api.sahibinden.service.SahibindenPlaywrightService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sahibinden")
@RequiredArgsConstructor
public class SahibindenPlaywrightController {

    private final SahibindenPlaywrightService sahibindenPlaywrightService;

    @GetMapping("/price")
    public String getPrice(@RequestParam String url) {
        return sahibindenPlaywrightService.fetchPrice(url);
    }
}
