package com.appmusic.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.appmusic")
public class PlaylistMicroservice {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	};

	public static void main(String[] args) {
		SpringApplication.run(PlaylistMicroservice.class, args);
	}

}
