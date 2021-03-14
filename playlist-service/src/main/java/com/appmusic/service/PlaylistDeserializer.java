package com.appmusic.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appmusic.model.PlaylistPojo;
import com.appmusic.model.TrackPojo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class PlaylistDeserializer extends StdDeserializer<PlaylistPojo> {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlaylistDeserializer() { 
        this(null); 
    } 

    public PlaylistDeserializer(Class<?> vc) { 
        super(vc); 
    }
	


	@Override
	public PlaylistPojo deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
		
		PlaylistPojo pp = new PlaylistPojo();
		
		jsonNode.get("tracks").forEach(jn -> {
			
			LOGGER.info(jn.get("name").toPrettyString());
			
			TrackPojo tp = new TrackPojo();
			
			tp.setName(jn.get("name").textValue());
			
			pp.getSongList().add(tp);
			
		});
		
		return pp;
	}
	
	

}
