package com.cmm.services;

import com.cmm.entities.UserMaster;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(UserMaster userMaster);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(HashMap<String, Object> extraClaims, UserMaster user);
}
