package com.borymskyi.trail.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    public DecodedJWT decodeToken(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("awd".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        String requestUrl = request.getServletPath();
        if (requestUrl.equals("/api/v1/sign-in") || requestUrl.equals("/api/v1/token/refresh")) {
            filterChain.doFilter(request, response);

        } else {

            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    DecodedJWT decodedJWT = decodeToken(authorizationHeader);

                    UsernamePasswordAuthenticationToken authenticationToken = authenticationTokenForToSetting(decodedJWT);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                    log.info("Profile with username {} authorized successfully", decodedJWT.getSubject());

                } catch (Exception exception) {
                    log.error("Error logging in: {}", exception.getMessage());

                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request, response);
            }

        }
    }

    private UsernamePasswordAuthenticationToken authenticationTokenForToSetting(DecodedJWT decodedJWT) {
        String username = decodedJWT.getSubject();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

}
