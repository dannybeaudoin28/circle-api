package com.beaudoin.circleapi.service;

import org.springframework.stereotype.Service;

import com.beaudoin.circleapi.data.model.User;
import com.beaudoin.circleapi.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUserEmail()) // using email as username
            .password(user.getUserPassword()) // You might need to encode the password here
            .roles(user.getUserRole()) // Assuming roles are stored as strings in the User entity
            .authorities(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()))
            .build();
    }

}
