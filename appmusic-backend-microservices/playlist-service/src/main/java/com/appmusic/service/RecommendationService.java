package com.appmusic.service;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.appmusic.model.GenreEnum;
import com.appmusic.model.TemperatureRangePojo;

@Service
@PropertySource("classpath:properties/application.properties")
public class RecommendationService implements EnvironmentAware {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${appmusic.spotifyapi.genre.fallback}")
	String fallbackGenre;
	
	@Autowired
	private Environment env; 
	
	private final String LOWER_INCLUSIVE_LIMIT = "from-inclusive";
	private final String LOWER_EXCLUSE_LIMIT = "from-exclusive";
	private final String GREATER_INCLUSIVE_LIMIT = "to-inclusive";
	private final String GREATER_EXCLUSE_LIMIT = "to-exclusive";
	
	private List<String> rangeLimitName;
	
	private Map<GenreEnum, TemperatureRangePojo> genreTmpRangeMap = new HashMap<GenreEnum, TemperatureRangePojo>();
	
	public Map<GenreEnum, TemperatureRangePojo> getGenreTmpRangeMap() {
		return genreTmpRangeMap;
	}

	public RecommendationService() {
		super();
		this.rangeLimitName = Collections.unmodifiableList(Arrays.asList(
			LOWER_INCLUSIVE_LIMIT,
			LOWER_EXCLUSE_LIMIT,
			GREATER_INCLUSIVE_LIMIT,
			GREATER_EXCLUSE_LIMIT
		));
		
	
	}

	private void updateGenreTmpRangeMap() {
		
		//not scalable. the last configuration override the earlier
		for(GenreEnum g : GenreEnum.values()) {
			
			TemperatureRangePojo tmpRange = new TemperatureRangePojo();
			
			rangeLimitName.forEach( s -> {
				
				//to enable tests
				String propname;
				if(Boolean.valueOf(env.getProperty("appmusic.test"))) {
					
					LOGGER.info("Loading test properties");
					propname = String.format("appmusic.test.spotifyapi.genre.%s.tmprange.%s", g.name().toLowerCase(), s);
				}
				else {  
					
					LOGGER.info("Loading production properties");
					propname = String.format("appmusic.spotifyapi.genre.%s.tmprange.%s", g.name().toLowerCase(), s);
				}
				
				String propvalue = env.getProperty(propname);
				 
				if(propvalue != null)
   			    LOGGER.info("Getting prop {} from enviroment: {} ", propname, propvalue);
				
				LOGGER.info("{} {} {} {}",g.name().toLowerCase(), propvalue, s, propname);
				
				if(propvalue != null) {	
					Float temp = Float.valueOf(propvalue);
					
					if(s.contains("to")) {
						
						tmpRange.setMaxTmp(temp);
						
						if(s.contains("inclusive")) {
							
							tmpRange.setMaxTmpInclusive(true);
							
						}else {
							
							tmpRange.setMaxTmpInclusive(false);
							
						}
					}else if(s.contains("from")) {
						tmpRange.setMinTmp(temp);
						
						if(s.contains("inclusive")) {
							
							tmpRange.setMinTmpInclusive(true);
							
						}else {
							
							tmpRange.setMinTmpInclusive(false);
						}
					}
				}
				
			});
			
			genreTmpRangeMap.put(g, tmpRange);
		}
		
		this.genreTmpRangeMap.forEach((g, t) -> {
			
			LOGGER.info(g.toString());
			LOGGER.info(t.toString());
			
		});
	}
	
	public GenreEnum getRecommendation(Float tmp) {
		
		LOGGER.info("Getting recommendation for temperature {}", tmp);
		
		GenreEnum geRecommended = null;
		
		//As we got a small amount of objects
		for (Entry<GenreEnum, TemperatureRangePojo> gtMap : this.genreTmpRangeMap.entrySet()) {
			
			if(containsTmp(tmp, gtMap.getValue())) {
				LOGGER.info("Matching genre {}", gtMap.getValue());

				geRecommended =  gtMap.getKey();
				break;
			}
			
		}
		
		if(geRecommended == null ) {
			LOGGER.info("Falling back to the default genre");
			geRecommended = getFallbackGenre();
		}    
		
		return geRecommended;
			
	}
	
	private boolean containsTmp(Float tmp, TemperatureRangePojo tmpRange) {
		Boolean minTest = false;//a priori
		Boolean maxTest = false;//a priori
		
		LOGGER.info("Checking if tmp {} is in the temperature range {}", tmp, tmpRange);
		
		if(allNotNull(tmpRange.getMinTmpInclusive(), tmpRange.getMinTmp())){
		
			minTest = (tmpRange.getMinTmpInclusive()) ?
				tmp >= tmpRange.getMinTmp() :
				tmp > tmpRange.getMinTmp();
		}
		
		if(allNotNull(tmpRange.getMaxTmpInclusive(), tmpRange.getMaxTmp())){
		
			maxTest = (tmpRange.getMaxTmpInclusive()) ?
				tmp <= tmpRange.getMaxTmp() :
				tmp < tmpRange.getMaxTmp();
		
		}
		
		return minTest && maxTest;
	}

	@Override
	public void setEnvironment(Environment environment) {
		
		updateGenreTmpRangeMap();
	}
	
	private GenreEnum getFallbackGenre() {
		return GenreEnum.valueOf(fallbackGenre.toUpperCase());
	}
}
