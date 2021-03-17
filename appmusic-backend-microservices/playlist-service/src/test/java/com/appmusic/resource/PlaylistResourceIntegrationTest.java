package com.appmusic.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.common.net.HttpHeaders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
public class PlaylistResourceIntegrationTest {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	//Test parameters
	@Autowired
	PlaylistResourceIntTestConfig config;
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Value("${appmusic.test.security.cors.frontend-url}")
	private String frontendUrl;
	
	private final String endpoint = "/playlist";
	
	
	@Test
	public void contextLoads() {
		
	}
	
	
	@Test
	public void testingPayloadWhenRequestRightByName() throws Exception {
		
		RequestBuilder requestBuilders = MockMvcRequestBuilders
				.get(endpoint)
				.param("cityName", config.getRightCityName());
		
		ResultMatcher [] resultMatcherVarags = {
			MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
		};
		
		ResultMatcher resultMatcher =  ResultMatcher.matchAll(resultMatcherVarags);
		
		this.mockMvc
				.perform(requestBuilders)
				.andDo(print())
				.andExpect(resultMatcher);
				
	}
	
	@Test
	public void testingPayloadWhenRequestRightByNameCORS() throws Exception {
		
		RequestBuilder requestBuilders = MockMvcRequestBuilders
				.get(endpoint)
				.param("cityName", config.getRightCityName())
	            .header(HttpHeaders.ORIGIN, frontendUrl);
		
		ResultMatcher [] resultMatcherVarags = {
			MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
			
			MockMvcResultMatchers.header().exists(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
		};
		
		 ResultMatcher resultMatcher =  ResultMatcher.matchAll(resultMatcherVarags);
		
		
		this.mockMvc
				.perform(requestBuilders)
				.andDo(print())
				.andExpect(resultMatcher);
				
	}
}