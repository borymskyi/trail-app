package com.borymskyi.trail.service;

import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.pojo.RolePojo;

/**
 * Service interface for {@link Roles} class.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public interface RoleService {

    Roles saveRole(RolePojo roleRequest);

    Roles getRoleByName(String name);
}
