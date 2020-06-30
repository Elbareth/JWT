package com.example.jwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ta metoda zostanie wywołana by załadować informacje o użytkowniach z bazy danych
        if("Goldek".equals(username)){
            return new User("Goldek", "$2y$12$3Y0bnZd5tmuyyhjqDtjrpOFRZTkzm/5r.pvftgqVovOOcbs/L9gc2", new ArrayList<>()); // haslo shaszowego BCrypt
        }
        else{
            throw new UsernameNotFoundException("Nie moge znaleźć Goldka!");
        }
    }
}
