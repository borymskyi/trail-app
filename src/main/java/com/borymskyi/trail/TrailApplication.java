package com.borymskyi.trail;

import com.borymskyi.trail.pojo.RolePojo;
import com.borymskyi.trail.pojo.SignupRequest;
import com.borymskyi.trail.service.RoleService;
import com.borymskyi.trail.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrailApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailApplication.class, args);
	}
}
