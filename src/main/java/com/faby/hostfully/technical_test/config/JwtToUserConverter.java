package com.faby.hostfully.technical_test.config;

import com.faby.hostfully.technical_test.services.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    private final UserService userService;

    public JwtToUserConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        UserDetails user = userService.loadUserByUsername(jwt.getSubject());
        return new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
    }
}
