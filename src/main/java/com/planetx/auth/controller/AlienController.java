package com.planetx.auth.controller;

import com.planetx.auth.exceptions.AlienNotFoundException;
import com.planetx.auth.model.Alien;
import com.planetx.auth.model.AlienUpdateRequest;
import com.planetx.auth.repository.AlienRepository;
import com.planetx.auth.service.alien.AlienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlienController {
    @Autowired
    private AlienService alienService;

    @Autowired
    private AlienRepository alienRepository;

    @PostMapping("/update-alien")
    public Alien changePassword(@RequestBody AlienUpdateRequest request) throws AlienNotFoundException {
        return alienService.updateAlien(request);
    }
}
