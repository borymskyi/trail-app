package com.borymskyi.trail.controller;

import com.borymskyi.trail.config.jwt.JwtUtils;
import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.pojo.UserResponse;
import com.borymskyi.trail.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * REST controller for {@link Profile} connected requests.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    private ProfileService profileService;
    private JwtUtils jwtUtils;

    @Autowired
    public ProfileController(ProfileService profileService, JwtUtils jwtUtils) {
        this.profileService = profileService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        UserResponse userResponse = UserResponse.buildUserResponse(
                profileService.getProfileByUsername(
                        jwtUtils.getUsernameByJwt(request.getHeader(AUTHORIZATION))
                )
        );

        return ResponseEntity.ok(userResponse);
    }
}
