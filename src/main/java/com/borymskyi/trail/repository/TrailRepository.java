package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Trail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Trail} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface TrailRepository extends JpaRepository<Trail, Long> {
}
