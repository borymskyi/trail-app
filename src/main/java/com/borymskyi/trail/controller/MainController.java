package com.borymskyi.trail.controller;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.domain.Role;
import com.borymskyi.trail.dto.RoleToUserForm;
import com.borymskyi.trail.service.ProfileService;
import com.borymskyi.trail.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private ProfileService profileService;
    private RoleService roleService;

    @Autowired
    public MainController(ProfileService profileService, RoleService roleService) {
        this.profileService = profileService;
        this.roleService = roleService;
    }

    @GetMapping("/profile")
    public ResponseEntity<List<Profile>> getAllUsers() {
        return ResponseEntity.ok().body(profileService.getAllUsers());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Profile> registration(@RequestBody Profile profile) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/sign-up").toUriString());
        return ResponseEntity.created(uri).body(profileService.createProfile(profile));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> registration(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        profileService.addRoleToProfile(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
