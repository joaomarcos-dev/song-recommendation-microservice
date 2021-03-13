package com.appmusic.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appmusic.model.PlaylistPojo;

@RestController
@RequestMapping("playlist")
public class PlaylistResource {
	
	@GetMapping
	public PlaylistPojo getPlaylistSuggestion(
			@RequestParam("latitude") double latitude,	
			@RequestParam("longitude") double longitude,
			@RequestParam("cityname") String cityname
 			) {
		
		
		
		PlaylistPojo playlistPojo = new PlaylistPojo();
		
		playlistPojo.getSongList().add("Music01");
		playlistPojo.getSongList().add("Music01");
		
		return playlistPojo;
	}
}
