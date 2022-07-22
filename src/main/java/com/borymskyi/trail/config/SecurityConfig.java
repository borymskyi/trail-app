package com.borymskyi.trail.config;

import com.borymskyi.trail.security.PasswordConfig;
import com.borymskyi.trail.security.filter.CustomAuthenticationFilter;
import com.borymskyi.trail.security.filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";

    private final UserDetailsService userDetailsService;
    private final PasswordConfig passwordConfig;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordConfig passwordConfig) {
        this.userDetailsService = userDetailsService;
        this.passwordConfig = passwordConfig;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordConfig.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/sign-in");

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/api/v1/sign-up",
                        "/api/v1/sign-in",
                        "/api/v1/token/refresh",
                        "/api/v1/users"
                ).permitAll()
                .antMatchers(
                        "/api/v1/profile**",
                        "/api/v1/trail**"
                ).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(ADMIN_ENDPOINT).hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();

        http.addFilter(customAuthenticationFilter);

        http.addFilterBefore(new CustomAuthorizationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
