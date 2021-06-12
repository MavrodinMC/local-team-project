package com.mavro.controllers;

import com.mavro.dto.AuthenticationResponse;
import com.mavro.dto.LoginRequest;
import com.mavro.dto.RefreshTokenRequest;
import com.mavro.dto.RegistrationRequest;
import com.mavro.services.RefreshTokenService;
import com.mavro.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {

    private final RegistrationService service;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public RegisterController(RegistrationService service,
                              RefreshTokenService refreshTokenService) {
        this.service = service;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {

        service.register(request);
        return new ResponseEntity<>("Registration success!", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshTokens(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return service.refreshToken(refreshTokenRequest);
    }

    @RequestMapping(value = "/logout", method = { RequestMethod.POST })
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!");
    }

}
