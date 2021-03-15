package com.appmusic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyBearerTokenPojo {
	
	@JsonProperty("access_token")
	String accessToken;
	
	@JsonProperty("token_type")
	String tokenType;
	
	@JsonProperty("expires_in")
	Integer expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
}
