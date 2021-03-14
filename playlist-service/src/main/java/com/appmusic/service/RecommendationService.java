package com.appmusic.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.apache.commons.lang3.ObjectUtils.anyNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.appmusic.model.TemperatureRange;

@Service
@PropertySource("classpath:application.properties")
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
	
	private Map<Genre, TemperatureRange> genreTmpRangeMap = new HashMap<Genre, TemperatureRange>();
	
	public Map<Genre, TemperatureRange> getGenreTmpRangeMap() {
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
		for(Genre g : Genre.values()) {
			
			TemperatureRange tmpRange = new TemperatureRange();
			
			rangeLimitName.forEach( s -> {
				
				String prop = env.getProperty(String.format("appmusic.spotifyapi.genre.%s.tmprange.%s", g.name().toLowerCase(), s));
				if(prop != null) {	
					
					if(s.contains("to")) {
						
						tmpRange.setMaxTmp(Float.valueOf(prop));
						
						if(s.contains("inclusive")) {
							
							tmpRange.setMaxTmpInclusive(true);
							
						}else {
							
							tmpRange.setMaxTmpInclusive(false);
							
						}
					}else {
						tmpRange.setMinTmp(Float.valueOf(prop));
						
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
	
	public Genre getRecommendation(Float tmp) {
		
		//As we got a small amount of objects
		for (Entry<Genre, TemperatureRange> gtMap : this.genreTmpRangeMap.entrySet()) {
			
			if(containsTmp(tmp, gtMap.getValue())) {
				
				return gtMap.getKey();
			}
		}
		return getFallbackGenre();			
			
	}
	
	private boolean containsTmp(Float tmp, TemperatureRange tmpRange) {
		Boolean minTest = false;//a priori
		Boolean maxTest = false;//a priori
		
		if(anyNotNull(tmpRange.getMinTmpInclusive(), tmpRange.getMinTmp())){
		
			minTest = (tmpRange.getMinTmpInclusive()) ?
				tmp >= tmpRange.getMinTmp() :
				tmp > tmpRange.getMinTmp();
		}
		
		if(anyNotNull(tmpRange.getMaxTmpInclusive(), tmpRange.getMaxTmp())){
		
			maxTest = (tmpRange.getMaxTmpInclusive()) ?
				tmp <= tmpRange.getMinTmp() :
				tmp < tmpRange.getMinTmp();
		
		}
		
		return minTest && maxTest;
	}

	@Override
	public void setEnvironment(Environment environment) {
		
		updateGenreTmpRangeMap();
	}
	
	private Genre getFallbackGenre() {
		return Genre.valueOf(fallbackGenre.toUpperCase());
	}
}
