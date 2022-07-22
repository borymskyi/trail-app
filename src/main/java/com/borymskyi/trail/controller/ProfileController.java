package com.borymskyi.trail.controller;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable("id") Long profileId) {
        return ResponseEntity.ok(profileService.getProfile(profileId));
    }

    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.createProfile(profile));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long profileId) {
        profileService.deleteProfile(profileId);
    }
}
