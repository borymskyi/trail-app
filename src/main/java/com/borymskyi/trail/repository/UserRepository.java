package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Users} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

}
