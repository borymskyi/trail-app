package com.borymskyi.trail.controller;

import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.pojo.RolePojo;
import com.borymskyi.trail.pojo.RoleToUserForm;
import com.borymskyi.trail.service.UserService;
import com.borymskyi.trail.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    private final UserService profileService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService profileService, RoleService roleService) {
        this.profileService = profileService;
        this.roleService = roleService;
    }

    @PutMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        profileService.addRoleToUser(form.getUsername(), form.getRoleName());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/role/removetouser")
    public ResponseEntity<?> removeRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        profileService.removeRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());

        return ResponseEntity.ok().build();
    }
}
