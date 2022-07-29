package com.borymskyi.trail.config.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Slf4j
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    public UsernamePasswordAuthenticationToken authenticationTokenForToSetting(DecodedJWT decodedJWT) {
        String username = decodedJWT.getSubject();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        String requestUrl = request.getServletPath();
        if (requestUrl.equals("/api/v1/sign-in") || requestUrl.equals("/api/v1/token/refresh")) {
            filterChain.doFilter(request, response);
        } else {

            String jwt = jwtUtils.parseJwt(request.getHeader(AUTHORIZATION));
            if (jwt != null) {
                try {
                    DecodedJWT decodedJWT = jwtUtils.decodeToken(jwt);

                    UsernamePasswordAuthenticationToken authenticationToken = authenticationTokenForToSetting(decodedJWT);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                    log.info("Profile with username {} authorized successfully", decodedJWT.getSubject());

                } catch (Exception e) {
                    log.error("Error logging in: {}", e.getMessage());

                    response.setHeader("error", e.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
