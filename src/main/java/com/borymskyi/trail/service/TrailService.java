package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Trail;

import java.util.List;

/**
 * Service interface for {@link Trail} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public interface TrailService {

    List<Trail> getAllTrails();

    Trail getTrail(Long idTrail);

    Trail createTrail(Trail trail, Long profileId);

    Trail editTrail(Trail trail, Long idTrail);

    Trail updateDateTrail(Long idTrail);

    void deleteTrail(Long idTrail);
}
