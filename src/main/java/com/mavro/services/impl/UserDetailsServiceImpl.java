package com.mavro.services.impl;

import com.mavro.entities.AppUser;
import com.mavro.exceptions.UserNotFoundException;
import com.mavro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = userRepository.findUserByUsername(username);

        AppUser user = userOptional
                .orElseThrow(() -> new UserNotFoundException("No user " +
                        "found with username : " + username));

        return new MyAppUserDetails(user);
    }
}
