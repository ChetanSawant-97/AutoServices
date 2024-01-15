package com.cmm.services.impl;

import com.cmm.dto.AuthRequestDTO;
import com.cmm.dto.JwtResponseDTO;
import com.cmm.entities.UserMaster;
import com.cmm.repositories.UserMasterRepo;
import com.cmm.services.AuthenticationService;
import com.cmm.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMasterRepo userMasterRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public JwtResponseDTO signinUser(AuthRequestDTO authRequestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        UserMaster user = userMasterRepo.findByEmail(authRequestDTO.getUsername());

        var jwt = jwtService.generateToken(user);

        var refreshedToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .userName(user.getEmail())
                .userFullName(user.getUserFullName())
                .accessToken(jwt).build();
        return jwtResponseDTO;
    }
}
