package com.appmusic.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.appmusic.model.SpotifyBearerTokenPojo;

@Service
@PropertySource("classpath:sensitive.properties")
public class SpotifyBearerTokenService {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${appmusic.spotifyapi.clientid}")
	private String clientId;
	
	@Value("${appmusic.spotifyapi.secret}")
	private String secret;
	
	@Value("${appmusic.spotifyaccount.baseurl}")
	private String spotifyAccountBaseUrl;
	
	private SpotifyBearerTokenPojo spotifyBearerToken;
	
	public SpotifyBearerTokenService() {
		super();
	}
	
	@Scheduled(fixedRateString = "${appmusic.spotifyapi.token-expiration-milleseconds}") //Renew the token
	private void getBearerToken() throws URISyntaxException {
		
		LOGGER.debug("Renewing Spotify token");
		
		//URL
		URI uri = new URI( String.format("%s/token", spotifyAccountBaseUrl));
		
		//Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(getCredentialsBASE64());
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//Body
		MultiValueMap<String, String> bodyMap= new LinkedMultiValueMap<>();
		bodyMap.add("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(bodyMap, headers);		
		
		//Request	
		this.spotifyBearerToken = restTemplate.exchange(uri,HttpMethod.POST, httpEntity, SpotifyBearerTokenPojo.class).getBody();
		
	}
	
	private String getCredentialsBASE64() {
			
			String rawCredentials = String.format("%s:%s", clientId, secret);
			
			return Base64.getEncoder().encodeToString(rawCredentials.getBytes());
	}

	public SpotifyBearerTokenPojo getSpotifyBearerToken() {
		return spotifyBearerToken;
	}
	
}
