package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.domain.Role;

import java.util.List;

/**
 * Service interface for {@link Profile} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public interface ProfileService {

    List<Profile> getAllUsers();

    void addRoleToProfile(String username, String rolename);

    Profile getProfile(Long profileId);

    Profile createProfile(Profile profile);

    void deleteProfile(Long profileId);
}
