package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Trail;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.repository.ProfileRepository;
import com.borymskyi.trail.repository.TrailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementations of {@link TrailService} interface.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Slf4j
@Service
public class TrailServiceImpl implements TrailService {

    private TrailRepository trailRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public TrailServiceImpl(TrailRepository trailRepository, ProfileRepository profileRepository) {
        this.trailRepository = trailRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Trail> getAllTrails() {
        return new ArrayList<>(trailRepository.findAll());
    }

    @Override
    public Trail getTrail(Long idTrail) {
        if (trailRepository.findById(idTrail).isPresent()) {
            return trailRepository.findById(idTrail).get();
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void createTrail(Trail trail, Long profileId) {
        if (profileRepository.findById(profileId).isPresent()) {
            trail.setProfile(profileRepository.findById(profileId).get());
            trail.setUpdate_time(LocalDateTime.now());
            trailRepository.save(trail);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void editTrail(Trail trail, Long idTrail) {
        if (trailRepository.findById(idTrail).isPresent()) {
            Trail editTrail = trailRepository.findById(idTrail).get();
            editTrail.setTitle(trail.getTitle());

            trailRepository.save(editTrail);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void updateDateTrail(Long idTrail) {
        if (trailRepository.findById(idTrail).isPresent()) {
            Trail updateTrail = trailRepository.findById(idTrail).get();
            updateTrail.setUpdate_time(LocalDateTime.now());

            trailRepository.save(updateTrail);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void deleteTrail(Long idTrail) {
        if (trailRepository.findById(idTrail).isPresent()) {
            trailRepository.deleteById(idTrail);
        } else {
            throw new NotFoundException();
        }
    }
}
