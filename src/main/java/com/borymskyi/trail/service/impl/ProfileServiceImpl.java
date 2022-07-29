package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.domain.Role;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.exception.ProfileAlreadyExists;
import com.borymskyi.trail.repository.ProfileRepository;
import com.borymskyi.trail.repository.RoleRepository;
import com.borymskyi.trail.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementations of {@link ProfileService} interface.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Service
@Transactional
@Slf4j
public class ProfileServiceImpl implements ProfileService, UserDetailsService {

    private ProfileRepository profileRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByUsername(username);

        if (profile == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database with username: {}", profile.getUsername());
        }

        return UserDetailImpl.build(profile);
    }

    @Override
    public UserDetailImpl getUserDetail(String username) {
        Profile profile = profileRepository.findByUsername(username);

        if (profile == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database with username: {}", profile.getUsername());
        }

        return UserDetailImpl.build(profile);
    }

    @Override
    public Profile getProfile(Long profileId) {
        return profileRepository.findById(profileId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Profile createProfile(Profile profile) {
        if (profileRepository.findByUsername(profile.getUsername()) != null) {
            throw new ProfileAlreadyExists();
        }

        profile.setPassword(passwordEncoder.encode(profile.getPassword()));

        Profile newProfile = profileRepository.save(profile);
        log.info("Created a new profile with username: {}", newProfile.getUsername());

        return newProfile;
    }

    @Override
    public void deleteProfile(Long profileId) {
        if (profileRepository.findById(profileId).isPresent()) {
            profileRepository.deleteById(profileId);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Profile> getAllUsers() {
        log.info("Fetching all users");
        return profileRepository.findAll();
    }

    @Override
    public void addRoleToProfile(String username, String rolename) {
        Profile profile = profileRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        profile.getRoles().add(role);

        log.info("Add role {} to profile {}", rolename, username);
        profileRepository.save(profile);
    }

    @Override
    public Profile getProfileByUsername(String username) {
        Profile profile = profileRepository.findByUsername(username);
        if (profile == null) {
            throw new NotFoundException();
        }

        return profile;
    }
}
