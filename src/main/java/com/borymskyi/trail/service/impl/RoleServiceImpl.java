package com.borymskyi.trail.service.impl;

import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.exception.NotFoundException;
import com.borymskyi.trail.pojo.RolePojo;
import com.borymskyi.trail.repository.RoleRepository;
import com.borymskyi.trail.service.RoleService;
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
    public Roles saveRole(RolePojo roleRequest) {
        if (!roleRequest.getName().equals("") && roleRequest.getName() != null) {
            Roles role = new Roles(null, roleRequest.getName());
            roleRepository.save(role);
            log.info("Role with name=" + roleRequest.getName() + " is created.");
            return role;
        } else {
            log.error("Bad request. RoleName=" + roleRequest.getName());
            throw new RuntimeException("Bad request");
        }

    }

    @Override
    public Roles getRoleByName(String name) {
        try {
            return roleRepository.findByName(name);
        } catch (Exception e) {
            log.error("Role with name:" + name + " not found");
            throw new NotFoundException();
        }
    }
}
