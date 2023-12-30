package com.faby.hostfully.technical_test.services;

import com.faby.hostfully.technical_test.domain.dtos.requests.RegisterRequest;
import com.faby.hostfully.technical_test.domain.model.User;
import com.faby.hostfully.technical_test.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;


@Service
@Transactional(readOnly = true)
public class UserService extends CommonRepositoryService<User, UserRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(RegisterRequest registerRequest) {
        return repository.save(new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword())));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("username {0} not found", username)
                ));
    }
}
