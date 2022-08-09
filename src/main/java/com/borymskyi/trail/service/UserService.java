package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.pojo.RolePojo;
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

    void addRoleToUser(String username, String roleName);

    Users getUserByUsername(String username);

    boolean checkUserByUsername(String username);

    void createUser(SignupRequest signupRequest);

    UserDetailImpl getUserDetail(String username);

    void createAdmin(SignupRequest signupRequest);

    void removeRoleToUser(String username, String roleName);
}
