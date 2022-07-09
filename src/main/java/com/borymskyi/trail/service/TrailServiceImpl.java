package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Trail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TrailServiceImpl implements TrailService {

    private Map<Long, Trail> allTrails = new HashMap<>();

    private Long count = 0L;

    @Override
    public List<Trail> getAllTrails() {
        return new ArrayList<>(allTrails.values());
    }

    @Override
    public Trail getTrail(Long idTrail) {
        return allTrails.get(idTrail);
    }

    @Override
    public void createTrail(Trail trail) {
        count++;
        trail.setUpdate_time(LocalDateTime.now());
        allTrails.put(count, trail);
    }

    @Override
    public void editTrail(Trail trail, Long idTrail) {
        if (!allTrails.get(idTrail).getId().equals(trail.getId()) &&
                !allTrails.get(idTrail).getUpdate_time().equals(trail.getUpdate_time())) {
            trail.setId(allTrails.get(idTrail).getId());
            trail.setUpdate_time(allTrails.get(idTrail).getUpdate_time());
        }

        allTrails.replace(idTrail, trail);
    }

    @Override
    public void updateDateTrail(Long idTrail) {
        allTrails.get(idTrail).setUpdate_time(LocalDateTime.now());
    }

    @Override
    public void deleteOneTrail(Long idTrail) {
        allTrails.remove(idTrail);
    }
}
