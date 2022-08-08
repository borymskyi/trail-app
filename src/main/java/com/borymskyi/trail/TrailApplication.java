package com.borymskyi.trail;

import com.borymskyi.trail.domain.Roles;
import com.borymskyi.trail.pojo.SignupRequest;
import com.borymskyi.trail.service.RoleService;
import com.borymskyi.trail.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

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

	@Bean
	CommandLineRunner runSecond(UserService userService) {
		return args -> {
			if (userService.checkUserByUsername("admin")) {
				userService.createAdmin(new SignupRequest("admin", "qwerty123"));
			} else {
				System.out.println("admin exists");
			}
		};
	}
}
