package com.jci.controller;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.naming.ServiceUnavailableException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.common.core.InjectableLogger;
import com.jci.service.AuthorizationService;
import com.jci.transfer.Permission;
import com.jci.util.ApplicationConstants;

@RestController
public class AuthController {
	
	@InjectableLogger
	private static Logger logger;
	
	@Autowired
	AuthorizationService authorizationService;
	
	/*@GetMapping("/")
	public String defaultMethod() {
		return "index";
	}*/
	
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_PERMISSION, method = RequestMethod.GET)
	public Permission activityPermission(@RequestHeader HttpHeaders headers) throws CertificateException, JSONException, ServiceUnavailableException, IOException, InterruptedException, ExecutionException, MissingServletRequestParameterException {
		// Extract the token from the Authorization header
		String token;
        String clientId;
        String clientSecret;
        try {
			token = headers.get(ApplicationConstants.AUTHORIZATION).get(0).substring(ApplicationConstants.AUTHENTICATION_SCHEME.length()).trim();
	        clientId = headers.get("clientId").get(0);
	        clientSecret = headers.get("clientSecret").get(0);
        } catch(Exception ex) {
        	throw new MissingServletRequestParameterException("JWT Token", "Token");
        }
		
        //logger.info("getActivityPermission ScreenName clientId {}", clientId);
		logger.info("getActivityPermission ScreenName Headers{}", token);
		
		return authorizationService.getActivityPermission(token,clientId,clientSecret);
		
	}

}
