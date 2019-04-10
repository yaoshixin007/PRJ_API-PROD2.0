package com.jci.service.impl;

import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.naming.ServiceUnavailableException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jci.service.AuthorizationService;
import com.jci.transfer.Permission;
import com.jci.util.CommonUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	CommonUtil commonUtils;
	
	@Value("${com.azure.config.url}")
	String azureConfigUrl;
	
	@Override
	public Permission getActivityPermission(String jwtToken, String clientId, String clientSecret)
			throws CertificateException, IOException, JSONException, ServiceUnavailableException, InterruptedException,
			ExecutionException {
		Permission response = new Permission();
		List<String> rolesFromToken = getGuidFromJWT(jwtToken, clientId, clientSecret);
		response.setRoles(rolesFromToken);
		return response;
		
	}

	private List<String> getGuidFromJWT(String jwtToken,String clientId,String clientSecret) throws IOException, JSONException, CertificateException, ServiceUnavailableException, InterruptedException, ExecutionException {
		List<String> guidList = null;
		JSONObject configJson = commonUtils.readUrl(azureConfigUrl);

		if (jwtToken != null && !jwtToken.isEmpty()) {
			String strX5cKey = commonUtils.getX5cKeyString(jwtToken, configJson);
			PublicKey pk = commonUtils.getPublicKey(strX5cKey);
			
			Jws<Claims> jwtClaims =commonUtils.decryptJWT( jwtToken, pk);
			guidList = commonUtils.getRolesFromJWT(jwtClaims);
			
			//guidSet = new HashSet<>(guidList);
			//logger.info("========== Getting Groups ========={}",guidList);

		}
		return guidList;
	}
}
