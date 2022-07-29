package com.borymskyi.trail.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.borymskyi.trail.config.jwt.AuthenticationManagerFilter;
import com.borymskyi.trail.config.jwt.JwtAuthTokenFilter;
import com.borymskyi.trail.config.jwt.JwtUtils;
import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.pojo.JwtResponse;
import com.borymskyi.trail.pojo.LoginRequest;
import com.borymskyi.trail.service.ProfileService;
import com.borymskyi.trail.service.impl.UserDetailImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * REST controller for authentication requests (sign-up, sign-in, etc.)
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping("api/v1")
public class MainController {

    private ProfileService profileService;
    private AuthenticationManagerFilter authenticationManagerFilter;
    private JwtAuthTokenFilter jwtAuthTokenFilter;
    private JwtUtils jwtUtils;

    @Autowired
    public MainController(ProfileService profileService, AuthenticationManagerFilter authenticationManagerFilter, JwtAuthTokenFilter jwtAuthTokenFilter, JwtUtils jwtUtils) {
        this.profileService = profileService;
        this.authenticationManagerFilter = authenticationManagerFilter;
        this.jwtAuthTokenFilter = jwtAuthTokenFilter;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Profile> registration(@RequestBody Profile profile) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/sign-up").toUriString());

        return ResponseEntity.created(uri).body(profileService.createProfile(profile));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManagerFilter.authenticationAttempt(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        JwtResponse jwtResponse = authenticationManagerFilter.handlingSuccessfulAuthentication(authentication);

        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DecodedJWT decodedJWT = jwtAuthTokenFilter.refreshJwtFilter(request.getHeader(AUTHORIZATION));

        String username = decodedJWT.getSubject();
        UserDetailImpl userDetail = profileService.getUserDetail(username);

        String access_token = jwtUtils.generateJwt(userDetail);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", jwtUtils.parseJwt(request.getHeader(AUTHORIZATION)));

        return ResponseEntity.ok(new JwtResponse(
                tokens,
                userDetail.getId(),
                userDetail.getUsername(),
                userDetail.getListRoles()));
    }

    @GetMapping("/users")
    public ResponseEntity<List<Profile>> getAllUsers() {
        return ResponseEntity.ok().body(profileService.getAllUsers());
    }
}
