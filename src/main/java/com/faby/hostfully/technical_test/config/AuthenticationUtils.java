package com.faby.hostfully.technical_test.config;

import com.faby.hostfully.technical_test.domain.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {

    private AuthenticationUtils() {
        //
    }

    public static User getAuthenticatedUser() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
