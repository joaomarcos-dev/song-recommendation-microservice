package com.appmusic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.appmusic.model.WeatherPojo;
import com.appmusic.model.exception.CityNotFoundException;

@Service
@PropertySources({
    @PropertySource("classpath:properties/application.properties"),
    @PropertySource("classpath:properties/sensitive.properties")
})
public class WeatherService {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${appmusic.openweatherapi.baseurl}")
	String openWeatherBaseUrl;
	
	@Value("${appmusic.openweatherapi.apiid}")
	String apiId;

	public WeatherService() {
		super();
	}
	
	public WeatherPojo fetchWeatherByCoodinates(Double latitude, Double longitude) {
		
		LOGGER.trace(String.format("fetch weather by coodinates for latitude {} and longitude {}", latitude, longitude));
		WeatherPojo wp;
		
		try {
			ResponseEntity<WeatherPojo> re = restTemplate.getForEntity(String.format("%s/weather?lat=%s&lon=%s&appid=%s", openWeatherBaseUrl, latitude, longitude, apiId), WeatherPojo.class);
			wp =  re.getBody();
			
		}catch(RestClientException rce) {
			//pretty simple
			throw new CityNotFoundException();
		}
		
		return wp;
		
	}	
	
	
	
	public WeatherPojo fetchWeatherByCityName(String cityName) {
		
		LOGGER.debug("Featching weather by city {}", cityName);
		String url = String.format("%s/weather?appid=%s&q=%s", openWeatherBaseUrl, apiId, cityName);
		
		WeatherPojo wp;
		
		try {
			
			ResponseEntity<WeatherPojo> re = restTemplate.getForEntity(url, WeatherPojo.class);
			wp =  re.getBody();
			
		}catch(RestClientException rce) {
			//pretty simple
			throw new CityNotFoundException();
		}
		
		return wp;
		
	}
	
	public static Float fromKelvinToCelsius(Float kelvin) {
		return kelvin - Float.valueOf("273.15");
	}

}
