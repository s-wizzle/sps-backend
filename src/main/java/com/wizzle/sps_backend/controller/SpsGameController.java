package com.wizzle.sps_backend.controller;

import com.wizzle.sps_backend.service.SpsGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sps-game")
public class SpsGameController {

    SpsGameService spsGameService;

    @Autowired
    public SpsGameController(SpsGameService spsGameService) {
        this.spsGameService = spsGameService;
    }

    @GetMapping
    public String getNpcChoice() {
        return spsGameService.getNpcChoice();
    }
}
