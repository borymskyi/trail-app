package com.borymskyi.trail.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.borymskyi.trail.service.impl.UserDetailImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${app.refreshJwtExpirationMs}")
    private int refreshJwtExpirationMs;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String generateJwt(UserDetailImpl userDetail) {
        return JWT.create()
                .withSubject(userDetail.getUsername())
                .withExpiresAt(new Date(new Date().getTime() + jwtExpirationMs))
                .withClaim("roles", userDetail.getAuthorities()
                                .stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(jwtSecret.getBytes()));
    }

    public String generateRefreshJwt(UserDetailImpl userDetail) {
        return JWT.create()
                .withSubject(userDetail.getUsername())
                .withExpiresAt(new Date(new Date().getTime() + refreshJwtExpirationMs))
                .sign(Algorithm.HMAC256(jwtSecret.getBytes()));
    }

    public DecodedJWT decodeToken(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(jwt);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Jwt validate error");
        }
    }

    public String parseJwt(String authorizationHeader) {
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring("Bearer ".length());
        }
        return null;
    }
}
