package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.IntegrationTestBase;
import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.pojo.SignupRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public class UserServiceImplTest extends IntegrationTestBase {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testGetUserByUsername() {
        Users user = userServiceImpl.getUserByUsername("Admin");
        assertEquals("Admin", user.getUsername());
    }

    @Test
    void testCreateUser() {
        SignupRequest signupRequest = new SignupRequest("Test2", "qwerty");
        Users user = userServiceImpl.createUser(signupRequest);
        assertNotEquals(null, user.getUser_id());
    }

    @Test
    void testAddRoleToUser() {
        Users user = userServiceImpl.addRoleToUser("Admin", "ROLE_USER");
        List<String> roles = user.getRoles().stream().map(roles1 ->
                roles1.getName()
        ).collect(Collectors.toList());
        assertTrue(roles.contains("ROLE_USER"));
    }

    @Test
    void testRemoveRoleToUser() {
        Users user = userServiceImpl.removeRoleToUser("Admin", "ROLE_ADMIN");
        List<String> roles = user.getRoles().stream().map(roles1 ->
                roles1.getName()
        ).collect(Collectors.toList());
        assertNotEquals(true, roles.contains("ROLE_ADMIN"));
    }
}













