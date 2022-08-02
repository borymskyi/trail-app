package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.pojo.SignupRequest;
import com.borymskyi.trail.service.impl.UserDetailImpl;

import java.util.List;

/**
 * Service interface for {@link Users} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public interface UserService {

    List<Users> getAllUsers();

    void addRoleToUser(String username, String rolename);

    Users getUser(Long userId);

    Users getUserByUsername(String username);

    void createUser(SignupRequest signupRequest);

    void deleteUser(Long userId);

    UserDetailImpl getUserDetail(String username);
}
