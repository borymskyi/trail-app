package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.IntegrationTestBase;
import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.pojo.RolePojo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

class RoleServiceImplTest extends IntegrationTestBase {

    @Autowired
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testSaveRole() {
        Roles role = roleService.saveRole(new RolePojo("ROLE_TEST"));
        assertEquals("ROLE_TEST", role.getName());
    }

    @Test
    public void testGetRoleByName() {
        Roles role = roleService.getRoleByName("ROLE_USER");
        assertEquals("ROLE_USER", role.getName());
    }
}