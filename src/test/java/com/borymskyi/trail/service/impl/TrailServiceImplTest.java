package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.IntegrationTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
    void testTrailService() {
        String expectedMessage = "Hello2";
        assertEquals(expectedMessage, trailService.greeting());
    }
}