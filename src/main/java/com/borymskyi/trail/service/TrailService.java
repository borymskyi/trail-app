package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Trails;
import com.borymskyi.trail.pojo.TrailRequest;
import com.borymskyi.trail.pojo.UserPojo;

/**
 * Service interface for {@link Trails} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

public interface TrailService {

    Trails getTrail(Long idTrail, UserPojo userResponse);

    Trails createTrail(TrailRequest trailRequest, Long userId);

    Trails editTrail(TrailRequest trailRequest, Long idTrail, UserPojo userResponse);

    Trails updateDateTrail(Long idTrail, UserPojo userResponse);

    boolean deleteTrail(Long idTrail, UserPojo userResponse);
}
