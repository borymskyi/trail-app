package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.repository.ProfileRepository;
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
        if (profileRepository.findById(profileId).isPresent()) {
            return profileRepository.findById(profileId).get();
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void createProfile(Profile profile) {
        profileRepository.save(profile);
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
