package com.appmusic.resource;

import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appmusic.model.PlaylistPojo;

@RestController
@RequestMapping("playlist")
public class PlaylistResource {
	
	@GetMapping
	public PlaylistPojo getPlaylistSuggestion(
			@QueryParam("latitude") Double latitude,	
			@QueryParam("longitude") Double longitude,
			@QueryParam("cityname") String cityname
 			) {

		PlaylistPojo playlistPojo = new PlaylistPojo();
		
		playlistPojo.getSongList().add(String.valueOf(latitude));
		playlistPojo.getSongList().add(String.valueOf(longitude));
		playlistPojo.getSongList().add(String.valueOf(cityname));
		
		return playlistPojo;
	}
}
