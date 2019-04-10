package com.jci.service;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.naming.ServiceUnavailableException;

import org.json.JSONException;

import com.jci.transfer.Permission;

public interface AuthorizationService {
	
	public Permission getActivityPermission(String jwtToken,String clientId,String clientSecret) throws CertificateException, IOException, JSONException, ServiceUnavailableException, InterruptedException, ExecutionException;
}