package com.borymskyi.trail.controller;

import com.borymskyi.trail.config.jwt.JwtUtils;
import com.borymskyi.trail.domain.Trail;
import com.borymskyi.trail.pojo.UserResponse;
import com.borymskyi.trail.service.ProfileService;
import com.borymskyi.trail.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * REST controller for {@link Trail} connected requests.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@RestController
@RequestMapping("api/v1/trail")
public class TrailController {

    private TrailService trailService;
    private ProfileService profileService;
    private JwtUtils jwtUtils;

    @Autowired
    public TrailController(TrailService trailService, ProfileService profileService, JwtUtils jwtUtils) {
        this.trailService = trailService;
        this.profileService = profileService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<Trail> createTrail(@RequestBody Trail trail, HttpServletRequest request) {
        UserResponse userResponse = UserResponse.buildUserResponse(
                profileService.getProfileByUsername(
                        jwtUtils.getUsernameByJwt(request.getHeader(HttpHeaders.AUTHORIZATION))
                )
        );

        return ResponseEntity.ok(trailService.createTrail(trail, userResponse.getId()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Trail> getTrail(@PathVariable("id") Long idTrail) {
        Trail trail = trailService.getTrail(idTrail);
        return ResponseEntity.ok(trail);
    }

    @PutMapping("{id}/edit")
    public ResponseEntity<Trail> editTrail(
            @RequestBody Trail trail,
            @PathVariable("id") Long idTrail
    ) {
        return ResponseEntity.ok(trailService.editTrail(trail, idTrail));
    }

    @PutMapping("{id}/update_date")
    public ResponseEntity<Trail> updateDateTrail(
            @PathVariable("id") Long idTrail
    ) {
        return ResponseEntity.ok(trailService.updateDateTrail(idTrail));
    }

    @DeleteMapping("{id}/delete")
    public void deleteTrail(@PathVariable("id") Long idTrail) {
        trailService.deleteTrail(idTrail);
    }
}
