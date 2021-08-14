package com.mavro.dto;


import java.time.Instant;

public class AuthenticationResponse {

   private String authenticationToken;
   private String refreshToken;
   private String username;
   private Instant expiresAt;
   private String message;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String authenticationToken, String refreshToken, String username, Instant expiresAt) {
        this.authenticationToken = authenticationToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.expiresAt = expiresAt;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
