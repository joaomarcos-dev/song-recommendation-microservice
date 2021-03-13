package com.appmusic.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.appmusic")
public class PlaylistMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistMicroservice.class, args);
	}

}
