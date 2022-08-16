package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.IntegrationTestBase;
import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.domain.Trails;
import com.borymskyi.trail.pojo.TrailRequest;
import com.borymskyi.trail.pojo.UserPojo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

class TrailServiceImplTest extends IntegrationTestBase {

    @Autowired
    private TrailServiceImpl trailService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateTrail() {
        Trails trail = trailService.createTrail(
                new TrailRequest("Programming 8h"), 2L);
        assertNotEquals(null, trail.getTrail_id());
    }

    @Test
    void testGetTrailWithAdminRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(2L, "ROLE_ADMIN"));
        UserPojo userPojo = new UserPojo(2L, "Admin", roles);

        Trails trail = trailService.getTrail(2L, userPojo);
        assertNotEquals(null, trail.getTrail_id());
    }

    @Test
    void testGetTrailWithUserRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(1L, "ROLE_USER"));
        UserPojo userPojo = new UserPojo(3L, "Test", roles);

        Trails trail = trailService.getTrail(2L, userPojo);
        assertNotEquals(null, trail.getTrail_id());
    }

    @Test
    void testEditTrailWithAdminRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(2L, "ROLE_ADMIN"));
        UserPojo userPojo = new UserPojo(2L, "Admin", roles);

        Trails trail = trailService.editTrail(new TrailRequest("English 5h"), 2L, userPojo);
        assertEquals("English 5h", trail.getTitle());
    }

    @Test
    void testEditTrailWithUserRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(2L, "ROLE_USER"));
        UserPojo userPojo = new UserPojo(3L, "Test", roles);

        Trails trail = trailService.editTrail(new TrailRequest("English 5h"), 2L, userPojo);
        assertEquals("English 5h", trail.getTitle());
    }

    @Test
    void testUpdateDateTrailWithAdminRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(2L, "ROLE_ADMIN"));
        UserPojo userPojo = new UserPojo(2L, "Admin", roles);

        Trails trail = trailService.updateDateTrail(2L, userPojo);
        assertNotEquals("2022-08-16T15:59:10.902", trail.getUpdate_time().toString());
    }

    @Test
    void testUpdateDateTrailWithUserRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(1L, "ROLE_USER"));
        UserPojo userPojo = new UserPojo(3L, "Test", roles);

        Trails trail = trailService.updateDateTrail(2L, userPojo);
        assertNotEquals("2022-08-16T15:59:10.902", trail.getUpdate_time().toString());
    }

    @Test
    void testDeleteTrailWithAdminRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(2L, "ROLE_ADMIN"));
        UserPojo userPojo = new UserPojo(2L, "Admin", roles);

        assertNotEquals(false, trailService.deleteTrail(2L, userPojo));
    }

    @Test
    void testDeleteTrailWithUserRole() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(1L, "ROLE_USER"));
        UserPojo userPojo = new UserPojo(3L, "Test", roles);

        assertNotEquals(false, trailService.deleteTrail(2L, userPojo));
    }
}