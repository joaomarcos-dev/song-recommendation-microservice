package com.appmusic.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

public class RequestLogger extends AbstractRequestLoggingFilter {
	
	private final Logger LOGGER = LoggerFactory.getLogger(RequestLogger.class.getSimpleName());

	final String REPLACEMENT_FOR_NO_BODY = "NO_BODY";
	final String REQUEST_ID_NAME = "REQUEST_ID";

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		
		final String date = LocalDateTime.now().toString();
		
		final String httpMethod = request.getMethod();
		
		final String baseURI = request.getRequestURI();
		
		final String queryString = request.getQueryString();
		
		
		String payload = null;
		
		try {
			
			payload = IOUtils.toString(request.getReader());
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
				
		final String finalPayload = (StringUtils.isNotEmpty(payload)) ? payload : REPLACEMENT_FOR_NO_BODY;
	
		message = String.format("%s %s %s %s %s", date, httpMethod, baseURI, queryString, finalPayload);
		
		LOGGER.trace(message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {

	}


}
