package com.appmusic.resource;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appmusic.model.PlaylistPojo;
import com.appmusic.model.WeatherPojo;
import com.appmusic.service.WeatherService;

@RestController
@RequestMapping("playlist")
public class PlaylistResource {
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping
	public PlaylistPojo getPlaylistSuggestion(
			@QueryParam("latitude") Double latitude,	
			@QueryParam("longitude") Double longitude,
			@QueryParam("cityname") String cityname
 			) {
		
		String type = null;
		
		if(latitude != null  && longitude != null) {
			
			type = "cord";
			
		}else if ( cityname != null ) {
			
			type = "cityname";
			
		}
		
		WeatherPojo wp = null;
		
		switch(type) {
			case "cord":
				
				wp = weatherService.fetchWeather(latitude, longitude);
				
				break;
			case "cityname":
				
				break;
		}
		
		PlaylistPojo playlistPojo = new PlaylistPojo();
		
		playlistPojo.getSongList().add(String.valueOf(wp.getTemperature()));
		playlistPojo.getSongList().add(String.valueOf(longitude));
		playlistPojo.getSongList().add(String.valueOf(latitude));
		playlistPojo.getSongList().add(String.valueOf(cityname));
		
		return playlistPojo;
	}
}
