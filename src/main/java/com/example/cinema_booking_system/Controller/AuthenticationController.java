package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.DTO.AuthenticationRequest;
import com.example.cinema_booking_system.DTO.AuthenticationResponse;
import com.example.cinema_booking_system.DTO.RegisterRequest;
import com.example.cinema_booking_system.DTO.TokenRefreshRequest;
import com.example.cinema_booking_system.Services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    @CrossOrigin
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
