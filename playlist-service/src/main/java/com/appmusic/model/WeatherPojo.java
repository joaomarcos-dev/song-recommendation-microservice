package com.appmusic.model;

import java.io.Serializable;
import java.util.Map;

import com.appmusic.service.WeatherService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "main")
public class WeatherPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public WeatherPojo() {
		super();
	}
	
	private Float temperature;


	public Float getTemperature() {
		return temperature;
	}


	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}
	
	@JsonProperty("main")
	private void unpackNameFromNestedObject(Map<String, String> main) {

		//Converting from Kelvin to Celsius
		this.temperature = WeatherService.fromKelvinToCelsius(Float.valueOf(main.get("temp")));
	}

}
