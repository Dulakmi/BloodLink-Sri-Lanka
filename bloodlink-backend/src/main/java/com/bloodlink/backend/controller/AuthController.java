package com.bloodlink.backend.controller;

import com.bloodlink.backend.dto.LoginRequest;
import com.bloodlink.backend.dto.LoginResponse;
import com.bloodlink.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        var loginResult = userService.login(loginRequest);

        if (loginResult.isPresent()) {
            return ResponseEntity.ok(loginResult.get());
        }

        return ResponseEntity
                .status(401)
                .body("Invalid email or password");
    }
}
