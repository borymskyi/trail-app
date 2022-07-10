package com.borymskyi.trail.controller;

import com.borymskyi.trail.domain.Trail;
import com.borymskyi.trail.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for {@link Trail} connected requests.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@RestController
@RequestMapping("trail")
public class TrailController {

    private TrailService trailService;

    @Autowired
    public TrailController(TrailService trailService) {
        this.trailService = trailService;
    }

    @GetMapping
    public ResponseEntity<List<Trail>> getAllTrails() {
        List<Trail> allTrails = trailService.getAllTrails();
        return ResponseEntity.ok(allTrails);
    }

    @GetMapping("{id}")
    public ResponseEntity<Trail> getTrail(@PathVariable("id") Long idTrail) {
        Trail trail = trailService.getTrail(idTrail);
        return ResponseEntity.ok(trail);
    }

    @PostMapping
    public void createTrail(
            @RequestBody Trail trail,
            @RequestParam Long profileId
    ) {
        trailService.createTrail(trail, profileId);
    }

    @PutMapping("{id}/edit")
    public void editTrail(
            @RequestBody Trail trail,
            @PathVariable("id") Long idTrail
    ) {
        trailService.editTrail(trail, idTrail);
    }

    @PutMapping("{id}/update_date")
    public void updateDateTrail(
            @PathVariable("id") Long idTrail
    ) {
        trailService.updateDateTrail(idTrail);
    }

    @DeleteMapping("{id}/delete")
    public void deleteTrail(@PathVariable("id") Long idTrail) {
        trailService.deleteTrail(idTrail);
    }
}
