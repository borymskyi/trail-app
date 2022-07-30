package com.borymskyi.trail.pojo;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.domain.Role;
import com.borymskyi.trail.domain.Trail;

import java.util.List;

/**
 * POJO class for user requests
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private List<Trail> trails;
    private List<Role> roles;

    public UserResponse(Long id, String name, String username, List<Trail> trails, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.trails = trails;
        this.roles = roles;
    }

    public static UserResponse buildUserResponse(Profile profile) {
        return new UserResponse(
                profile.getId(),
                profile.getName(),
                profile.getUsername(),
                profile.getTrails(),
                profile.getRoles()
                );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Trail> getTrails() {
        return trails;
    }

    public void setTrails(List<Trail> trails) {
        this.trails = trails;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
