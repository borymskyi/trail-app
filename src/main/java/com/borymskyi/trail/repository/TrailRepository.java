package com.borymskyi.trail.repository;

import com.borymskyi.trail.domain.Trails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Trails} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Repository
public interface TrailRepository extends JpaRepository<Trails, Long> {
}
