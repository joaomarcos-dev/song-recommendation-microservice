package com.appmusic.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@TestPropertySource("classpath:properties/test.properties")
@ConfigurationProperties(prefix = "appmusic.test.resouce.playlist")
public class PlaylistResourceIntTestConfig {
	
	String rightCityName;
	String wrongCityName;
	String rightLatitude;
	String wrongLatitude;
	String rightLongitude;
	String wrongLongitude;

	public String getRightCityName() {
		return rightCityName;
	}

	public void setRightCityName(String rightCityName) {
		this.rightCityName = rightCityName;
	}




	public String getWrongCityName() {
		return wrongCityName;
	}




	public void setWrongCityName(String wrongCityName) {
		this.wrongCityName = wrongCityName;
	}




	public String getRightLatitude() {
		return rightLatitude;
	}




	public void setRightLatitude(String rightLatitude) {
		this.rightLatitude = rightLatitude;
	}




	public String getWrongLatitude() {
		return wrongLatitude;
	}




	public void setWrongLatitude(String wrongLatitude) {
		this.wrongLatitude = wrongLatitude;
	}




	public String getRightLongitude() {
		return rightLongitude;
	}




	public void setRightLongitude(String rightLongitude) {
		this.rightLongitude = rightLongitude;
	}




	public String getWrongLongitude() {
		return wrongLongitude;
	}




	public void setWrongLongitude(String wrongLongitude) {
		this.wrongLongitude = wrongLongitude;
	}




	public PlaylistResourceIntTestConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

}
