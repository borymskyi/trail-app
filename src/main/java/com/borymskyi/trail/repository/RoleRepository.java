package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Role} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
