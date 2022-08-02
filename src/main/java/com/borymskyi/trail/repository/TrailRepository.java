package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Trails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Trails} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface TrailRepository extends JpaRepository<Trails, Long> {
}
