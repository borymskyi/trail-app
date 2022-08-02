package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Trails;
import com.borymskyi.trail.pojo.TrailRequest;

/**
 * Service interface for {@link Trails} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public interface TrailService {

    Trails getTrail(Long idTrail);

    Trails createTrail(TrailRequest trailRequest, Long userId);

    Trails editTrail(TrailRequest trailRequest, Long idTrail);

    Trails updateDateTrail(Long idTrail);

    void deleteTrail(Long idTrail);
}
