package com.jodev.businessappmusic.businessappmusic.serverservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BusinessappmusicServerservicediscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessappmusicServerservicediscoveryApplication.class, args);
	}

}
