package com.borymskyi.trail.config.jwt;

import com.borymskyi.trail.pojo.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Authentication filter, checks and processes requests.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Component
@Slf4j
public class AuthenticationManagerFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthenticationManagerFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public Authentication authenticationAttempt(String username, String password) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    public JwtResponse handlingSuccessfulAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();

        String access_token = jwtUtils.generateJwt(userDetails);
        String refresh_token = jwtUtils.generateRefreshJwt(userDetails);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        return new JwtResponse(
                tokens,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getListRoles());
    }

}
