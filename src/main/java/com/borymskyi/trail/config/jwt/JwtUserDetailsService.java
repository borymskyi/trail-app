package com.borymskyi.trail.config.jwt;

import com.borymskyi.trail.domain.Profile;
import com.borymskyi.trail.service.ProfileService;
import com.borymskyi.trail.service.impl.UserDetailImpl;
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

    private final ProfileService profileService;

    @Autowired
    public JwtUserDetailsService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileService.getProfileByUsername(username);
        return UserDetailImpl.build(profile);
    }
}
