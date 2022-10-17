package com.planetx.auth.controller;

import com.planetx.auth.exceptions.AuthException;
import com.planetx.auth.model.Alien;
import com.planetx.auth.model.AuthRequest;
import com.planetx.auth.model.AuthResponse;
import com.planetx.auth.model.CreateAlienRequest;
import com.planetx.auth.service.alien.AlienService;
import com.planetx.auth.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AlienService alienService;

    @Autowired
    private AuthService authService;

    // POST /new-alien [CreateAlienRequest -> Alien]
    @PostMapping("/new-alien")
    public ResponseEntity<?> createAlien(@RequestBody CreateAlienRequest request){
        Alien alien = alienService.createAlien(request);
        return new ResponseEntity<>(alien, HttpStatus.CREATED);
    }

    // POST /authenticate [AuthRequest -> String]
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) throws AuthException {

        String alienId = request.getAlienId();
        String password = request.getPassword();

        String token = authService.authenticate(alienId, password);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    // GET /validate [_ -> Boolean]
    @GetMapping("/validate")
    public ResponseEntity<?> validate(){
        return ResponseEntity.ok(true);
    }
}
