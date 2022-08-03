package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.exception.UserAlreadyExists;
import com.borymskyi.trail.pojo.SignupRequest;
import com.borymskyi.trail.repository.UserRepository;
import com.borymskyi.trail.repository.RoleRepository;
import com.borymskyi.trail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementations of {@link UserService} interface.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository profileRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository profileRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetailImpl getUserDetail(String username) {
        Users profile = profileRepository.findByUsername(username);

        if (profile == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database with username: {}", profile.getUsername());
        }

        return UserDetailImpl.build(profile);
    }

    @Override
    public Users getUser(Long userId) {
        return profileRepository.findById(userId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Users getUserByUsername(String username) {
        try {
            Users profile = profileRepository.findByUsername(username);
            log.info("User found in the database with username: {}", profile.getUsername());
            return profile;
        } catch (Exception e) {
            log.error("User " + username + " not found in the database");
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public boolean checkUserByUsername(String username) {
        if (profileRepository.findByUsername(username) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void createUser(SignupRequest signupRequest) {
        if (profileRepository.findByUsername(signupRequest.getUsername()) != null) {
            throw new UserAlreadyExists();
        }

        Users user = new Users(null, signupRequest.getUsername(), signupRequest.getPassword(), null,
                new ArrayList<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));

        Users newProfile = profileRepository.save(user);
        log.info("Created a new profile with username: {}", newProfile.getUsername());
    }

    @Override
    public void createAdmin(SignupRequest signupRequest) {
        if (profileRepository.findByUsername(signupRequest.getUsername()) != null) {
            throw new UserAlreadyExists();
        }

        Users user = new Users(null, signupRequest.getUsername(), signupRequest.getPassword(), null,
                new ArrayList<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));

        Users newProfile = profileRepository.save(user);
        log.info("Created a new profile with username: {}", newProfile.getUsername());
    }

    @Override
    public void deleteUser(Long userId) {
        if (profileRepository.findById(userId).isPresent()) {
            profileRepository.deleteById(userId);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Users> getAllUsers() {
        log.info("Fetching all users");
        return profileRepository.findAll();
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        Users profile = profileRepository.findByUsername(username);
        Roles role = roleRepository.findByName(rolename);
        profile.getRoles().add(role);

        log.info("Add role {} to profile {}", rolename, username);
        profileRepository.save(profile);
    }
}
