package com.faby.hostfully.technical_test.services;

import com.faby.hostfully.technical_test.domain.dtos.requests.AuthenticateRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuthService {

    private final JwtEncoder jwtEncoder;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    public AuthService(JwtEncoder jwtEncoder, DaoAuthenticationProvider daoAuthenticationProvider) {
        this.jwtEncoder = jwtEncoder;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    public String authenticate(AuthenticateRequest authenticateRequest) {
        Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("Hostfully-Technical-Test")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
