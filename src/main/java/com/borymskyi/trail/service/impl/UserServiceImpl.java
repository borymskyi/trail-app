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

    private UserRepository usersRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository profileRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.usersRepository = profileRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkUserByUsername(String username) {
        if (usersRepository.findByUsername(username) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDetailImpl getUserDetail(String username) {
        Users user = usersRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database with username: {}", user.getUsername());
        }

        return UserDetailImpl.build(user);
    }

    @Override
    public Users getUserByUsername(String username) {
        try {
            Users profile = usersRepository.findByUsername(username);
            log.info("User found in the database with username: {}", profile.getUsername());
            return profile;
        } catch (Exception e) {
            log.error("User " + username + " not found in the database");
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public void createUser(SignupRequest signupRequest) {
        if (usersRepository.findByUsername(signupRequest.getUsername()) != null) {
            throw new UserAlreadyExists();
        }

        Users user = new Users(null, signupRequest.getUsername(), signupRequest.getPassword(), null,
                new ArrayList<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));

        Users newProfile = usersRepository.save(user);
        log.info("Created a new profile with username: {}", newProfile.getUsername());
    }

    @Override
    public void createAdmin(SignupRequest signupRequest) {
        if (usersRepository.findByUsername(signupRequest.getUsername()) != null) {
            throw new UserAlreadyExists();
        }

        Users user = new Users(null, signupRequest.getUsername(), signupRequest.getPassword(), null,
                new ArrayList<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));

        Users newProfile = usersRepository.save(user);
        log.info("Created a new profile with username: {}", newProfile.getUsername());
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Users user = usersRepository.findByUsername(username);
        Roles role = roleRepository.findByName(roleName);
        if (user != null && role != null) {
            user.getRoles().add(role);
            log.info("Role=" + role.getName() + " add to User=" + user.getUsername());
            usersRepository.save(user);
        } else {
            log.error("Bad request. Username=" + username +
                    " RoleName=" + roleName);
            throw new NotFoundException();
        }
    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        Users user = usersRepository.findByUsername(username);
        Roles role = roleRepository.findByName(roleName);
        if (user != null && role != null) {
            for (int i = 0; i < user.getRoles().size(); i++) {
                if (user.getRoles().get(i).getName().equals(roleName)) {
                    user.getRoles().remove(i);
                }
            }
            log.info("Role=" + role.getName() + " remove to User=" + user.getUsername());
            usersRepository.save(user);
        } else {
            log.error("Bad request. Username=" + username +
                    " RoleName=" + roleName);
            throw new NotFoundException();
        }
    }
}
