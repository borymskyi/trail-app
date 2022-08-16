package com.borymskyi.trail.repository;

import com.borymskyi.trail.IntegrationTestBase;
import com.borymskyi.trail.domain.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

class UserRepositoryTest extends IntegrationTestBase {

    public static final Long users_id = 3L;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {
        Optional<Users> user = userRepository.findById(users_id);
        assertTrue(user.isPresent());
        user.ifPresent(entity -> {
            assertEquals("admin", entity.getUsername());
        });
    }

    @Test
    void testSave() {
        Users user = Users.builder()
                .username("TestUsername")
                .password("qwerty123")
                .build();
        userRepository.save(user);
        assertNotNull(user.getUser_id());
    }
}