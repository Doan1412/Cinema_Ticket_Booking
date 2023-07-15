package com.example.cinema_booking_system.Services;

import com.example.cinema_booking_system.Configuration.JwtService;
import com.example.cinema_booking_system.DTO.AuthenticationRequest;
import com.example.cinema_booking_system.DTO.AuthenticationResponse;
import com.example.cinema_booking_system.DTO.RegisterRequest;
import com.example.cinema_booking_system.DataType.AccountStatus;
import com.example.cinema_booking_system.DataType.Role;
import com.example.cinema_booking_system.Model.*;
import com.example.cinema_booking_system.Repositories.AccountRepository;
import com.example.cinema_booking_system.Repositories.CustomerRepository;
import com.example.cinema_booking_system.Repositories.PersonRepository;
import com.example.cinema_booking_system.Repositories.TokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var person = Person.builder()
                .email(request.getEmail())
                .phone(request.getPhone())
                .name(request.getName())
                .build();
        Person p = personRepository.save(person);
        var user = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .person(p)
                .role(request.getRole())
                .status(AccountStatus.ACTIVE)
                .build();
        var savedUser = accountRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        if (savedUser.getRole()== Role.CUSTOMER){
            var c = Customer.builder()
                    .account(savedUser)
                    .bookings(new ArrayList<Booking>())
                    .build();
            var saveCustomer = customerRepository.save(c);
        }
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .name(user.getPerson().getName())
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = accountRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .name(user.getPerson().getName())
                .role(user.getRole())
                .build();
    }

    private void saveUserToken(Account user, String jwtToken) {
        var token = Token.builder()
                .account(user)
                .token(jwtToken)
                .tokenType("BEARER")
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Account user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.accountRepository.findByUsername(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .name(user.getPerson().getName())
                        .role(user.getRole())
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
