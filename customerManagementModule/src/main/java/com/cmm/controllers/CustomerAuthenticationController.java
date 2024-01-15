package com.cmm.controllers;

import com.cmm.dto.AuthRequestDTO;
import com.cmm.dto.JwtResponseDTO;
import com.cmm.dto.RegisterUserDto;
import com.cmm.entities.UserMaster;
import com.cmm.entities.UserRole;
import com.cmm.repositories.UserMasterRepo;
import com.cmm.repositories.UserRoleRepo;
import com.cmm.services.impl.AuthenticationServiceImpl;
import com.cmm.services.impl.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class CustomerAuthenticationController {

    final UserMasterRepo userMasterRepo;

    final UserRoleRepo userRoleRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationServiceImpl authenticationService;


    @Autowired
    private JwtServiceImpl jwtService;
    @PostMapping("/register")
    public ResponseEntity<UserMaster> register(@RequestBody RegisterUserDto registerUserDto){

        UserRole userRole = userRoleRepo.findById(registerUserDto.getUserRoleId()<=0 ? 2:registerUserDto.getUserRoleId()).get();

        UserMaster userMasterToSave = UserMaster.builder()
                .firstName(registerUserDto.getFirstName())
                .lastName(registerUserDto.getLastName())
                .email(registerUserDto.getEmail())
                .phoneNumber(registerUserDto.getPhoneNumber())
                .userRole(userRole)
                .password(new BCryptPasswordEncoder().encode(registerUserDto.getPassword()))
                .build();
        return ResponseEntity.ok(userMasterRepo.save(userMasterToSave));
    }

    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO = authenticationService.signinUser(authRequestDTO);
        return jwtResponseDTO;
    }
}
