package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.pojo.SignupRequest;
import com.borymskyi.trail.config.jwt.UserDetailImpl;

/**
 * Service interface for {@link Users} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public interface UserService {

    Users addRoleToUser(String username, String roleName);

    Users getUserByUsername(String username);

    boolean checkUserByUsername(String username);

    Users createUser(SignupRequest signupRequest);

    UserDetailImpl getUserDetail(String username);

    Users removeRoleToUser(String username, String roleName);
}
