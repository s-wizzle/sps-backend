package com.wizzle.sps_backend.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SpsGameServiceImpl implements SpsGameService {

    private static final String[] CHOICES = {"rock", "paper", "scissors"};
    private final Random random = new Random();

    @Override
    public String getNpcChoice() {
        int index = random.nextInt(CHOICES.length);
        return CHOICES[index];
    }
}
