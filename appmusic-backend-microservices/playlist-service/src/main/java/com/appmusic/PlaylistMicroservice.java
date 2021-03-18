package com.appmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.appmusic.config.AppCorsConfiguration;
import com.appmusic.filters.RequestLogger;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@ComponentScan("com.appmusic")
@PropertySource("classpath:properties/application.properties")
public class PlaylistMicroservice {
	
	@Bean
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
	};
	
	@Bean
	public RequestLogger getRequestResponseLogger() {
		
		return new RequestLogger();
	}
	
	@Bean
	public AppCorsConfiguration getCorsConfiguration() {
		return new AppCorsConfiguration();
	}
	

	public static void main(String[] args) {
		
		SpringApplication.run(PlaylistMicroservice.class, args);
	}

}
