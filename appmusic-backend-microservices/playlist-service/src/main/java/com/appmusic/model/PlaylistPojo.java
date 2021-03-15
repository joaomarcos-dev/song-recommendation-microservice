package com.appmusic.model;

import java.util.ArrayList;
import java.util.List;

import com.appmusic.service.PlaylistDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = PlaylistDeserializer.class)
public class PlaylistPojo extends AbstractEntity{
	
	List<TrackPojo> songList;

	public List<TrackPojo> getSongList() {	
		return songList;
	}

	public void setSongList(List<TrackPojo> songList) {
		this.songList = songList;
	}

	public PlaylistPojo() {
		super();
		
		// Initilization
		this.songList = new ArrayList<TrackPojo>();
	}

}
