package com.example.jwt.contoller;

import com.example.jwt.secure.JwtRequest;
import com.example.jwt.secure.JwtResponse;
import com.example.jwt.secure.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
//@RequestMapping("/jwt")
@CrossOrigin
public class JwtAuthenticationContoller {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil util;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authentication")
    public ResponseEntity<?> generateAuthnticationToken(@RequestBody JwtRequest authenticationRequest){
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = util.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username, String password){
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch(DisabledException | BadCredentialsException e){
            e.printStackTrace();
        }
    }
}
