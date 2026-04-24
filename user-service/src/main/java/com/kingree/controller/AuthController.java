package com.kingree.controller;

import com.kingree.payload.dto.LoginDTO;
import com.kingree.payload.dto.SignupDTO;
import com.kingree.payload.response.AuthResponse;
import com.kingree.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupDTO req) throws Exception {
        AuthResponse authResponse = authService.signup(req);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO req) throws Exception {
        AuthResponse authResponse = authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/access-token/refresh-token/{refreshToken}")
    public ResponseEntity<AuthResponse> getAccessToken(@PathVariable("refreshToken") String refreshToken) throws Exception {
        AuthResponse authResponse = authService.getAccessTokenFromRefreshToken(refreshToken);
        return ResponseEntity.ok(authResponse);
    }

}
