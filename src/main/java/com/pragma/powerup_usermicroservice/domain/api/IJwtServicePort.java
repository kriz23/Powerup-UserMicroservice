package com.pragma.powerup_usermicroservice.domain.api;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJwtServicePort {
    <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver);
    
    Claims getAllClaimsFromToken(String token);
    
    Key getSignInKey();
    
    String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration);
    
    String generateToken(UserDetails userDetails);
    
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    
    boolean isTokenValid(String token, UserDetails userDetails);
    
    boolean isTokenExpired(String token);
    
    Date extractExpirationFromToken(String token);
    
    String getMailFromToken(String token);
    
    String getTokenFromHeader(String authHeader);
}
