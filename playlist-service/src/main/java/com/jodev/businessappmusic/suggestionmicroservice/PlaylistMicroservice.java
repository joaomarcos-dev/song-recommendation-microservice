package com.jodev.businessappmusic.suggestionmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlaylistMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistMicroservice.class, args);
	}

}
