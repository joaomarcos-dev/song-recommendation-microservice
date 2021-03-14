package com.appmusic.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.appmusic.model.PlaylistPojo;
import com.appmusic.model.SpotifyBearerTokenPojo;

@Service
@PropertySource("classpath:application.properties")
public class PlaylistService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;	
	
	@Autowired
	private SpotifyBearerTokenService spotifyBearerTokenService;
	
	@Value("${appmusic.spotifyapi.baseurl}")
	private String spotifyApiBaseUrl;
	
	public PlaylistService() {
		super();
	}
	
	public PlaylistPojo playlistByGenre(Genre genre) throws URISyntaxException{
		
		//URL
		URI uri = new URI( String.format("%s/recommendations?seed_genres=%s", spotifyApiBaseUrl, genre.name().toLowerCase()));
		
		LOGGER.info(uri.toASCIIString());

		//Headers
		HttpHeaders headers = new HttpHeaders();		
		SpotifyBearerTokenPojo sbtp = spotifyBearerTokenService.getSpotifyBearerToken();
		headers.setBearerAuth(sbtp.getAccessToken());
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		LOGGER.info(sbtp.getAccessToken());
		
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//		
//		messageConverters.add(converter);
//		restTemplate.setMessageConverters(messageConverters);
		
		//Request
		RequestEntity<PlaylistPojo> requestEntity = new RequestEntity<PlaylistPojo>(headers, HttpMethod.GET, uri);
		
		ResponseEntity<PlaylistPojo> response = restTemplate.exchange(requestEntity, PlaylistPojo.class);
//		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

		LOGGER.info(response.getHeaders().getContentType().toString());
		
		return response.getBody();
//		return null;
		
	}
	
}
