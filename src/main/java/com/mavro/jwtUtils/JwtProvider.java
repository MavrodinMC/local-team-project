package com.mavro.jwtUtils;

import com.mavro.exceptions.ProjectException;
import com.mavro.services.impl.MyAppUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/mvr.jks");
            keyStore.load(resourceAsStream, "mvr123".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e){
            throw new ProjectException("Exception occurred while loading keystore");
        }
    }

    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        MyAppUserDetails principal = (MyAppUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("role", authorities)
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public String generateTokenWithUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(java.util.Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }


    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("mvr", "mvr123".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e){
            throw new ProjectException("Exception occurred while retrieving public key from keystore");
        }
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
            return true;
        } catch (ExpiredJwtException e) {
            e.getMessage();
        }
        return false;
    }

    private PublicKey getPublicKey() {

        try {
            return keyStore.getCertificate("mvr").getPublicKey();
        } catch (KeyStoreException e) {
            throw new ProjectException("Exception occurred while retrieving public key from keystore");
        }
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
