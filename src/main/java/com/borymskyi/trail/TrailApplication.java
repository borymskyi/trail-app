package com.borymskyi.trail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrailApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailApplication.class, args);
		System.out.println("GET         /                         --> OAuth2");
		System.out.println("POST        /profile                  --> create profile");
		System.out.println("GET         /profile/:id              --> get profile");
		System.out.println("DELETE      /profile/:id              --> get profile id");
		System.out.println("GET         /trail                    --> get all trails");
		System.out.println("GET         /trail/:id                --> get trail");
		System.out.println("POST        /trail?profileId=:id      --> create trail for profile");
		System.out.println("PUT         /trail/:id/edit           --> edit title trail");
		System.out.println("PUT         /trail/:id/update_date    --> update date trail");
		System.out.println("DELETE      /trail/:id/delete         --> delete trail");
	}

}
