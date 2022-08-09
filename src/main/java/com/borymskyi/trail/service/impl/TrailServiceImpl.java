package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Trails;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.pojo.TrailRequest;
import com.borymskyi.trail.pojo.UserPojo;
import com.borymskyi.trail.repository.UserRepository;
import com.borymskyi.trail.repository.TrailRepository;
import com.borymskyi.trail.service.TrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Trails createTrail(TrailRequest trailRequest, Long userId) {
        if (trailRequest.getTitle() != null && !trailRequest.getTitle().equals("")) {
            if (userRepository.findById(userId).isPresent()) {
                Trails trail = new Trails(null, trailRequest.getTitle(), null, null);
                trail.setUser(userRepository.findById(userId).get());
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
    public Trails getTrail(Long idTrail, UserPojo userResponse) {
        Trails trails = trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
        if (checkRolesIncomingUser(idTrail, userResponse)) {
            return trails;
        } else {
            throw new RuntimeException("Bad request");
        }
    }

    @Override
    public Trails editTrail(TrailRequest trailRequest, Long idTrail, UserPojo userResponse) {
        Trails trails = trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
        if (trailRequest.validateTitle(trailRequest.getTitle()) && checkRolesIncomingUser(idTrail, userResponse)) {
            trails.setTitle(trailRequest.getTitle());
            log.info("Trail with id= " + idTrail + " changed title to new.");
            return trailRepository.save(trails);
        } else {
            log.error("Bad request. IncomingTrailTitle: " + trailRequest.getTitle());
            throw new RuntimeException("Bad request");
        }
    }

    @Override
    public Trails updateDateTrail(Long idTrail, UserPojo userResponse) {
        Trails trail = trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
        if (checkRolesIncomingUser(idTrail, userResponse)) {
            trail.setUpdate_time(LocalDateTime.now());
            log.info("Trail with id= " + idTrail + " updated the date.");
            return trailRepository.save(trail);
        } else {
            throw new RuntimeException("Bad request");
        }
    }

    @Override
    public void deleteTrail(Long idTrail, UserPojo userResponse) {
        Trails trails = trailRepository.findById(idTrail).orElseThrow(NotFoundException::new);
        if (checkRolesIncomingUser(idTrail, userResponse)) {
            trailRepository.deleteById(idTrail);
            log.info("Trail with id: " + idTrail + " is removed.");
        } else {
            throw new RuntimeException("Bad request");
        }
    }

    private boolean checkRolesIncomingUser(Long idTrail, UserPojo userResponse) {
        List<String> rolesUserRequest = new ArrayList<>();
        userResponse.getRoles().stream().map(element ->
                rolesUserRequest.add(element.getName())).collect(Collectors.toList());

        if (trailRepository.findById(idTrail).get().getUser().getUserId().equals(userResponse.getId()) || rolesUserRequest.contains("ROLE_ADMIN")) {
            return true;
        } else {
            log.error("Bad request. " +
                    "Trail userId=" + trailRepository.findById(idTrail).get().getUser().getUserId() +
                    " Incoming userId=" + userResponse.getId() + " Roles incoming user=" + rolesUserRequest);
            return false;
        }
    }
}
