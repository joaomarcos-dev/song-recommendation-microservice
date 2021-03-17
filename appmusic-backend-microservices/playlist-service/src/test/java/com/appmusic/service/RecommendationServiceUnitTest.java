package com.appmusic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

import com.appmusic.model.GenreEnum;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:properties/test.properties", inheritLocations=false)
public class RecommendationServiceUnitTest {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RecommendationService recommendationService;
	
	@Test
	public void contextLoads() {
		
	}

	@Test
	public void testRecommedationDeterminism() throws Exception {
		
		LOGGER.trace("Testing RecommendationService.getRecommendation(Float)");	
		
		GenreEnum genre =  recommendationService.getRecommendation(Float.valueOf(5));
		assertEquals( GenreEnum.PARTY, genre);
		
		genre =  recommendationService.getRecommendation(Float.valueOf(15));
		assertEquals(GenreEnum.POP, genre);
		
		genre =  recommendationService.getRecommendation(Float.valueOf(25));
		assertEquals(GenreEnum.ROCK, genre);
	}
	
	@Test
	public void testRecommedationDeterminismAtMinimumInclusiveValues() throws Exception {
		
		LOGGER.trace("Testing RecommendationService.testRecommedationDeterminismAtMinimumValues(Float)");	
		
		GenreEnum genre =  recommendationService.getRecommendation(Float.valueOf(0));
		assertEquals( GenreEnum.PARTY, genre);
		
		genre =  recommendationService.getRecommendation(Float.valueOf(10));
		assertEquals(GenreEnum.POP, genre);
		
		genre =  recommendationService.getRecommendation(Float.valueOf(20));
		assertEquals(GenreEnum.ROCK, genre);
	}
	

	@Test
	public void testRecommedationDeterminismAtMaximumExclusiveValues() throws Exception {
		
		LOGGER.trace("Testing RecommendationService.testRecommedationDeterminismAtMaximumExclusiveValues(Float)");	
		
		GenreEnum genre =  recommendationService.getRecommendation(Float.valueOf(10));
		assertEquals( GenreEnum.POP, genre);//FALLBACK
		
		genre =  recommendationService.getRecommendation(Float.valueOf(20));
		assertEquals(GenreEnum.ROCK, genre);
		
		genre =  recommendationService.getRecommendation(Float.valueOf(30));
		assertEquals(GenreEnum.CLASSICAL, genre);
	}
	
	@Test
	public void testRecommedationDeterminismGreaterThenRange() throws Exception {
		
		LOGGER.trace("Testing RecommendationService.testRecommedationDeterminismGreaterThenRange(Float)");	
		
		GenreEnum genre =  recommendationService.getRecommendation(Float.valueOf(50));
		assertEquals( GenreEnum.CLASSICAL, genre);//FALLBACK
		
		genre =  recommendationService.getRecommendation(Float.valueOf(50));
		assertEquals(GenreEnum.CLASSICAL, genre);
		
		genre =  recommendationService.getRecommendation(Float.valueOf(50));
		assertEquals(GenreEnum.CLASSICAL, genre);
	}
	
	@Test
	public void testRecommedationDeterminismLowerThenRange() throws Exception {
		
		LOGGER.trace("Testing RecommendationService.testRecommedationDeterminismAtMaximumExclusiveValues(Float)");	
		
		GenreEnum genre =  recommendationService.getRecommendation(Float.valueOf(-50));
		assertEquals( GenreEnum.CLASSICAL, genre);//FALLBACK
		
		genre =  recommendationService.getRecommendation(Float.valueOf(-50));
		assertEquals(GenreEnum.CLASSICAL, genre);
		
		genre =  recommendationService.getRecommendation(Float.valueOf(-50));
		assertEquals(GenreEnum.CLASSICAL, genre);
	}
}