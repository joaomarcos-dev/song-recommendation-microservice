package com.appmusic.model;

import java.util.ArrayList;
import java.util.List;

public class PlaylistPojo extends AbstractEntity{
	
	List<String> songList;

	public List<String> getSongList() {	
		return songList;
	}

	public void setSongList(List<String> songList) {
		this.songList = songList;
	}

	public PlaylistPojo() {
		super();
		
		// Initilization
		this.songList = new ArrayList<String>();
	}
	
	

}
