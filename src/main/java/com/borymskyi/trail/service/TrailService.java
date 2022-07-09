package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Trail;

import java.util.List;

public interface TrailService {

    List<Trail> getAllTrails();

    Trail getTrail(Long idTrail);

    void createTrail(Trail trail);

    void editTrail(Trail trail, Long idTrail);

    void updateDateTrail(Long idTrail);

    void deleteOneTrail(Long idTrail);
}
