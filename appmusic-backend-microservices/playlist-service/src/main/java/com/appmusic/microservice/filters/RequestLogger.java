package com.appmusic.microservice.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

public class RequestLogger extends AbstractRequestLoggingFilter {
	
	private final Logger LOGGER = LoggerFactory.getLogger(RequestLogger.class.getSimpleName());

	final String REPLACEMENT_FOR_NO_BODY = "NO_BODY";
	final String REQUEST_ID_NAME = "REQUEST_ID";
 	
//	static ContentCachingResponseWrapper  hsrReference;

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
		
		//Add request ID to bind to the response that will also be logged
//		String requestId =  UUID.randomUUID().toString();
//		request.setAttribute(REQUEST_ID_NAME, requestId);
		
		final String finalPayload = (StringUtils.isNotEmpty(payload)) ? payload : REPLACEMENT_FOR_NO_BODY;

//		message = String.format("REQUEST_ID_%s %s %s %s %s %s ", requestId, date, httpMethod, baseURI, queryString, finalPayload);
		
		message = String.format("%s %s %s %s %s", date, httpMethod, baseURI, queryString, finalPayload);
		
		LOGGER.trace(message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
//		LOGGER.debug("AFTER");	
//		logResponsePayload(wrapResponse(hsrReference), String.valueOf(request.getAttribute(REQUEST_ID_NAME)));
	}
	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
////		LOGGER.debug("DO FILTER");	
////		
////		hsrReference = wrapResponse(response);
////
////		super.doFilterInternal(request, response, filterChain);
//		
//	}
	
	

//    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
//    	
//        if (response instanceof ContentCachingResponseWrapper) {
//        	
//            return (ContentCachingResponseWrapper) response;
//            
//        } else {
//        	
//            return new ContentCachingResponseWrapper(response);
//        }
//    }
    
//    protected void logResponsePayload(ContentCachingResponseWrapper responseWrapped, final String requestID) {
//    	
//    	final String date = LocalDateTime.now().toString();
//    			
//		String payload = null;
//		
//		try {
//			
//			Reader reader = new InputStreamReader(responseWrapped.getContentInputStream());
//			payload = IOUtils.toString(reader);
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		final String finalPayload = (StringUtils.isNotEmpty(payload)) ? payload : REPLACEMENT_FOR_NO_BODY;
//
//		String message = String.format("RESPONSE_FOR_REQUEST_ID_%s PAYLOAD_%s", date, payload);
//		
//		LOGGER.trace(message);
//    }
	

}
