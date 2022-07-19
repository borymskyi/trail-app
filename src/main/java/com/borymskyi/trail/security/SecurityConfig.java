package com.borymskyi.trail.security;

import com.borymskyi.trail.filter.CustomAuthenticationFilter;
import com.borymskyi.trail.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
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

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/sign-in");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(POST,"api/role/add/**")
                .hasAnyAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(GET, "/api/profile/**")
                .hasAnyAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers("/api/sing-in/**", "/api/token/refresh/**", "/api/sign-up/**")
                .permitAll().anyRequest().authenticated()
                .and()
                .httpBasic();


        /*http.authorizeRequests().antMatchers(POST,"api/role/add/**")
                .hasAnyAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(GET, "/api/profile/**")
                .hasAnyAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers("/api/sing-in/**", "/api/token/refresh/**", "/api/sign-up/**")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/**")
                .hasAnyAuthority("ROLE_ADMIN").anyRequest().authenticated()
                .and()
                .httpBasic();*/

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
