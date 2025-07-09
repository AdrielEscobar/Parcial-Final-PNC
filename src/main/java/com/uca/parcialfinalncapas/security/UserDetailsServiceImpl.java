package com.uca.parcialfinalncapas.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBuilder userBuilder;

        if (username.equals("user")) {
            userBuilder = User.withUsername("user")
                    .password("{noop}user123") // {noop} = sin codificar
                    .roles("USER");
        } else if (username.equals("tech")) {
            userBuilder = User.withUsername("tech")
                    .password("{noop}tech123")
                    .roles("TECH");
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return userBuilder.build();
    }
}
