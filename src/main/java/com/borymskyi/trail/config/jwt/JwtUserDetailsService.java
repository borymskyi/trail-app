package com.borymskyi.trail.config.jwt;

import com.borymskyi.trail.domain.Users;
import com.borymskyi.trail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService profileService;

    @Autowired
    public JwtUserDetailsService(UserService profileService) {
        this.profileService = profileService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users profile = profileService.getUserByUsername(username);
        return UserDetailImpl.build(profile);
    }
}
