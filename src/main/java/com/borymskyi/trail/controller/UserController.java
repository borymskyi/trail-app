package com.borymskyi.trail.controller;

import com.borymskyi.trail.config.jwt.JwtUtils;
import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.pojo.UserPojo;
import com.borymskyi.trail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * REST controller for {@link Users} connected requests.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@RestController
@RequestMapping("api/v1/profile")
public class UserController {

    private UserService profileService;
    private JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService profileService, JwtUtils jwtUtils) {
        this.profileService = profileService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        UserPojo userResponse = UserPojo.buildUserResponse(
                profileService.getUserByUsername(
                        jwtUtils.getUsernameByJwt(request.getHeader(AUTHORIZATION))
                )
        );

        return ResponseEntity.ok(userResponse);
    }
}
