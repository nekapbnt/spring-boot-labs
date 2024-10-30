package ru.nabiev.SpringBootWebSecurity.service;

import ru.nabiev.SpringBootWebSecurity.dto.UserDto;
import ru.nabiev.SpringBootWebSecurity.entity.User;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nabiev.SpringBootWebSecurity.entity.User;
import ru.nabiev.SpringBootWebSecurity.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailService implements UserDetailsService {

    private UserRepository userRepository;


    public CustomUsersDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(usernameOrEmail);
        if (user != null) {
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                    .map((role) -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities
            );
        } else {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

    }
}