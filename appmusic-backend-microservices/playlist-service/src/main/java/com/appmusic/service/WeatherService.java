package com.appmusic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.appmusic.model.WeatherPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:sensitive.properties")
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
//		weather?lat={lat}&lon={lon}&appid={API key}
		
//		LOGGER.info(String.format("%s/weather?lat=%s&lon=%s&appid=%s", openWeatherBaseUrl, latitude, longitude, apiId));
		
		ObjectMapper om = new ObjectMapper();
		WeatherPojo wp = null;
		
		try {
			
			wp = om.readValue("{\r\n"
					+ "    \"coord\": {\r\n"
					+ "        \"lon\": 139,\r\n"
					+ "        \"lat\": 35\r\n"
					+ "    },\r\n"
					+ "    \"weather\": [\r\n"
					+ "        {\r\n"
					+ "            \"id\": 800,\r\n"
					+ "            \"main\": \"Clear\",\r\n"
					+ "            \"description\": \"clear sky\",\r\n"
					+ "            \"icon\": \"01n\"\r\n"
					+ "        }\r\n"
					+ "    ],\r\n"
					+ "    \"base\": \"stations\",\r\n"
					+ "    \"main\": {\r\n"
					+ "        \"temp\": 280.93,\r\n"
					+ "        \"feels_like\": 279.51,\r\n"
					+ "        \"temp_min\": 280.93,\r\n"
					+ "        \"temp_max\": 280.93,\r\n"
					+ "        \"pressure\": 1002,\r\n"
					+ "        \"humidity\": 92\r\n"
					+ "    },\r\n"
					+ "    \"visibility\": 10000,\r\n"
					+ "    \"wind\": {\r\n"
					+ "        \"speed\": 0.89,\r\n"
					+ "        \"deg\": 173,\r\n"
					+ "        \"gust\": 2.68\r\n"
					+ "    },\r\n"
					+ "    \"clouds\": {\r\n"
					+ "        \"all\": 2\r\n"
					+ "    },\r\n"
					+ "    \"dt\": 1615647007,\r\n"
					+ "    \"sys\": {\r\n"
					+ "        \"type\": 3,\r\n"
					+ "        \"id\": 2019346,\r\n"
					+ "        \"country\": \"JP\",\r\n"
					+ "        \"sunrise\": 1615582679,\r\n"
					+ "        \"sunset\": 1615625358\r\n"
					+ "    },\r\n"
					+ "    \"timezone\": 32400,\r\n"
					+ "    \"id\": 1851632,\r\n"
					+ "    \"name\": \"Shuzenji\",\r\n"
					+ "    \"cod\": 200\r\n"
					+ "}", WeatherPojo.class);
			
		} catch (JsonMappingException e) {

			e.printStackTrace();
			
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		
//		wp = restTemplate.getForObject(String.format("%s/weather?lat=%s&lon=%s&appid=%s", openWeatherBaseUrl, latitude, longitude, apiId), WeatherPojo.class);
		
		return wp;
		
	}	
	
	public WeatherPojo fetchWeatherByCityName(String cityName) {
		LOGGER.debug("Featching weather by cituname {}", cityName);
		
		String url = String.format("%s/weather?appid=%s&q=%s", openWeatherBaseUrl, apiId, cityName);
		
		LOGGER.debug(url);
		
		return restTemplate.getForObject(url, WeatherPojo.class);
		
	}
	
	public static Float fromKelvinToCelsius(Float kelvin) {
		return kelvin - Float.valueOf("273.15");
	}

}
