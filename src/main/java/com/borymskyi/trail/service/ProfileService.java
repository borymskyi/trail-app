package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Profile;

/**
 * Service interface for {@link Profile} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public interface ProfileService {

    Profile getProfileId(Long profileId);

    Profile createProfile(Profile profile);

    void deleteProfile(Long profileId);
}
