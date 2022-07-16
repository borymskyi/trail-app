package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
