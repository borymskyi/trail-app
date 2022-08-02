package com.borymskyi.trail;

import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrailApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleService roleService) {
		return args -> {
			if (roleService.getRoleByName("ROLE_USER") == null) {
				roleService.saveRole(new Roles(null, "ROLE_USER"));
			} else {
				System.out.println("ROLE_USER exist");
			}

			if (roleService.getRoleByName("ROLE_ADMIN") == null) {
				roleService.saveRole(new Roles(null, "ROLE_ADMIN"));
			} else {
				System.out.println("ROLE_ADMIN exist");
			}
		};
	}

}
