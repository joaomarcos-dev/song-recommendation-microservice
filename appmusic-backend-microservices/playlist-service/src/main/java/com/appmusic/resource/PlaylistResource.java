package com.appmusic.resource;

import static org.apache.commons.lang3.ObjectUtils.anyNull;

import java.net.URISyntaxException;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appmusic.model.GenreEnum;
import com.appmusic.model.PlaylistPojo;
import com.appmusic.model.WeatherPojo;
import com.appmusic.model.exception.IncorrectSyntaxException;
import com.appmusic.service.PlaylistService;
import com.appmusic.service.RecommendationService;
import com.appmusic.service.WeatherService;

@RestController
@RequestMapping("playlist")
public class PlaylistResource {
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private PlaylistService spotifyPlaylistService;
	
	@Autowired
	private RecommendationService recommendationService;
	
	@GetMapping
	public PlaylistPojo getPlaylistSuggestion(
			@QueryParam("latitude") Double latitude,	
			@QueryParam("longitude") Double longitude,
			@QueryParam("cityName") String cityName
 			) throws URISyntaxException {
		
		WeatherPojo wp = null;
		
		if(!anyNull(latitude, longitude)) {
			
			wp = weatherService.fetchWeatherByCoodinates(latitude, longitude);
			
		}else if (!anyNull(cityName)){
			
			wp = weatherService.fetchWeatherByCityName(cityName);
		}else {
			throw new IncorrectSyntaxException();
		}
		
		GenreEnum genre = recommendationService.getRecommendation(wp.getTemperature());
			
		return spotifyPlaylistService.playlistByGenre(genre);
	}
}
