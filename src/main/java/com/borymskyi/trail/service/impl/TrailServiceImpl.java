package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Trail;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.repository.ProfileRepository;
import com.borymskyi.trail.repository.TrailRepository;
import com.borymskyi.trail.service.TrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementations of {@link TrailService} interface.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Service
@Transactional
@Slf4j
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
        return trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
    }

    @Override
    public Trail createTrail(Trail trail, Long profileId) {
        if (profileRepository.findById(profileId).isPresent()) {
            trail.setProfile(profileRepository.findById(profileId).get());
            trail.setUpdate_time(LocalDateTime.now());
            return trailRepository.save(trail);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Trail editTrail(Trail trail, Long idTrail) {
        if (trailRepository.findById(idTrail).isPresent()) {
            Trail editTrail = trailRepository.findById(idTrail).get();
            editTrail.setTitle(trail.getTitle());

            return trailRepository.save(editTrail);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Trail updateDateTrail(Long idTrail) {
        Trail trail = trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
        trail.setUpdate_time(LocalDateTime.now());
        return trailRepository.save(trail);
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
