package com.borymskyi.trail.controller;

import com.borymskyi.trail.domain.Role;
import com.borymskyi.trail.pojo.RoleToUserForm;
import com.borymskyi.trail.service.ProfileService;
import com.borymskyi.trail.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * REST controller for ROLE_ADMIN requests.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final ProfileService profileService;
    private final RoleService roleService;

    @Autowired
    public AdminController(ProfileService profileService, RoleService roleService) {
        this.profileService = profileService;
        this.roleService = roleService;
    }

    @PostMapping("/role/new")
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
