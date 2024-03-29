package com.borymskyi.trail.pojo;

import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.domain.Trails;

import java.util.List;

/**
 * POJO class for user requests
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public class UserPojo {

    private Long id;
    private String username;
    private List<Trails> trails;
    private List<Roles> roles;

    public UserPojo() {
    }

    public UserPojo(Long id, String username, List<Roles> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public UserPojo(Long id, String username, List<Trails> trails, List<Roles> roles) {
        this.id = id;
        this.username = username;
        this.trails = trails;
        this.roles = roles;
    }

    public static UserPojo buildUserResponse(Users profile) {
        return new UserPojo(
                profile.getUser_id(),
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Trails> getTrails() {
        return trails;
    }

    public void setTrails(List<Trails> trails) {
        this.trails = trails;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
