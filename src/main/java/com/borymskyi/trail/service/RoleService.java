package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Roles;

/**
 * Service interface for {@link Roles} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface RoleService {

    Roles saveRole(Roles role);

    Roles getRoleByName(String name);

}
