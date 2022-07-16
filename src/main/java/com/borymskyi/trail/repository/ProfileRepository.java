package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Profile} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUsername(String username);

}
