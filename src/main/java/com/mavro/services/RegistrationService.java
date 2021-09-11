package com.mavro.services;

import com.mavro.dto.AuthenticationResponse;
import com.mavro.dto.LoginRequest;
import com.mavro.dto.RefreshTokenRequest;
import com.mavro.dto.RegistrationRequest;
import com.mavro.entities.AppUser;
import com.mavro.exceptions.UserNotFoundException;
import com.mavro.jwtUtils.JwtProvider;
import com.mavro.repositories.RoleRepository;
import com.mavro.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Set;

@Service
public class RegistrationService {

  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;
  private final RefreshTokenService refreshTokenService;

    public RegistrationService(PasswordEncoder passwordEncoder,
                               RoleRepository roleRepository,
                               UserRepository userRepository,
                               AuthenticationManager authenticationManager,
                               JwtProvider jwtProvider,
                               RefreshTokenService refreshTokenService
                               ) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
    }

  public void register(RegistrationRequest request) {

      AppUser user = new AppUser();

      user.setUsername(request.getName());
      user.setPassword(passwordEncoder.encode(request.getPassword()));
      user.setRoles(Set.of(roleRepository.findRoleByName("ADMIN")));

      userRepository.save(user);
  }

    @Transactional
    public AppUser getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public AuthenticationResponse login(LoginRequest loginRequest){

      try {
          Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                  loginRequest.getPassword()));

          SecurityContextHolder.getContext().setAuthentication(authenticate);
          String token = jwtProvider.generateToken(authenticate);
          AuthenticationResponse response = new AuthenticationResponse();
          response.setAuthenticationToken(token);
          response.setRefreshToken(refreshTokenService.generateRefreshToken().getToken());
          response.setExpiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()));
          response.setUsername(loginRequest.getUsername());
          response.setMessage("Authentication success.");
          return response;
      }  catch (AuthenticationException e) {
          AuthenticationResponse response = new AuthenticationResponse();
          response.setMessage("Bad credentials.");
          return response;
      }
    }

//    public boolean isLoggedIn() {
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
//    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

      refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
      String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());

      AuthenticationResponse response = new AuthenticationResponse();
      response.setAuthenticationToken(token);
      response.setRefreshToken(refreshTokenRequest.getRefreshToken());
      response.setExpiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()));
      response.setUsername(refreshTokenRequest.getUsername());
      response.setMessage("Token refreshed");

      return response;
    }
}
