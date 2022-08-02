package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Trails;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.pojo.TrailRequest;
import com.borymskyi.trail.repository.UserRepository;
import com.borymskyi.trail.repository.TrailRepository;
import com.borymskyi.trail.service.TrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    private UserRepository userRepository;

    @Autowired
    public TrailServiceImpl(TrailRepository trailRepository, UserRepository profileRepository) {
        this.trailRepository = trailRepository;
        this.userRepository = profileRepository;
    }

    @Override
    public Trails getTrail(Long idTrail) {
        return trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
    }

    @Override
    public Trails createTrail(TrailRequest trailRequest, Long userId) {
        if (trailRequest.getTitle() != null && !trailRequest.getTitle().equals("")) {
            if (userRepository.findById(userId).isPresent()) {
                Trails trail = new Trails(null, trailRequest.getTitle(), null, null);
                trail.setProfile(userRepository.findById(userId).get());
                trail.setUpdate_time(LocalDateTime.now());
                return trailRepository.save(trail);
            } else {
                log.error("User with id: " + userId + "not found");
                throw new UsernameNotFoundException("User with id: " + userId + "not found");
            }
        } else {
            log.error("bad request, title: " + trailRequest.getTitle());
            throw new RuntimeException("Invalid title: " + trailRequest.getTitle());
        }
    }

    @Override
    public Trails editTrail(TrailRequest trailRequest, Long idTrail) {
        if (trailRequest.getTitle() != null && !trailRequest.getTitle().equals("")) {
            if (trailRepository.findById(idTrail).isPresent()) {
                Trails editTrail = trailRepository.findById(idTrail).get();
                editTrail.setTitle(trailRequest.getTitle());

                return trailRepository.save(editTrail);
            } else {
                throw new NotFoundException();
            }
        } else {
            log.error("bad request, title: " + trailRequest.getTitle());
            throw new RuntimeException("Invalid title: " + trailRequest.getTitle());
        }
    }

    @Override
    public Trails updateDateTrail(Long idTrail) {
        Trails trail = trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
        trail.setUpdate_time(LocalDateTime.now());
        return trailRepository.save(trail);
    }

    @Override
    public void deleteTrail(Long idTrail) {
        if (trailRepository.findById(idTrail).isPresent()) {
            trailRepository.deleteById(idTrail);
            log.info("trail with id: " + idTrail + " is removed");
        } else {
            log.error("trail not found with id: " + idTrail);
            throw new NotFoundException();
        }
    }
}
