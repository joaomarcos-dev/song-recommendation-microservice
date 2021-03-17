package com.appmusic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@PropertySource("classpath:properties/application.properties")
public class AppCorsConfiguration implements WebMvcConfigurer {
	
	Logger LOGGER  = LoggerFactory.getLogger(this.getClass());
	
	@Value("${appmusic.security.cors.frontend-url}")
	String clientURL;
	
	@Value("${appmusic.security.cors.allowed-methods}")
	String allowedMethods;

	@Override
	public void	addCorsMappings(CorsRegistry registry){
		
		LOGGER.debug("Adding CORS mapping");
		
		String [] methods = allowedMethods.split(".");
		LOGGER.info(methods.toString());
		registry
			.addMapping("/playlist")//applied to all
			.allowedMethods(methods)
			.allowedOrigins(clientURL)
			.exposedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
			;
	}

}
