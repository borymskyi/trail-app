package com.borymskyi.trail.controller;

import com.borymskyi.trail.config.jwt.JwtUtils;
import com.borymskyi.trail.domain.Trails;
import com.borymskyi.trail.pojo.MessageResponse;
import com.borymskyi.trail.pojo.TrailRequest;
import com.borymskyi.trail.pojo.UserResponse;
import com.borymskyi.trail.service.UserService;
import com.borymskyi.trail.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * REST controller for {@link Trails} connected requests.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@RestController
@RequestMapping("api/v1/trail")
public class TrailController {

    private TrailService trailService;
    private UserService profileService;
    private JwtUtils jwtUtils;

    @Autowired
    public TrailController(TrailService trailService, UserService profileService, JwtUtils jwtUtils) {
        this.trailService = trailService;
        this.profileService = profileService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<?> createTrail(@RequestBody TrailRequest trailRequest, HttpServletRequest request) {
        try {
            UserResponse userResponse = UserResponse.buildUserResponse(
                    profileService.getUserByUsername(
                            jwtUtils.getUsernameByJwt(request.getHeader(HttpHeaders.AUTHORIZATION))
                    )
            );

            return ResponseEntity.ok(trailService.createTrail(trailRequest, userResponse.getId()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Exception: " + e));
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTrail(@PathVariable("id") Long idTrail) {
        try {
            return ResponseEntity.ok(trailService.getTrail(idTrail));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Exception: " + e));
        }
    }

    @PutMapping("{id}/edit")
    public ResponseEntity<?> editTrail(@RequestBody TrailRequest trailRequest, @PathVariable("id") Long idTrail) {
        try {
            return ResponseEntity.ok(trailService.editTrail(trailRequest, idTrail));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Exception: " + e));
        }

    }

    @PutMapping("{id}/update_date")
    public ResponseEntity<?> updateDateTrail(@PathVariable("id") Long idTrail) {
        try {
            return ResponseEntity.ok(trailService.updateDateTrail(idTrail));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Exception: " + e));
        }
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteTrail(@PathVariable("id") Long idTrail) {
        try {
            trailService.deleteTrail(idTrail);
            return ResponseEntity.ok().body("deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Exception: " + e));
        }
    }
}
