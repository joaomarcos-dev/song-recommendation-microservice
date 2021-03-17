package com.appmusic.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

import com.appmusic.model.WeatherPojo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:properties/test.properties")
public class WeatherServiceExternalAPIIntegrationTest {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WeatherService weatherService;
	
	@Test
	public void contextLoads() {
		
	}
	
	@BeforeEach
	public void beforeAnotherRequestToOpenWeather() throws InterruptedException {
		
		//Delay to make another call to OpenWeather
	    Thread.sleep(2000);
	}

	@Test
	public void assertingConectionWithWeatherExternalAPI() throws Exception {
		
		LOGGER.trace("Testing WeatherService.fetchWeatherByCityName(\"Lodon\")");	
		
		WeatherPojo weatherPojo = weatherService.fetchWeatherByCityName("London");
		
		//If Jackson could parse correctly, the response body was in the right format
		assertNotNull(weatherPojo);
		
		assertNotNull(weatherPojo.getTemperature());
		
		//Asserting the temperature value is greater than 0 Kelvin
		assertFalse(weatherPojo.getTemperature().compareTo(Float.valueOf("-273.23")) <= 0);	
				
	}
	
	@Test
	public void fetchWeatherByCoodinatesWeatherExternalAPI() throws Exception {
		
		Double lat = Double.valueOf(35);
		Double lon = Double.valueOf(139);
		
		LOGGER.trace("Testing WeatherService.fetchWeatherByCoodinates()");	
		
		WeatherPojo weatherPojo = weatherService.fetchWeatherByCoodinates(lat, lon);
		
		//If Jackson could parse correctly, the response body was in the right format
		assertNotNull(weatherPojo);
		
		assertNotNull(weatherPojo.getTemperature());
		
		//Asserting the temperature value is greater than 0 Kelvin
		assertFalse(weatherPojo.getTemperature().compareTo(Float.valueOf("-273.23")) <= 0);
		
				
	}
}