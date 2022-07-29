package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Role;
import com.borymskyi.trail.repository.RoleRepository;
import com.borymskyi.trail.service.RoleService;
import com.borymskyi.trail.service.TrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementations of {@link RoleService} interface.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
