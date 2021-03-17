package com.appmusic.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

import com.appmusic.model.GenreEnum;
import com.appmusic.model.PlaylistPojo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:properties/test.properties")
public class PlaylistServiceExternalAPIIntegrationTest {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PlaylistService playlistService;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void getPlayListServiceIntegrationTest() throws Exception {
		
		LOGGER.debug("Asserting the service is being injected");
		assertNotNull(playlistService); 
		
		LOGGER.info("Asserts that there are resources for all the genres defined in the applcation");
		
		for(GenreEnum genre : GenreEnum.values()) {
			
			PlaylistPojo playlistPojo  = playlistService.playlistByGenre(genre);
			
			LOGGER.info("Requesting Spotify for genre {} returned {} songs:", genre.name(), playlistPojo.getSongList().size());
			
			assertFalse(playlistPojo.getSongList().isEmpty());
			
		}
		
	}
}