package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.repository.ProfileRepository;
import com.borymskyi.trail.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementations of {@link ProfileService} interface.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository userRepository) {
        this.profileRepository = userRepository;
    }

    @Override
    public Profile getProfileId(Long profileId) {
        return profileRepository.findById(profileId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Long profileId) {
        if (profileRepository.findById(profileId).isPresent()) {
            profileRepository.deleteById(profileId);
        } else {
            throw new NotFoundException();
        }
    }
}
