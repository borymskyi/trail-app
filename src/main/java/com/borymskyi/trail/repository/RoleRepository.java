package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Roles} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}
