package com.appmusic.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.appmusic.model.GenreEnum;
import com.appmusic.model.PlaylistPojo;
import com.appmusic.model.SpotifyBearerTokenPojo;
import com.appmusic.model.TrackPojo;
import com.appmusic.model.exception.IncorrectSyntaxException;
import com.appmusic.model.exception.InternalErrorException;



@Service
@PropertySource("classpath:properties/application.properties")
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
	
	public PlaylistPojo playlistByGenre(GenreEnum genre, String orderByFieldName) throws URISyntaxException{
		
		//URL
		URI uri = new URI( String.format("%s/recommendations?seed_genres=%s", spotifyApiBaseUrl, genre.name().toLowerCase()));	

		//Headers
		HttpHeaders headers = new HttpHeaders();		
		SpotifyBearerTokenPojo sbtp = spotifyBearerTokenService.getSpotifyBearerToken();
		headers.setBearerAuth(sbtp.getAccessToken());
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		//Request
		RequestEntity<PlaylistPojo> requestEntity = new RequestEntity<PlaylistPojo>(headers, HttpMethod.GET, uri);
		
		ResponseEntity<PlaylistPojo> response;
		
		
		try {
			
			response = restTemplate.exchange(requestEntity, PlaylistPojo.class);
			
		}catch(RestClientException rce) {
			//pretty simple
			throw new InternalErrorException();
		}
		
		PlaylistPojo playlistPojo = response.getBody(); 
		
		
		//ORDERING 
		return orderSongsByFieldName(orderByFieldName, playlistPojo);
	}
	
	private PlaylistPojo orderSongsByFieldName(String fieldName, PlaylistPojo playlistPojo) {
		
		List<TrackPojo> tpList = (List<TrackPojo>) playlistPojo
			.getSongList()
			.stream()
			.sorted(getTrackComparator(fieldName))
			.collect(Collectors.toList());
			
		playlistPojo.setSongList(tpList);
			
		return playlistPojo;
	}

	private Comparator<TrackPojo> getTrackComparator(String fieldName){
		
		Comparator<TrackPojo> genericComparator = null;
		
		try {
			
			Method getter = TrackPojo.class.getMethod("get" + StringUtils.capitalize(fieldName));
		
			//Assuming the class is regular pojo
			genericComparator = 
					(p1, p2) -> {
						try {
							return ((String)getter.invoke(p1))
							.compareToIgnoreCase((String)getter.invoke(p2));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return -1;
					};
		 
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
			
			throw new IncorrectSyntaxException();		
		}
		
		return genericComparator;
	
	}
}